package com.example.sqlliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Manoj on 11-03-2019.
 */

public class Mydbhandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="products.db";
    private static final String TABLE_PRODUCTS="products";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_PRODUCTNAME="productname";

    public Mydbhandler(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){

        super(context,DATABASE_NAME,factory,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = " CREATE TABLE " + TABLE_PRODUCTS + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_PRODUCTNAME + " TEXT " + " ); " ;

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addProduct(Products productname){
        ContentValues contentv=new ContentValues();

        contentv.put(COLUMN_PRODUCTNAME,productname.get_productname());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_PRODUCTS,null,contentv);
        db.close();


    }

    public void deleteProduct(String productname){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productname + "\";" );

    }

    public String databaseToString(){
        String dbString="";
        SQLiteDatabase db=getWritableDatabase();
        String query=" SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1 ";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("productname"))!=null)
            {
                dbString+=c.getString(c.getColumnIndex("productname"));
                dbString+="\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;

    }}