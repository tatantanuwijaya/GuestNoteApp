package com.example.weddingapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.print.pdf.PrintedPdfDocument;

import java.io.FileNotFoundException;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tamu.db";
    public static final String TABLE_NAME = "daftarhadiah_table";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "NAMA";
    public static final String COLUMN_3 = "ALAMAT";

    public DatabaseHelper2 (Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMA TEXT, ALAMAT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }
    public boolean insertData(String nama, String alamat){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_2,nama);
        contentValues.put(COLUMN_3,alamat);
        long result=sqLiteDatabase.insert(TABLE_NAME, null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select*from "+TABLE_NAME,null);
        return cursor;
    }
    public boolean updateData(String id, String nama, String alamat){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_1,id);
        contentValues.put(COLUMN_2,nama);
        contentValues.put(COLUMN_3,alamat);
        sqLiteDatabase.update(TABLE_NAME,contentValues, "ID=?", new String[]{id});
        return true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "ID=?",new String[]{id});
    }
    public Cursor searchData(String nama){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String[] columns = new String[] {COLUMN_1, COLUMN_2,COLUMN_3};
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME, columns, "NAMA=?", new String[]{nama}, null,null,null,null);
        return cursor;
    }
}
