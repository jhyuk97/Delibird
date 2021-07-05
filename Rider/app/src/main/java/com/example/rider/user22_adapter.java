package com.example.rider;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class user22_adapter extends BaseAdapter {
    ArrayList<user22_data> data;
    LayoutInflater mLayoutInflater = null;
    Context mContext = null;
    TextView user22_date;
    TextView user22_title;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public user22_adapter(ArrayList<user22_data> data, Context Context) {
        this.data = data;
        mContext = Context;
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
        View view = mLayoutInflater.inflate(R.layout.user22_acc,null);
        user22_date = (TextView)view.findViewById(R.id.user22_date);
        user22_title = (TextView)view.findViewById(R.id.user22_title);
        user22_title.setText(data.get(position).getTitle());
        user22_date.setText(data.get(position).getDate());



        SharedPreferences pref = mContext.getSharedPreferences("pref", MODE_PRIVATE);
        papago papago = new papago();
        String language = pref.getString("language","");
        user22_date.setText(papago.papago(user22_date.getText().toString(),language));
        user22_title.setText(papago.papago(user22_title.getText().toString(),language));


        return view;
    }
}
