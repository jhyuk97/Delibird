package com.example.shop;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class user08_adapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<user08_data> sampledata;
    TextView name;
    TextView won;
    public user08_adapter(Context context, ArrayList<user08_data> data){
        mContext = context;
        sampledata = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sampledata.size();
    }

    @Override
    public user08_data getItem(int position) {
        return sampledata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.user08listviewacc,null);

        name = (TextView)view.findViewById(R.id.menu_name);
        won = (TextView)view.findViewById(R.id.menu_Won);
        name.setText(sampledata.get(position).getName());
        won.setText(sampledata.get(position).getNumber());



        SharedPreferences pref = mContext.getSharedPreferences("pref", MODE_PRIVATE);
        papago papago = new papago();
        String language = pref.getString("language","");

        name.setText(papago.papago(name.getText().toString(),language));
        won.setText(papago.papago(won.getText().toString(),language));
        name.setText(papago.papago(name.getText().toString(),language));




        return view;
    }
}
