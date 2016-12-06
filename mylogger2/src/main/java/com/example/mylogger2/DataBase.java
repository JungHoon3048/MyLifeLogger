package com.example.mylogger2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    private static ArrayList<Integer> mArrayList;

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE MyLogger2 (_id INTEGER PRIMARY KEY AUTOINCREMENT,position TEXT,content TEXT,latitude DOUBLE,longitude DOUBLE,pNumber INTEGER,walk INTEGER);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String position, String content,double latitude,double longitude,int pNumber,int walk) {

        SQLiteDatabase db = getWritableDatabase();
        String s="INSERT INTO MyLogger2 VALUES(null, '" + position + "','" + content + "','" + latitude + "','" + longitude + "','" + pNumber + "','" + walk + "');";
        db.execSQL(s);
        db.close();
    }

    public String getResult() {

        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM MyLogger2", null);
        while (cursor.moveToNext()) {
            result += cursor.getInt(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getString(2)
                    + " | "
                    + cursor.getDouble(3)
                    + " | "
                    + cursor.getDouble(4)
                    + " | "
                    + cursor.getInt(5)
                    + " | "
                    + cursor.getInt(6)
                    + "\n";
        }

        return result;
    }

    public ArrayList<Integer> getPosition(){

        SQLiteDatabase db = getReadableDatabase();

        mArrayList=new ArrayList<Integer>();

        Cursor cursor = db.rawQuery("SELECT pNumber FROM MyLogger2", null);
        while(cursor.moveToNext()){
            mArrayList.add(cursor.getInt(0));

        }
        return mArrayList;
    }

    public String getContent(){

        SQLiteDatabase db = getReadableDatabase();
        String content="";
        Cursor cursor = db.rawQuery("SELECT content FROM MyLogger2", null);

        return content;
    }
}
