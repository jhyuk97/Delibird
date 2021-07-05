package com.example.shop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User14 extends AppCompatActivity {
    TextView user14_name;
    TextView det;
    ImageView user14_img;
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user14);
        user14_name = (TextView)findViewById(R.id.user14_name);
        user14_img = (ImageView)findViewById(R.id.user14_img);
        det = (TextView)findViewById(R.id.user14_det);
        Intent intent = getIntent();
        String foodnum = intent.getStringExtra("foodnum");

        String sql = "select * from 한식홍보 where 홍보번호 = " + foodnum;

        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );









        tryConnect(true);
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                user14_name.setText(rs.getString("제목"));
                Bitmap bitmap = BitmapFactory.decodeFile(rs.getString("IMG"));
                user14_img.setImageBitmap(bitmap);
                det.setText(rs.getString("사진설명"));
            }
        }catch (Exception e){
            e.getMessage();
        }

        papago papago = new papago();
        String language = pref.getString("language","");
        user14_name.setText(papago.papago(user14_name.getText().toString(),language));

        det.setText(papago.papago(det.getText().toString(),language));




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