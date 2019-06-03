package com.example.manjitsingh.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by manjitsingh on 2017-12-11.
 */

public class databaseagent extends SQLiteOpenHelper {

    public databaseagent(Context context){
        super(context,"C0721464_SECRET",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE ADMIN(id INTEGER PRIMARY KEY,NAME TEXT NOT NULL,PASSWORD TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql="DROP TABLE IF EXISTS ADMIN";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
    //    public void deletetable(){
//        SQLiteDatabase db=this.getWritableDatabase();
//        onCreate(db);
//        String sql="DROP TABLE IF EXISTS photobook";
//        db.execSQL(sql);
//
//    }
//    public ArrayList<String> dbSearch1(String name){
//        ArrayList<String> path = new ArrayList();
//        String path1 = null;
//        SQLiteDatabase db=getReadableDatabase();
//
//        String sql="SELECT * FROM photobook WHERE NAME = ?";
//        Cursor c=db.rawQuery(sql,new String[]{name});
//        while(c.moveToNext()){
//
//            path1=c.getString(c.getColumnIndex("PATH"));
//
//            path.add(path1);
//
//        }
//        c.close();
//        return path;
//    }
    public String dbfind(String name,String pass){

        String name1, pass1,result="";

        SQLiteDatabase db = getReadableDatabase();
        String sql ="SELECT * FROM ADMIN";
        Cursor c=db.rawQuery(sql,null);
        while(c.moveToNext()){
            name1=c.getString(c.getColumnIndex("NAME"));
            pass1=c.getString(c.getColumnIndex("PASSWORD"));
            if(name1.equals(name)&&pass1.equals(pass)){
                result="yes";
            }
        }
        c.close();
        return result;
    }
    public void dbinsert(adminbeans beans){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues logindata=new ContentValues();
        logindata.put("NAME" ,beans.getUserid());
        logindata.put("PASSWORD",beans.getPassword());
        db.insert("ADMIN",null,logindata);
    }
//    public photobeans dbsearch(String name){
//        long id;
//        photobeans beans=null;
//        String pname,path;
//        SQLiteDatabase db = getReadableDatabase();
//        String sql ="SELECT * FROM photobook WHERE NAME ="+name;
//        Cursor c=db.rawQuery(sql,null);
//        while(c.moveToNext()){
//            beans=new photobeans();
//            beans.setId(c.getLong(c.getColumnIndex("id")));
//            beans.setP_name(c.getString(c.getColumnIndex("NAME")));
//            beans.setPath(c.getString(c.getColumnIndex("PATH")));
//
//        }
//        c.close();
//        return beans;
//    }
}