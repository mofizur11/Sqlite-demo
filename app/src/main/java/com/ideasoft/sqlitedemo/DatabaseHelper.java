package com.ideasoft.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydb";
    public static final String TABLE_NAME = "mytb";
    public static final int VERSION = 1;

    public static final String ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_PHONE = "phone";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT,"
                + COL_PHONE + " TEXT)";
        db.execSQL(query);
    }

    public void addNewPerson(String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_NAME, name);
        values.put(COL_PHONE, phone);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<Model> readPersonList() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME+ " ORDER BY id DESC", null);

        ArrayList<Model> arrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                arrayList.add(new Model(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return arrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
