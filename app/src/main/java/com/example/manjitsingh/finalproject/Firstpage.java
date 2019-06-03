package com.example.manjitsingh.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Firstpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
    }
    public void back(View v){
        Intent loginpage =new Intent(this, MainActivity.class);
        startActivity(loginpage);
    }
    public void searchpage(View v){
        Intent searchpage =new Intent(this, searchpage.class);
        startActivity(searchpage);
    }
    public void addagent(View v){
        Intent addagent =new Intent(this, addagent.class);
        startActivity(addagent);
    }
    public void agentlist(View v){
        Intent agentlist =new Intent(this, agentlist.class);
        startActivity(agentlist);
    }
}
