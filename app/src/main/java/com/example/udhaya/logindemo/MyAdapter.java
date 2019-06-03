package com.example.udhaya.logindemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * Created by udhaya on 07-10-2018.
 */

class MyAdapter extends ArrayAdapter {

    private final Activity context;
    private final Integer [] image;

    public MyAdapter (Activity context, Integer [] image)
    {
        super(context,R.layout.photolist);
        this.image=image;
        this.context=context;
    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
        //LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater=context.getLayoutInflater();

        //View row = inflater.inflate(R.layout.photolist,parent,false);

        View row=inflater.inflate(R.layout.photolist, null,true);

        ImageView myimage= (ImageView)row.findViewById(R.id.list1);

        myimage.setImageResource(image[position]);
        return  row;

    }

}

