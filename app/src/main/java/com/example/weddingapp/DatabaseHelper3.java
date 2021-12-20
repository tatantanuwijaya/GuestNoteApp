package com.example.weddingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper3 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tamu.db";
    public static final String TABLE_NAME = "syukuran_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAMA";
    public static final String COL_3 = "BERAS";
    public static final String COL_4 = "LAIN";
    public static final String COL_5 = "ALAMAT";

    public DatabaseHelper3(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMA TEXT, BERAS INTEGER, LAIN TEXT, ALAMAT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nama, String beras, String lain, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3,beras);
        contentValues.put(COL_4, lain);
        contentValues.put(COL_5,alamat);
        long result = db.insert(TABLE_NAME, null,contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase sqLite = this.getWritableDatabase();
        Cursor cursor = sqLite.rawQuery("select*from " + TABLE_NAME, null);
        return cursor;
    }

    public boolean UpdateData(String id, String nama, String beras, String lain, String alamat){
        SQLiteDatabase sqLite = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3,beras);
        contentValues.put(COL_4, lain);
        contentValues.put(COL_5,alamat);
        sqLite.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase sqLite = this.getWritableDatabase();
        return sqLite.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }
}
