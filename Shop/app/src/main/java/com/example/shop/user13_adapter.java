package com.example.shop;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class user13_adapter extends BaseAdapter {
    TextView user13_name;
    TextView user13_detail;
    ImageView user13_img;
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<user13_data> data;

    public user13_adapter(Context context, ArrayList<user13_data> data){
        mContext = context;
        this.data = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.user12_acc,null);
        user13_name = (TextView) view.findViewById(R.id.user13_name);
        user13_detail = (TextView) view.findViewById(R.id.user13_detail);
        user13_img = (ImageView) view.findViewById(R.id.user13_img);
        user13_name.setText(data.get(position).getName());
        user13_detail.setText(data.get(position).getDetail());
        Bitmap myBitmap = BitmapFactory.decodeFile(data.get(position).getImage());
        user13_img.setImageBitmap(myBitmap);

        SharedPreferences pref = mContext.getSharedPreferences("pref", MODE_PRIVATE);
        papago papago = new papago();
        String language = pref.getString("language","");

        user13_detail.setText(papago.papago(user13_detail.getText().toString(),language));
        user13_name.setText(papago.papago(user13_name.getText().toString(),language));





        return view;
    }
}
