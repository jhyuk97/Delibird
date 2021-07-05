package com.example.delivered;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class user24 extends AppCompatActivity {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    TextView title;
    TextView contents;
    TextView date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user24);

        Intent intent = getIntent();
        String number = intent.getStringExtra("noticeNumber");
        tryConnect(true);

        title = (TextView)findViewById(R.id.user24_title);
        date = (TextView)findViewById(R.id.user24_date);
        contents = (TextView)findViewById(R.id.user24_contents);

        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        papago papago = new papago();
        String language = pref.getString("language","");
        title.setText(papago.papago(title.getText().toString(),language));
        contents.setText(papago.papago(contents.getText().toString(),language));




        try{
            String sql = "select * from 공지사항 where 공지번호 = " + number;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                title.setText(rs.getString("제목"));
                date.setText(rs.getString("날짜"));
                contents.setText(rs.getString("내용"));
            }
        }catch (Exception e){
            e.getMessage();
        }
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
