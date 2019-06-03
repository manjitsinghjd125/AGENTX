package com.example.manjitsingh.finalproject;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class adduser extends AppCompatActivity {
    private AgentHelper helper;
    private helperR helper1;
    private String dir;
    String profilePath = "";
    String selectedFiles = "";
    private static final int REQUEST_PERMISSIONS = 100;
    private static final int CAMERA_REQUEST = 1888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        helper = new AgentHelper(this);
        helper1 =new helperR(this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==CAMERA_REQUEST){
                helper.loadImage(dir);
            }
        }
    }
    public void selectImages(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = null;
        try {
            uri = FileProvider.getUriForFile(adduser.this,
                    BuildConfig.APPLICATION_ID + ".com.project.manjitsingh.GenericFileProvider",
                    createImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            cameraIntent.putExtra("return-data", true);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        } catch (ActivityNotFoundException e) {
            System.out.print(e);
        }
    }
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        selectedFiles = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                selectedFiles,
                ".jpg",
                storageDir
        );
        return image;
    }

    public void askForCameraPermission(){
        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(adduser.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(adduser.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(adduser.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        }
    }
    public void save(View v){

        Agents beans =helper.adgenthelper();
        resgister beans1 =helper1.helpregister();
        if(beans.getName().equals("")&&beans.getLevel().equals("")&&beans.getAgency().equals("")&&beans.getWebsite().equals("")&&beans.getPhone().equals("")&&beans.getAddress().equals("")){
            Toast.makeText(this,"Fill data first",Toast.LENGTH_SHORT).show();

        }
        else {
            database db=new database(this);

            db.dbInsert1(beans);
            db.dbInsertuser(beans);
            db.dbinsert2(beans1);
            db.close();

            Toast.makeText(this,""+beans.getName()+" Saved",Toast.LENGTH_SHORT).show();
            finish();}
    }
}
