package com.example.rider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class user15_adapter extends BaseAdapter {
    ArrayList<user15_data> data;
    LayoutInflater mLayoutInflater = null;
    Context mContext = null;
    ImageView img;
    ImageView star;
    TextView user15_name;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String id;

    public user15_adapter(ArrayList<user15_data> data, Context Context, String id) {
        this.data = data;
        mContext = Context;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.id = id;
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
        View view = mLayoutInflater.inflate(R.layout.user15_m_listviewacc,null);
        final int pos = position;
        img = (ImageView)view.findViewById(R.id.user15_img);

        user15_name = (TextView)view.findViewById(R.id.user15_name);
        user15_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql = "select  매장전화번호 from 매장 where 매장번호 = " + data.get(pos).getData();
                String number = "";
                tryConnect(true);
                try{
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql);
                    while (rs.next()){
                        number = rs.getString("매장전화번호");
                    }
                }catch (Exception e){
                    e.getMessage();
                }
                Intent intent = new Intent(mContext,user08_restaurant_data.class);
                intent.putExtra("number1",number);
                mContext.startActivity(intent);
            }
        });

        star = (ImageView)view.findViewById(R.id.user15_star);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql = "delete 즐겨찾기 where 사용자ID = '" + id + "' and 매장번호 = " + data.get(pos).getData();
                tryConnect(true);
                try{
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                }catch (Exception e){
                    e.getMessage();
                }
            }
        });

        user15_name.setText(data.get(position).getUser15_name());
        Bitmap ImgBitmap = BitmapFactory.decodeFile(data.get(position).getUser15_img());
        img.setImageBitmap(ImgBitmap);




        return view;
    }

    private boolean tryConnect(boolean showMessage) {
        try {
            app_loginDB connClass = new app_loginDB();
            conn = connClass.TestQuery();
            if (conn == null) {
                return false;
            } else if (conn.isClosed()) {
                return false;
            } else {
                //     Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (SQLException e) {
            if (showMessage)
                e.printStackTrace();
            return false;
        }
    }

}
