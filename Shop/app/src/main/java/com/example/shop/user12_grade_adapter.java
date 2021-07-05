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

public class user12_grade_adapter extends BaseAdapter {
    TextView name;
    TextView phone;
    TextView grade;



    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<user12_grade_data> data;






    public user12_grade_adapter(Context context, ArrayList<user12_grade_data> data) {
        mContext = context;
        this.data = data;
        mLayoutInflater = LayoutInflater.from(mContext);

        papago papago = new papago();

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
        View view = mLayoutInflater.inflate(R.layout.user12_grade_acc,null);
        name = (TextView) view.findViewById(R.id.user12_Name);
        phone = (TextView) view.findViewById(R.id.user12_Phone);
        grade = (TextView) view.findViewById(R.id.user12_grds);

        name.setText(data.get(position).getName());
        phone.setText(data.get(position).getPhone());
        grade.setText(data.get(position).getGrade());

  SharedPreferences pref = mContext.getSharedPreferences("pref", MODE_PRIVATE);
    String language = pref.getString("language","");





        return view;






    }










}
