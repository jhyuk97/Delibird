package com.example.rider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class rastaurantlist_adapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<raustarntSampledata> sampledata;

    public rastaurantlist_adapter(Context context, ArrayList<raustarntSampledata> data){
        mContext = context;
        sampledata = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sampledata.size();
    }

    @Override
    public raustarntSampledata getItem(int position) {
        return sampledata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.restaurantlist,null);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView3);
        TextView name = (TextView)view.findViewById(R.id.textView24);
        TextView num = (TextView)view.findViewById(R.id.textview26);

        imageView.setImageResource(sampledata.get(position).getPoster());
        name.setText(sampledata.get(position).getName());
        num.setText(sampledata.get(position).getNumber());
        return view;
    }
}
