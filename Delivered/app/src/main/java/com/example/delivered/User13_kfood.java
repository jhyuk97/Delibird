package com.example.delivered;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class User13_kfood extends AppCompatActivity {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    ListView lv;
    ArrayList<user13_data> data;
     TextView gksrnrdmatlr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user13_kfood);


        lv = (ListView)findViewById(R.id.user13_lv);
        gksrnrdmatlr= (TextView)findViewById(R.id.gksrnrdmatlr);


        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );

        papago papago = new papago();
        String language = pref.getString("language","");
        gksrnrdmatlr.setText(papago.papago("한국 음식 페이지",language));

        tryConnect(true);

        data = new ArrayList<user13_data>();

        String sql = "select * from 한식홍보";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                data.add(new user13_data(rs.getString("제목"), rs.getString("사진설명"),rs.getString("안드IMG"),rs.getString("홍보번호")));
            }

        }catch (Exception e){
            e.getMessage();
        }

        user13_adapter adapter = new user13_adapter(this,data);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),User14.class);
                String foodnum = data.get(position).getNum();

                intent.putExtra("foodnum", foodnum);
                startActivity(intent);
            }
        });
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