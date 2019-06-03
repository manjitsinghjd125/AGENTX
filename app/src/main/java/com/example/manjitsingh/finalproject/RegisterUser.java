package com.example.manjitsingh.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity {
    private adminhelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        helper =new adminhelper(this);
    }
    public void back(View v){
        Intent loginpage =new Intent(this, MainActivity.class);
        startActivity(loginpage);
    }
    public void save(View v){

        adminbeans beans =helper.helpadmin();
        if(beans.getUserid().equals("")&&beans.getPassword().equals("")){
            Toast.makeText(this,"Fill data first",Toast.LENGTH_SHORT).show();

        }
        else {
        database db=new database(this);
        db.dbinsert(beans);
        db.close();
        Toast.makeText(this,""+beans.getUserid()+" Saved",Toast.LENGTH_SHORT).show();
        finish();}
    }
}
