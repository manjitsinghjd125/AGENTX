package com.example.manjitsingh.finalproject;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class SendUpdate extends AppCompatActivity {

    private static final int CAMERA_CODE = 990;
    private String dir;
    public int PIC_CODE=0;
    private  Agents agents;
    String photo="";
    String message;
    String phoneNo;
    String name;
    ArrayList<String> itemList;
    String phone;
    GridView gridview;
    ImageAdapter myImageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_update);

        final Intent intent=getIntent();
        agents=(Agents)intent.getSerializableExtra("data");
//        database db=new database(this);
//        itemList=db.dbSearch1(agents.getName());
        TextView uname=(TextView)findViewById(R.id.name);
        uname.setText(agents.getName());
         gridview = (GridView) findViewById(R.id.gridview);
//        myImageAdapter = new ImageAdapter(this);
//        myImageAdapter.setItemList(itemList);
//        gridview.setAdapter(myImageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // DO something
                photo=photo.concat("Photo no."+position+", ");
                Toast.makeText(SendUpdate.this,"photo is Selected "+photo,Toast.LENGTH_SHORT).show();

            }
        });


        Toast.makeText(this,agents.getName(), Toast.LENGTH_SHORT).show();
        Button button = (Button) findViewById(R.id.Camera);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=agents.getName();


                    Intent intentcamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    dir = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
               // agents.setPhotopath(dir);
                    File filephoto = new File(dir);
                    intentcamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filephoto));
                    startActivityForResult(intentcamera, CAMERA_CODE);

                }}
        );
        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNo = agents.getPhone();
                message="Mission Update, please look "+photo+" ";

                    Intent intentsms=new Intent(Intent.ACTION_VIEW,Uri.parse("sms:"+phoneNo));
                    intentsms.putExtra("sms_body",message);
                    //intentsms.setType("image/jpg");
                    //intentsms.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(new File(itemList.get(0))));

                    startActivity(intentsms);


            }
        });
    }

    @Override
    protected void onResume() {
        database db=new database(this);
        itemList=db.dbSearch1(agents.getName());
        myImageAdapter = new ImageAdapter(this);
        myImageAdapter.setItemList(itemList);
        gridview.setAdapter(myImageAdapter);
        super.onResume();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CODE ) {
            if(resultCode == RESULT_OK){

                // get new image here like this
                if(PIC_CODE<20){
                    // add new requset of picture like this
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_CODE);

                    String name=agents.getName();

                    database db = new database(this);
                    db.dbinsertmission(name,dir);
                    Toast.makeText(this,"Fill Saved ",Toast.LENGTH_SHORT).show();
                    db.close();
                    PIC_CODE++;}

            }

        }
    }
}
