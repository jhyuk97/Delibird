package com.example.rider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class orderlistadapter extends ArrayAdapter<String> {
    orderlistadapter(Context context, String[] items){
        super(context, R.layout.orderhistorylist, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater2 = LayoutInflater.from(getContext());
        View view1 = inflater2.inflate( R.layout.orderhistorylist, parent, false);
        String item = getItem(position);
        TextView textView1 = (TextView)view1.findViewById( R.id.StoreName);
        textView1.setText(item);
        return view1;
        }

}
