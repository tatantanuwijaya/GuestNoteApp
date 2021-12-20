package com.example.weddingapp;

import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TableRow;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="TAMU.db";
    public static final String TABLE_NAME="DAFTARTAMU_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAMA";
    public static final String COL_3="UANG";
    public static final String COL_4="BERAS";
    public static final String COL_5="OPAK";
    public static final String COL_6="PISANG";
    public static final String COL_7="RANGINANG";
    public static final String COL_8="WAJIT";
    public static final String COL_9="KUEALI";
    public static final String COL_10="STATUS";
    public static final String COL_11="LAIN";
    public static final String COL_12="ALAMAT";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMA TEXT, UANG INTEGER, BERAS INTEGER,OPAK INTEGER, PISANG INTEGER, RANGINANG INTEGER, WAJIT INTEGER, KUEALI INTEGER, STATUS TEXT,LAIN TEXT,ALAMAT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String nama, String uang, String beras, String opak, String pisang, String ranginang, String wajit, String kueali, String status, String lain, String alamat){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,nama);
        contentValues.put(COL_3,uang);
        contentValues.put(COL_4,beras);
        contentValues.put(COL_5,opak);
        contentValues.put(COL_6,pisang);
        contentValues.put(COL_7,ranginang);
        contentValues.put(COL_8,wajit);
        contentValues.put(COL_9,kueali);
        contentValues.put(COL_10,status);
        contentValues.put(COL_11,lain);
        contentValues.put(COL_12,alamat);
        long result =db.insert(TABLE_NAME,null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select*from "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String id,String nama, String uang, String beras, String opak, String pisang, String ranginang, String wajit, String kueali, String status, String lain, String alamat){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nama);
        contentValues.put(COL_3,uang);
        contentValues.put(COL_4,beras);
        contentValues.put(COL_5,opak);
        contentValues.put(COL_6,pisang);
        contentValues.put(COL_7,ranginang);
        contentValues.put(COL_8,wajit);
        contentValues.put(COL_9,kueali);
        contentValues.put(COL_10,status);
        contentValues.put(COL_11,lain);
        contentValues.put(COL_12,alamat);
        db.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});
        return true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?", new String[]{id});
    }
    public Cursor searchData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        String[] colums=new String[]{COL_1,COL_2,COL_3,COL_4,COL_5,COL_6,COL_7,COL_8,COL_9,COL_10,COL_11,COL_12};
        Cursor cursor=db.query(TABLE_NAME, colums, "ID=?", new String[]{id}, null, null, null,null);
        return cursor;
    }
    public Cursor searchDataNama(String nama){
        SQLiteDatabase db=this.getWritableDatabase();
        String[] columns=new String[]{COL_1,COL_2,COL_3,COL_4,COL_5,COL_6,COL_7,COL_8,COL_9,COL_10,COL_11,COL_12};
        Cursor cursor=db.query(TABLE_NAME, columns, "NAMA=?", new String[]{nama}, null, null,null,null);
        return cursor;
    }
}
