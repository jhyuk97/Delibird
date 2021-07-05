package com.example.shop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class Rider_Main extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;

    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;


TextView ridermainname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.ridermain);

        ridermainname =(TextView)findViewById( R.id.ridermainname);

        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        String reiderID = pref.getString("reiderID","");


        button1 = (Button) findViewById( R.id.RiderTell);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), tell.class);
                startActivity(intent);

            }
        });
        button2 = (Button) findViewById( R.id.Current);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), current.class);
                startActivity(intent);

            }
        });


        if(tryConnect(true))
            // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

            try{
                sql = "select 이름 from 라이더 where ID = '"+ reiderID +"' ";

                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    ridermainname.setText(rs.getString("이름"));

                }
            }catch (Exception e){
                e.printStackTrace();
            }

    }




    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.order_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        //or switch문을 이용하면 될듯 하다.
//개인정보 수정
        if (id == R.id.datechange) {
            Intent settingIntent = new Intent(this, datechange.class);
            startActivity(settingIntent);
        }
//오토바이정보
        if (id == R.id.motorcycle) {
            Intent settingIntent = new Intent(this, motorcycle.class);
            startActivity(settingIntent);
        }

//급여조회

        if (id == R.id.money) {
            Intent settingIntent = new Intent(this, money.class);
            startActivity(settingIntent);
        }

//배달이력조회

        if (id == R.id.Riderreference) {
            Intent settingIntent = new Intent(this, Riderreference.class);
            startActivity(settingIntent);
        }

        if (id == R.id.cxchange) {
            Intent settingIntent = new Intent(this, Rider_cxchange.class);
            startActivity(settingIntent);
        }

        
        if (id == R.id.out) {
            finish();
        }

        return super.onOptionsItemSelected(item);
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
        //        Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
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


