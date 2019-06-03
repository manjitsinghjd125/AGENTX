package com.example.manjitsingh.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by manjitsingh on 2017-12-13.
 */

public class MissionAdapter extends BaseAdapter {
    private final List<Agents> agent;
    private final Context context;
    public MissionAdapter(Context context,List<Agents> agent){
        this.context=context;
        this.agent=agent;
    }
    @Override
    public int getCount() {
        return agent.size();
    }

    @Override
    public Object getItem(int position) {
        return agent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return agent.get(position).getId();
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {

        Agents agents=agent.get(position);
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=converView;
        if(view==null){
            view=inflater.inflate(R.layout.item_list,parent,false);
        }

        TextView fieldname=(TextView)view.findViewById(R.id.item_name);
        fieldname.setText(agents.getMname());
        TextView fielddate=(TextView)view.findViewById(R.id.item_date);
        fielddate.setText(agents.getDate());
        TextView fieldstatus=(TextView)view.findViewById(R.id.item_status);
        fieldstatus.setText(agents.getStatus());

//        ImageView fieldphoto=(ImageView)view.findViewById(R.id.item_photo);
//        String dir=agents.getPhotopath();
//        if (dir!=null){
//            Bitmap bitmap= BitmapFactory.decodeFile(dir);
//            Bitmap lowbits=Bitmap.createScaledBitmap(bitmap,100,100,true);
//            fieldphoto.setImageBitmap(lowbits);
//            fieldphoto.setScaleType(ImageView.ScaleType.FIT_XY);
//
//        }


        return view;
    }
}