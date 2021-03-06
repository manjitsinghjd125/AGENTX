package com.example.manjitsingh.finalproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by manjitsingh on 2018-11-25.
 */

public class usersidehelper implements Serializable {
    private final TextView name;
    private final TextView level;
    private final TextView agency;
    private final TextView website;
    private final TextView country;
    private final TextView phone;
    private final TextView address;
    private final ImageView photo;
    private Agents beans;

    public usersidehelper(userside addagent)
    {
        name=(TextView)addagent.findViewById(R.id.aname);
        level=(TextView)addagent.findViewById(R.id.level);
        agency=(TextView)addagent.findViewById(R.id.agency);
        website=(TextView)addagent.findViewById(R.id.website);
        country=(TextView)addagent.findViewById(R.id.country);
        phone=(TextView)addagent.findViewById(R.id.phone);
        address=(TextView)addagent.findViewById(R.id.address);
        photo=(ImageView)addagent.findViewById(R.id.form_photo);


    }

    public void fillform(Agents beans){
        name.setText(beans.getName());
        level.setText(beans.getLevel());
        agency.setText(beans.getAgency());
        website.setText(beans.getWebsite());
        country.setText(beans.getCountry());
        phone.setText(beans.getPhone());
        address.setText(beans.getAddress());
        loadImage(beans.getPhotopath());
        this.beans=beans;

    }
    public void loadImage(String dir){
        if(dir!=null){
            Bitmap bitmap= BitmapFactory.decodeFile(dir);
            Bitmap lowbit=Bitmap.createScaledBitmap(bitmap,300,300,true);
            photo.setImageBitmap(lowbit);
            photo.setScaleType(ImageView.ScaleType.FIT_XY);
            photo.setTag(dir);
        }
    }

}
