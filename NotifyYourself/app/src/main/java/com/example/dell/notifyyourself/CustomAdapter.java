package com.example.dell.notifyyourself;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;

public class CustomAdapter extends BaseAdapter {
    String[] arr;
    LayoutInflater inflater;
    CustomAdapter(Context context,String[] arr)
    {
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arr=arr;
    }

    @Override
    public int getCount() {
        return arr.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.layout,null);

        TextView textView=(TextView)view.findViewById(R.id.textViews);
        //textView.setTextColor(Color.BLUE);
        return view;
    }
}
