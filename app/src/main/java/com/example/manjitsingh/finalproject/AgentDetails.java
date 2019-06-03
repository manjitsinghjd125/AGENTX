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

public class AgentDetails extends AppCompatActivity {
    private AgentDetailhelper helper;
    private String dir;
    private Agents agents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_details);
        helper =new AgentDetailhelper(this);
        final Intent intent=getIntent();
         agents=(Agents)intent.getSerializableExtra("data");
        Toast.makeText(this,agents.getName(), Toast.LENGTH_SHORT).show();
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
                Intent intentweb=new Intent(Intent.ACTION_VIEW,Uri.parse(site));

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
                if(ActivityCompat.checkSelfPermission(AgentDetails.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AgentDetails.this,new String[]{Manifest.permission.CALL_PHONE},123);
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
        database db = new database(AgentDetails.this);
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
//intent.setType("vnd.android.cursor.item/event");
//mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
