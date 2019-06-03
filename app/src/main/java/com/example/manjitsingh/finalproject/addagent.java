package com.example.manjitsingh.finalproject;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class addagent extends AppCompatActivity {
    private AgentHelper helper;
    private String dir;
    private static final int CAMERA_REQUEST = 990;
    //private static final int REQUEST_PERMISSIONS=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.askForCameraPermission();
        setContentView(R.layout.activity_addagent);
        helper =new AgentHelper(this);
        Intent intent=getIntent();
        Agents agents=(Agents)intent.getSerializableExtra("data");
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
                startActivityForResult(intentcamera, CAMERA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==CAMERA_REQUEST){
                helper.loadImage(dir);
            }
        }
    }

    public void back(View v){
        Intent firstpage =new Intent(this, Firstpage.class);
        startActivity(firstpage);
    }
    public void save(View v){

        Agents beans =helper.adgenthelper();
        if(beans.getName().equals("")&&beans.getLevel().equals("")&&beans.getAgency().equals("")&&beans.getWebsite().equals("")&&beans.getPhone().equals("")&&beans.getAddress().equals("")){
            Toast.makeText(this,"Fill data first",Toast.LENGTH_SHORT).show();

        }
        else {
            database db=new database(this);
            db.dbInsert1(beans);
            db.close();
            Toast.makeText(this,""+beans.getName()+" Saved",Toast.LENGTH_SHORT).show();
            finish();}
    }
//    public void askForCameraPermission(){
//        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
//            if ((ActivityCompat.shouldShowRequestPermissionRationale(addagent.this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(addagent.this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE))) {
//            } else {
//                ActivityCompat.requestPermissions(addagent.this,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
//                        REQUEST_PERMISSIONS);
//            }
//        }
//    }
}
