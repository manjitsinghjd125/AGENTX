package com.example.manjitsingh.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class agentlist extends AppCompatActivity {
private ListView agentslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agentlist);

        agentslist=(ListView)findViewById(R.id.agent_list);
        agentslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Agents agents=(Agents)agentslist.getItemAtPosition(position);
                Intent intentgoto=new Intent(agentlist.this,AgentDetails.class);
                intentgoto.putExtra("data",agents);
                startActivity(intentgoto);
                //Toast.makeText(agentlist.this,agents.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadagentslist(){
        database db=new database(this);
        List<Agents> agents=db.dbSearch();
        db.close();
        AgentsAadpter agentsAadpter=new AgentsAadpter(this,agents);
        agentslist.setAdapter(agentsAadpter);
    }

    @Override
    protected void onResume() {
        loadagentslist();
        super.onResume();
    }
}
