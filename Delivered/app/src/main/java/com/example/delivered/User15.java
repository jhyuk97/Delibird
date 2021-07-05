package com.example.delivered;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User15 extends AppCompatActivity {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    ArrayList<user15_data> data;
    ListView lv;
    user15_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user15);

        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        String userid = pref.getString("id","");
        data = new ArrayList<user15_data>();
        lv = (ListView)findViewById(R.id.favor_list);

        tryConnect(true);

        String sql = "select * from 즐겨찾기,매장 where 사용자ID = '" + userid + "' and 즐겨찾기.매장번호 = 매장.매장번호";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                data.add(new user15_data(rs.getString("매장이름"),rs.getString("매장번호"),rs.getString("매장번호")));
            }
        }catch (Exception e){
            e.getMessage();
        }

        adapter = new user15_adapter(data,this, userid);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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