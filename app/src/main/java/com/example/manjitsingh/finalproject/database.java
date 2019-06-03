package com.example.manjitsingh.finalproject;

/**
 * Created by manjitsingh on 2017-12-11.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class database extends SQLiteOpenHelper {
      code cod=new code();
    public database(Context context){
        super(context,"C0721464_XAGENTS1",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE ADMIN(id INTEGER PRIMARY KEY,NAME TEXT NOT NULL,PASSWORD TEXT)";
        sqLiteDatabase.execSQL(sql);
        String sql1="CREATE TABLE Agents(id INTEGER PRIMARY KEY,NAME TEXT NOT NULL,LEVEL TEXT,AGENCY TEXT,WEBSITE TEXT,COUNTRY TEXT,PHONE TEXT,ADDRESS TEXT,PHOTOPATH TEXT)";
        sqLiteDatabase.execSQL(sql1);
        String sql4="CREATE TABLE Users(id INTEGER PRIMARY KEY,UNAME TEXT NOT NULL,PASSWORD TEXT,NAME TEXT NOT NULL,LEVEL TEXT,AGENCY TEXT,WEBSITE TEXT,COUNTRY TEXT,PHONE TEXT,ADDRESS TEXT,PHOTOPATH TEXT)";
        sqLiteDatabase.execSQL(sql4);
        String sql2="CREATE TABLE missions(id INTEGER PRIMARY KEY,NAME TEXT NOT NULL,MNAME TEXT,DATE TEXT,STATUS TEXT)";
        sqLiteDatabase.execSQL(sql2);
        String sql3="CREATE TABLE missionsphotos(id INTEGER PRIMARY KEY,NAME TEXT NOT NULL,MPHOTOS TEXT)";
        sqLiteDatabase.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            sqLiteDatabase.execSQL("ALTER TABLE missions ADD COLUMN MNAME TEXT ");
        }
//        String sql="DROP TABLE IF EXISTS missions";
//        sqLiteDatabase.execSQL(sql);
//        onCreate(sqLiteDatabase);
    }
//        public void deletetable(){
//        SQLiteDatabase db=this.getWritableDatabase();
//        onCreate(db);
//        String sql="DROP TABLE IF EXISTS ADMIN";
//        db.execSQL(sql);
//
//    }
    public List<Agents> dbSearch(){

        SQLiteDatabase db=getReadableDatabase();
        String sql="SELECT * FROM Agents";
        Cursor c=db.rawQuery(sql,null);
        List<Agents> agentlist=new ArrayList<Agents>();
        while(c.moveToNext()){
            Agents agents=new Agents();

            agents.setId(c.getLong(c.getColumnIndex("id")));
            agents.setName(cod.decode(c.getString(c.getColumnIndex("NAME"))));
            agents.setLevel(cod.decode(c.getString(c.getColumnIndex("LEVEL"))));
            agents.setAgency(cod.decode(c.getString(c.getColumnIndex("AGENCY"))));
            agents.setWebsite(cod.decode(c.getString(c.getColumnIndex("WEBSITE"))));
            agents.setCountry(cod.decode(c.getString(c.getColumnIndex("COUNTRY"))));
            agents.setPhone(cod.decode(c.getString(c.getColumnIndex("PHONE"))));
            agents.setAddress(cod.decode(c.getString(c.getColumnIndex("ADDRESS"))));
            agents.setPhotopath(c.getString(c.getColumnIndex("PHOTOPATH")));
            agentlist.add(agents);

        }
        c.close();
        return agentlist;
    }
    public String dbfind(String name,String pass){

        String name1, pass1,result="";

        SQLiteDatabase db = getReadableDatabase();
        String sql ="SELECT * FROM ADMIN";
        Cursor c=db.rawQuery(sql,null);
        while(c.moveToNext()){
            name1=cod.decode(c.getString(c.getColumnIndex("NAME")));
            pass1=cod.decode(c.getString(c.getColumnIndex("PASSWORD")));
            if(name1.equals(name.toLowerCase())&&pass1.equals(pass.toLowerCase())){
                result="yes";
            }
        }
        c.close();
        return result;
    }
    public Agents dbsearchagent(String name){

        String name1,result="";
        Agents agents=new Agents();
        SQLiteDatabase db = getReadableDatabase();
        String sql="SELECT * FROM Agents WHERE NAME = ?";
        Cursor c=db.rawQuery(sql,new String[]{cod.encrypt(name)});
        while(c.moveToNext()){
            agents.setId(c.getLong(c.getColumnIndex("id")));
            agents.setName(cod.decode(c.getString(c.getColumnIndex("NAME"))));
            agents.setLevel(cod.decode(c.getString(c.getColumnIndex("LEVEL"))));
            agents.setAgency(cod.decode(c.getString(c.getColumnIndex("AGENCY"))));
            agents.setWebsite(cod.decode(c.getString(c.getColumnIndex("WEBSITE"))));
            agents.setCountry(cod.decode(c.getString(c.getColumnIndex("COUNTRY"))));
            agents.setPhone(cod.decode(c.getString(c.getColumnIndex("PHONE"))));
            agents.setAddress(cod.decode(c.getString(c.getColumnIndex("ADDRESS"))));
            agents.setPhotopath(c.getString(c.getColumnIndex("PHOTOPATH")));
        }
        c.close();
        return agents;
    }
    public Agents dbsearchuser(String name){

        String name1,result="";
        Agents agents=new Agents();
        SQLiteDatabase db = getReadableDatabase();
        String sql="SELECT * FROM Users WHERE UNAME = ?";
        Cursor c=db.rawQuery(sql,new String[]{cod.encrypt(name)});
        while(c.moveToNext()){
            agents.setId(c.getLong(c.getColumnIndex("id")));
            agents.setName(cod.decode(c.getString(c.getColumnIndex("NAME"))));
            agents.setLevel(cod.decode(c.getString(c.getColumnIndex("LEVEL"))));
            agents.setAgency(cod.decode(c.getString(c.getColumnIndex("AGENCY"))));
            agents.setWebsite(cod.decode(c.getString(c.getColumnIndex("WEBSITE"))));
            agents.setCountry(cod.decode(c.getString(c.getColumnIndex("COUNTRY"))));
            agents.setPhone(cod.decode(c.getString(c.getColumnIndex("PHONE"))));
            agents.setAddress(cod.decode(c.getString(c.getColumnIndex("ADDRESS"))));
            agents.setPhotopath(c.getString(c.getColumnIndex("PHOTOPATH")));
        }
        c.close();
        return agents;
    }
    public void dbinsert(adminbeans beans){
        String name,pass;
        name=cod.encrypt(beans.getUserid());
        pass=cod.encrypt(beans.getPassword());
        SQLiteDatabase db =getWritableDatabase();
        ContentValues logindata=new ContentValues();
        logindata.put("NAME" ,name);
        logindata.put("PASSWORD",pass);
        db.insert("ADMIN",null,logindata);
    }
    public void dbinsert2(resgister beans){
        String name,pass;
        name=cod.encrypt(beans.getUserid());
        pass=cod.encrypt(beans.getPassword());
        SQLiteDatabase db =getWritableDatabase();
        ContentValues logindata=new ContentValues();
        logindata.put("NAME" ,name);
        logindata.put("PASSWORD",pass);
        db.insert("ADMIN",null,logindata);
    }
    public void dbMission(String name,String Mname,String date,String status){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues missiondata=new ContentValues();
        missiondata.put("NAME" ,cod.encrypt(name));
        missiondata.put("MNAME",cod.encrypt(Mname));
        missiondata.put("DATE",date);
        missiondata.put("STATUS",cod.encrypt(status));

        db.insert("missions",null,missiondata);
    }
public void dbInsert1(Agents agents){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues agentdata=getContenvalues(agents);
        db.insert("Agents",null,agentdata);
}
    public void dbInsertuser(Agents agents){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues agentdata=getContenvalues1(agents);
        db.insert("Users",null,agentdata);
    }
    public  ContentValues getContenvalues1(Agents agents){
        String name,pass;
        name=cod.encrypt(agents.getUser());
        pass=cod.encrypt(agents.getPassword());
        SQLiteDatabase db =getWritableDatabase();
        ContentValues agentdata=new ContentValues();
        agentdata.put("NAME",cod.encrypt(agents.getName()));
        agentdata.put("LEVEL",cod.encrypt(agents.getLevel()));
        agentdata.put("AGENCY",cod.encrypt(agents.getAgency()));
        agentdata.put("WEBSITE",cod.encrypt(agents.getWebsite()));
        agentdata.put("COUNTRY",cod.encrypt(agents.getCountry()));
        agentdata.put("PHONE",cod.encrypt(agents.getPhone()));
        agentdata.put("ADDRESS",cod.encrypt(agents.getAddress()));
        agentdata.put("PHOTOPATH",agents.getPhotopath());
        agentdata.put("UNAME",name);
        agentdata.put("PASSWORD",pass);

        return agentdata;
    }
public  ContentValues getContenvalues(Agents agents){
    ContentValues agentdata=new ContentValues();
    agentdata.put("NAME",cod.encrypt(agents.getName()));
    agentdata.put("LEVEL",cod.encrypt(agents.getLevel()));
    agentdata.put("AGENCY",cod.encrypt(agents.getAgency()));
    agentdata.put("WEBSITE",cod.encrypt(agents.getWebsite()));
    agentdata.put("COUNTRY",cod.encrypt(agents.getCountry()));
    agentdata.put("PHONE",cod.encrypt(agents.getPhone()));
    agentdata.put("ADDRESS",cod.encrypt(agents.getAddress()));
    agentdata.put("PHOTOPATH",agents.getPhotopath());

    return agentdata;
}
public void dbdelete(Agents agents){
    SQLiteDatabase db =getWritableDatabase();
    String[] param={String.valueOf(agents.getId())};
    String[] param1={String.valueOf(agents.getName())};
    db.delete("Agents","id=?",param);
    db.delete("missions","NAME=?",param1);
}
    public void dbalter(Agents agents){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues agentdata=new ContentValues();
        String[] param={String.valueOf(agents.getId())};
        db.update("Agents",agentdata,"id=?",param);
    }
    public void dbinsertmission(String name,String dir){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues logindata=new ContentValues();
        logindata.put("NAME" ,cod.encrypt(name));
        logindata.put("MPHOTOS",dir);
        db.insert("missionsphotos",null,logindata);
    }
    public ArrayList<String> dbSearch1(String name){
        ArrayList<String> path = new ArrayList();
        String path1 = null;
        SQLiteDatabase db=getReadableDatabase();

        String sql="SELECT * FROM missionsphotos WHERE NAME = ?";
        Cursor c=db.rawQuery(sql,new String[]{cod.encrypt(name)});
        while(c.moveToNext()){

            path1=c.getString(c.getColumnIndex("MPHOTOS"));

            path.add(path1);

        }
        c.close();
        return path;
    }
    public List<Agents> dbSearchmission(String name){

        String path1 = null;
        SQLiteDatabase db=getReadableDatabase();

        String sql="SELECT * FROM missions WHERE NAME = ? AND MNAME IS NOT NULL AND DATE IS NOT NULL AND STATUS IS NOT NULL";
        Cursor c=db.rawQuery(sql,new String[]{cod.encrypt(name)});
        List<Agents> agentlist=new ArrayList<Agents>();
        while(c.moveToNext()){
            Agents agents=new Agents();

            agents.setId(c.getLong(c.getColumnIndex("id")));
            agents.setName(cod.decode(c.getString(c.getColumnIndex("NAME"))));
            agents.setMname( cod.decode(c.getString(c.getColumnIndex("MNAME"))));

            agents.setDate(c.getString(c.getColumnIndex("DATE")));
            agents.setStatus(cod.decode(c.getString(c.getColumnIndex("STATUS"))));
//            agents.setCountry(c.getString(c.getColumnIndex("COUNTRY")));
//            agents.setPhone(c.getString(c.getColumnIndex("PHONE")));
//            agents.setAddress(c.getString(c.getColumnIndex("ADDRESS")));
//            agents.setPhotopath(c.getString(c.getColumnIndex("PHOTOPATH")));
            agentlist.add(agents);

        }
        c.close();
        return agentlist;
    }

}
