package com.example.delivered;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class user16_adapter extends BaseAdapter {
    ArrayList<user16_data> data;
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    TextView user16_name;
    TextView user16_detail;
    Button button;
    Statement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    Button goReview;


    public user16_adapter(ArrayList<user16_data> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
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
        final View view = mLayoutInflater.inflate(R.layout.user16_acc,null);
        final int pos = position;
        tryConnect(true);
        user16_name = (TextView)view.findViewById(R.id.user16_name);
        user16_name.setText(data.get(position).getUser16_name());
        user16_detail = (TextView)view.findViewById(R.id.user16_detail);
        user16_detail.setText(data.get(position).getUser16_detail());
        goReview = (Button)view.findViewById(R.id.user16_goreivew);

        button= (Button)view.findViewById(R.id.user16_button);

        SharedPreferences pref = mContext.getSharedPreferences("pref", MODE_PRIVATE);
        final String ID = pref.getString("id","");
        papago papago = new papago();
        String language = pref.getString("language","");
        user16_detail.setText(papago.papago(user16_detail.getText().toString(),language));

        button.setText(papago.papago(button.getText().toString(),language));



        try{
            String sql = "select 현재상황 from 주문 where 주문번호 = " + data.get(position).getNumber();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                String check = rs.getString("현재상황");
                if(check.equals("조리중") || check.equals("리뷰작성완료") || check.equals("음식점이동중") || check.equals("주문취소"))
                    goReview.setVisibility(View.GONE);
                if(check.equals("배달중")){
                    goReview.setText(papago.papago("위치 보기",language));
                    goReview.setVisibility(View.VISIBLE);
                }
                if(check.equals("배달완료")){
                    goReview.setText(papago.papago("리뷰 작성",language));
                    goReview.setVisibility(View.VISIBLE);
                }
                if(check.equals("주문옴")){
                    goReview.setText(papago.papago("주문취소",language));
                    goReview.setVisibility(View.VISIBLE);
                }
            }
        }catch (Exception e){
            e.getMessage();
        }



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql = "select  매장전화번호 from 매장 where 매장번호 = " + data.get(pos).getData();
                String number = "";
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


        goReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql = "select 현재상황 from 주문 where 주문번호 = " + data.get(pos).getNumber();
                try{
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql);
                    while (rs.next()){
                        if(rs.getString("현재상황").equals("배달완료")) {
                            Intent intent = new Intent(mContext, user26_reviewwrite.class);
                            intent.putExtra("OrderNum", data.get(pos).getNumber());
                            mContext.startActivity(intent);
                        }
                        if(rs.getString("현재상황").equals("배달중")){
                            Intent intent = new Intent(mContext, user25.class);
                            intent.putExtra("OrderNum",data.get(pos).getNumber());
                            mContext.startActivity(intent);
                        }
                        if(rs.getString("현재상황").equals("리뷰작성완료")){
                            Intent intent = new Intent(mContext, User18.class);
                            intent.putExtra("OrderNum", data.get(pos).getNumber());
                            mContext.startActivity(intent);
                        }
                        if(rs.getString("현재상황").equals("주문옴")){
                            sql = "update 주문 set 현재상황 = '주문취소' where 주문번호 = " + data.get(pos).getNumber();
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.executeUpdate();
                            String[] aaa = data.get(pos).getUser16_detail().split("\n");
                            sql = "update 사용자 set 잔액 = 잔액 + " + aaa[2] + " where ID = '" + ID +"'";
                            pstmt = conn.prepareStatement(sql);
                            pstmt.executeUpdate();
                            Intent intent = new Intent(mContext, User16.class);
                            mContext.startActivity(intent);
                            ((Activity)mContext).finish();
                        }
                    }
                }catch (Exception e){
                    e.getMessage();
                }
            }
        });

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
