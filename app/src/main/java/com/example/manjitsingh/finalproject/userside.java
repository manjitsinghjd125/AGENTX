package com.example.manjitsingh.finalproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class userside extends AppCompatActivity {
    private usersidehelper helper;
    private String dir;
    private Agents agents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userside);
       helper =new usersidehelper(this);
      //  final Intent intent=getIntent();
       // agents=(Agents)intent.getSerializableExtra("data");
        Intent intent2=getIntent();
        String name=intent2.getStringExtra("data");
        database db = new database(this);
      agents =db.dbsearchuser(name);
        Toast.makeText(this,agents.getPhone(), Toast.LENGTH_SHORT).show();
        if(agents!=null){
            helper.fillform(agents);
        }
        Button web=(Button)findViewById(R.id.web);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String site=agents.getWebsite();
                if(!site.startsWith("http://")){ site="http://"+site;

                }
                Intent intentweb=new Intent(Intent.ACTION_VIEW, Uri.parse(site));

                startActivity(intentweb);
            }
        });
        Button Map=(Button)findViewById(R.id.geoloaction);
        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentmap=new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q="+agents.getAddress()));

                startActivity(intentmap);
            }
        });
        Button sms=(Button)findViewById(R.id.sms);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentsms=new Intent(Intent.ACTION_VIEW,Uri.parse("sms:"+agents.getPhone()));
                startActivity(intentsms);
            }
        });
        Button call=(Button)findViewById(R.id.form_call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(userside.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(userside.this,new String[]{Manifest.permission.CALL_PHONE},123);
                }
                else{
                    Intent intentcall=new Intent(Intent.ACTION_CALL);
                    intentcall.setData(Uri.parse("tel:"+agents.getPhone()));
                    startActivity(intentcall);
                }

            }
        });

    }
    public void senddata(View v){
        Intent sendupdate =new Intent(this, SendUpdate.class);
        sendupdate.putExtra("data",agents);
        startActivity(sendupdate);
    }
    public void mission(View v){
        Intent addagent =new Intent(this, Missionhistory.class);
        addagent.putExtra("data",agents);
        startActivity(addagent);
    }
    public void addmission(View v){
        Intent sendupdate =new Intent(this, NewMission.class);
        sendupdate.putExtra("data",agents);
        startActivity(sendupdate);
    }
    public void delete(View v){
        database db = new database(userside.this);
        db.dbdelete(agents);
        db.close();
        Intent sendupdate =new Intent(this, agentlist.class);
        sendupdate.putExtra("data",agents);
        startActivity(sendupdate);
    }
    public void edit(View v){

        Intent agentupdate =new Intent(this, AssignMission.class);
        agentupdate.putExtra("data",agents);
        startActivity(agentupdate);
    }
}
