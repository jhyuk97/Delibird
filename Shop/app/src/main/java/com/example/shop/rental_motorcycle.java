package com.example.shop;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class rental_motorcycle extends AppCompatActivity {
    Button button1;
    Button button2;
    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.rental_motorcycle);
        setTitle("오토바이 대여 신청 ");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));

        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
       final String reiderID = pref.getString( "reiderID", "" );
        button1 = (Button) findViewById( R.id.otUP);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(tryConnect(true))
                    try {
                        sql = "update 대여 set 대여신청 ='대여신청합니다' ,대여여부='대여신청완료' where 라이더ID = '" + reiderID + "' " ;

                        stmt = conn.createStatement();
                        stmt.executeUpdate(sql);
                        finish();
                    } catch (SQLException e) {

                        e.printStackTrace();
                    }





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
                Toast.makeText( getApplicationContext(), "데이터베이스 연결 실패", Toast.LENGTH_SHORT ).show();
                return false;
            } else {
       //         Toast.makeText( getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT ).show();
                return true;
            }
        } catch (SQLException e) {
            if (showMessage)
                Toast.makeText( getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT ).show();
            e.printStackTrace();
            return false;
        }

    }


    }

