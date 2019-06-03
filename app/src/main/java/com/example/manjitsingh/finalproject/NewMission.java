package com.example.manjitsingh.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewMission extends AppCompatActivity {
    private Agents agents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mission);
        final Intent intent=getIntent();
         agents=(Agents)intent.getSerializableExtra("data");
        Toast.makeText(this,agents.getName(), Toast.LENGTH_SHORT).show();
        final EditText mission=(EditText) findViewById(R.id.form_mission);
        final EditText date=(EditText) findViewById(R.id.form_date);
        final EditText Status=(EditText) findViewById(R.id.form_Status);
        Button button=(Button)findViewById(R.id.save);
//        agents.setMname(Mname.getText().toString());
//        agents.setDate(date.getText().toString());
//        agents.setStatus(Status.getText().toString());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database db=new database(NewMission.this);
                db.dbMission(agents.getName(),mission.getText().toString(),date.getText().toString(),Status.getText().toString());
                db.close();
                Toast.makeText(NewMission.this,""+mission.getText().toString()+" Saved",Toast.LENGTH_SHORT).show();
                mission.setText("");
                date.setText("");
                Status.setText("");
                //Toast.makeText(NewMission.this,""+mission.getText().toString()+" Saved",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void back(View v){
        Intent back =new Intent(this, AgentDetails.class);
        back.putExtra("data",agents);
        startActivity(back);
    }
}
