package com.example.manjitsingh.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class searchpage extends AppCompatActivity {
private Agents agents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);
agents=new Agents();
//        Button button=(Button)findViewById(R.id.sbutton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    public void search(View view){
        final EditText search=(EditText)findViewById(R.id.name);
        final TextView name=(TextView) findViewById(R.id.aname);
        final TextView lvl=(TextView) findViewById(R.id.level);
        final TextView agency=(TextView) findViewById(R.id.agency);
        final TextView website=(TextView) findViewById(R.id.website);
        final TextView country=(TextView) findViewById(R.id.country);
        final TextView phone=(TextView) findViewById(R.id.phone);
        final TextView address=(TextView) findViewById(R.id.address);

        String agentname=search.getText().toString();
       // Toast.makeText(this,agentname,Toast.LENGTH_SHORT).show();
        database db=new database(searchpage.this);
        agents=db.dbsearchagent(agentname);
        db.close();
        if(agents!=null){
            name.setText(agents.getName());
            lvl.setText(agents.getLevel());
            agency.setText(agents.getAgency());
            website.setText(agents.getWebsite());
            country.setText(agents.getCountry());
            phone.setText(agents.getPhone());
            address.setText(agents.getAddress());
        }
        else {
            Toast.makeText(this,"wrong Entry",Toast.LENGTH_SHORT).show();
        }


    }
}
