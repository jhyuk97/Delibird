package com.example.rider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User18 extends AppCompatActivity {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    TextView title;
    TextView pay;
    TextView UserName;
    TextView dat;
    TextView  textView8;
    TextView   textView5;


    RatingBar rat;
    RatingBar rat2;
    RatingBar rat3;
    RatingBar rat4;
    RatingBar rat5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user18);

        Intent intent = getIntent();
        int OrderNum = intent.getExtras().getInt("number");
        title = (TextView)findViewById(R.id.user18_name);
        pay = (TextView)findViewById(R.id.user18_pay);
        UserName = (TextView)findViewById(R.id.user18_UserName);
        dat = (TextView)findViewById(R.id.user18_comment);  //이거
        rat = (RatingBar)findViewById(R.id.ratingBar);
        rat2 = (RatingBar)findViewById(R.id.ratingBar2);
        rat3 = (RatingBar)findViewById(R.id.ratingBar3);
        rat4 = (RatingBar)findViewById(R.id.ratingBar4);
        rat5 = (RatingBar)findViewById(R.id.ratingBar5);
        textView8= (TextView)findViewById(R.id.textView8);
        textView5= (TextView)findViewById(R.id.textView5);


        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );

        papago papago = new papago();
        String language = pref.getString("language","");


        textView8.setText(papago.papago(textView8.getText().toString(),language));
        dat.setText(papago.papago(dat.getText().toString(),language));
        textView5.setText(papago.papago(textView5.getText().toString(),language));

        tryConnect(true);

        String sql = "select * from 리뷰,주문 where 리뷰.리뷰번호 = 주문.주문번호 and 리뷰번호 = " + OrderNum;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                title.setText(rs.getString("제목"));
                pay.setText(rs.getString("금액"));
                UserName.setText(rs.getString("사용자ID"));
                dat.setText(rs.getString("답변"));
                if(rs.getFloat("평점") > 4)
                    rat.setRating(rs.getFloat("평점"));
                else if(rs.getFloat("평점") <= 4 && rs.getFloat("평점") > 3)
                    rat2.setRating(rs.getFloat("평점"));
                else if(rs.getFloat("평점") <= 3 && rs.getFloat("평점") > 2)
                    rat3.setRating(rs.getFloat("평점"));
                else if(rs.getFloat("평점") <= 2 && rs.getFloat("평점") > 1)
                    rat4.setRating(rs.getFloat("평점"));
                else
                    rat5.setRating(rs.getFloat("평점"));
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

