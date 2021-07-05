package com.example.rider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User16 extends AppCompatActivity {
    ListView lv;
    ResultSet rs = null;
    Connection conn = null;
    Statement stmt = null;
   TextView textView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user16);
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        String userid = pref.getString("id","");
        ArrayList<user16_data> data = new ArrayList<user16_data>();

        textView5 = (TextView) findViewById(R.id.textView5);

        papago papago = new papago();
        String language = pref.getString("language","");
        textView5.setText(papago.papago(textView5.getText().toString(),language));



        tryConnect(true);
        String sql = "select distinct a.금액, a.주문일 ,매장이름, a.주문번호, a.매장번호, STUFF(( SELECT ',' + d.메뉴명 FROM 메뉴 d, 주문한음식 e where d.메뉴번호 = e.메뉴번호 and e.주문번호 = a.주문번호 FOR XML PATH('') ),1,1,'') AS 메뉴 from 주문 a, 매장,주문한음식 where 매장.매장번호 = a.매장번호 and a.주문번호 = 주문한음식.주문번호 and 사용자ID = '" + userid + "' order by 주문번호 desc";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                data.add(new user16_data(rs.getString("매장이름"),(rs.getString("메뉴") + "\n" + rs.getString("주문일") + "\n" + rs.getString("금액")), rs.getString("매장번호"), rs.getString("주문번호")));
            }
        }catch (Exception e){
            e.getMessage();
        }

        user16_adapter adapter = new user16_adapter(data,this);

        lv = (ListView)findViewById(R.id.order_List);
        lv.setAdapter(adapter);

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