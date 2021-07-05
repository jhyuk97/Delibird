package com.example.delivered;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class user08_basket_adpater extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<user08_basket_data> sampledata;

    public user08_basket_adpater(Context context, ArrayList<user08_basket_data>data){
        mContext = context;
        sampledata = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }



    @Override
    public int getCount() {
        return sampledata.size();
    }

    @Override
    public user08_basket_data getItem(int position) {
        return sampledata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {







        View view = mLayoutInflater.inflate(R.layout.user08_basket_list,null);






        Button mi = (Button)view.findViewById(R.id.basket_list_mi);
        Button plus = (Button)view.findViewById(R.id.basket_list_plus);
        TextView name = (TextView)view.findViewById(R.id.basket_MenuName);
        final TextView money = (TextView)view.findViewById(R.id.basket_money);
        //TextView detail = (TextView)view.findViewById(R.id.basket_MenuName_more);
        final TextView count = (TextView)view.findViewById(R.id.basket_count);
        final String money3 = sampledata.get(position).getMoney();
        String bbb = sampledata.get(position).getCount();
        int ccc = Integer.parseInt(money3);
        int ddd = Integer.parseInt(bbb);
        int eee = ccc * ddd;
        TextView DeleteOne = (TextView)view.findViewById(R.id.DeleteItemOne);








        name.setText(sampledata.get(position).getName());
        money.setText(String.valueOf(eee));
        //detail.setText(sampledata.get(position).getDetail());
        count.setText(sampledata.get(position).getCount());

        DeleteOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sampledata.remove(position);
                notifyDataSetChanged();
            }
        });

        mi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count2 = count.getText().toString();
                String money2 = money.getText().toString();
                if(Integer.parseInt(count2) > 1) {
                    count.setText(""+(Integer.parseInt(count2) - 1));
                    money.setText(""+ (Integer.parseInt(money2) - Integer.parseInt(money3)));
                    sampledata.get(position).setCount(String.valueOf(Integer.parseInt(sampledata.get(position).getCount()) - 1));
                }
                else{
                }
            }
        });

        SharedPreferences pref = mContext.getSharedPreferences("pref", MODE_PRIVATE);

        papago papago = new papago();
        String language = pref.getString("language","");

        mi.setText(papago.papago(mi.getText().toString(),language));
        plus.setText(papago.papago(plus.getText().toString(),language));
        name.setText(papago.papago(name.getText().toString(),language));
        //detail.setText(papago.papago(detail.getText().toString(),language));
        count.setText(papago.papago(count.getText().toString(),language));

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count2 = count.getText().toString();
                String money2 = money.getText().toString();
                count.setText(""+(Integer.parseInt(count2) + 1));
                money.setText(""+ (Integer.parseInt(money2) + Integer.parseInt(money3)));
                sampledata.get(position).setCount(String.valueOf(Integer.parseInt(sampledata.get(position).getCount()) + 1));
            }
        });

        return view;


    }


}
