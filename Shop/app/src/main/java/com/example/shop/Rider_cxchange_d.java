package com.example.shop;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Rider_cxchange_d extends AppCompatActivity {

    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    TextView cxchangeDnumber; //급여번호
    TextView cxchangeDmo; //급액
    TextView cxchangeDday; // 날짜
    TextView cxchangeDtime; // 시간

    Button cxchangeDch; //환전하기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.rider_cxchange_d);
        Intent intent = getIntent();

       // final String mknumber = intent.getExtras().getString("mknumber");

        final String minho = intent.getExtras().getString("minho");
        final   int min = Integer.parseInt(minho);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));

        cxchangeDnumber = (TextView) findViewById( R.id.cxchangeDnumber);
        cxchangeDmo = (TextView)findViewById( R.id.cxchangeDmo);
        cxchangeDday = (TextView) findViewById( R.id.cxchangeDday);
        cxchangeDtime = (TextView)findViewById( R.id.cxchangeDtime);

        cxchangeDch = (Button)findViewById( R.id.cxchangeDch);



        cxchangeDch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                sql = "update 급여 set 환전 = 'O' where 급여번호 = " + min + " ";

                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);

                    Toast.makeText(getApplicationContext(),"환전이 완료었습니다",Toast.LENGTH_SHORT).show();

                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });


        if(tryConnect(true))
           // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

        try{
            sql="select *from 급여 where 급여번호 = " + min + " ";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cxchangeDnumber.setText(rs.getString("급여번호"));
                cxchangeDmo.setText(rs.getString("돈"));
                cxchangeDday.setText(rs.getString("날짜"));
                cxchangeDtime.setText(rs.getString("시간"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private boolean tryConnect(boolean showMessage) {
        try {
            app_loginDB connClass = new app_loginDB();
            conn = connClass.TestQuery();
            if (conn == null) {
                return false;
            } else if (conn.isClosed()) {
                Toast.makeText(getApplicationContext(),"데이터베이스 연결 실패",Toast.LENGTH_SHORT).show();
                return false;
            } else {
                Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (SQLException e) {
            if (showMessage)
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
    }
}
