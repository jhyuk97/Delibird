package com.example.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class listadapter extends ArrayAdapter<String> {
    listadapter(Context context, String[] items){
        super(context, R.layout.user15_m_listviewacc, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.user15_m_listviewacc, parent, false);
        String item = getItem(position);
        //TextView textView = (TextView)view.findViewById(R.id.textView);

       // textView.setText(item);

        return view;
    }
}
