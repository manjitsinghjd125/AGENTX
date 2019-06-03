package com.example.manjitsingh.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Missionhistory extends AppCompatActivity {
    private ListView agentslist;
    private Agents agents;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missionhistory);
        final Intent intent=getIntent();
        agents=(Agents)intent.getSerializableExtra("data");
        name=agents.getName();
        Toast.makeText(this,agents.getName(), Toast.LENGTH_SHORT).show();

        agentslist=(ListView)findViewById(R.id.Mission_list);
    }
    private void loadagentslist(){
        database db=new database(this);
        List<Agents> agents=db.dbSearchmission(name);
        db.close();
        MissionAdapter agentsAadpter=new MissionAdapter(this,agents);
        agentslist.setAdapter(agentsAadpter);
    }

    @Override
    protected void onResume() {
        loadagentslist();
        super.onResume();
    }

}
