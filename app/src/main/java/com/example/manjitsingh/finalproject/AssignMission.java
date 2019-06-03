package com.example.manjitsingh.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class AssignMission extends AppCompatActivity {
    private AgentHelper helper;
    private String dir;
    private Agents agents;
    private static final int CAMERA_CODE = 990;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_mission);
        helper =new AgentHelper(this);
        Intent intent=getIntent();
         agents=(Agents)intent.getSerializableExtra("data");
        if(agents!=null){
            helper.fillform(agents);
        }
        Button button=(Button)findViewById(R.id.button_form_photo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                dir = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File filephoto = new File(dir);
                intentcamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filephoto));
                startActivityForResult(intentcamera, CAMERA_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==CAMERA_CODE){
                helper.loadImage(dir);
            }
        }
    }

    public void back(View v){
        Intent firstpage =new Intent(this, AgentDetails.class);
        firstpage.putExtra("data",agents);
        startActivity(firstpage);
    }
    public void save(View v){

        Agents beans =helper.adgenthelper();
        if(beans.getName().equals("")&&beans.getLevel().equals("")&&beans.getAgency().equals("")&&beans.getWebsite().equals("")&&beans.getPhone().equals("")&&beans.getAddress().equals("")){
            Toast.makeText(this,"Fill data first",Toast.LENGTH_SHORT).show();

        }
        else {
            database db=new database(this);
            db.dbalter(beans);
            db.close();
            Toast.makeText(this,""+beans.getName()+" Saved",Toast.LENGTH_SHORT).show();
            finish();}
    }
}
