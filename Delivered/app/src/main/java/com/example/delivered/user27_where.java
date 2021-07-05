package com.example.delivered;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;


public class user27_where extends AppCompatActivity {
    Button button1;
    Button button2;



    TextView user27_2; //문의번호
    TextView user27_4;//리뷰번호
    TextView user27_12; //유저아이디
    TextView user27_6; //제목
    TextView user27_8; //작성일
    TextView user27_10; //내용


    private Connection conn = null;
    private ResultSet rs= null ;

    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private Instant Glide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user27_where);
        final Intent intent = getIntent();




        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        final String userid = pref.getString("id","");




        user27_2 = (TextView)findViewById(R.id.user27_2);
        user27_4 = (TextView)findViewById(R.id.user27_4);
        user27_12 = (TextView)findViewById(R.id.user27_12);
        button2 = (Button) findViewById(R.id.user27_11);
        user27_6 = (TextView) findViewById(R.id.user27_6);
        user27_8 = (TextView)findViewById(R.id.user27_8);
        user27_10 =(TextView) findViewById(R.id.user27_10);





        final String number = intent.getExtras().getString("user27mknumber");

        final   int num = Integer.parseInt(number);


        button2 = (Button) findViewById(R.id.user27_11);
        button2.setOnClickListener(new View.OnClickListener() {
            private int rs1;

            @Override
            public void onClick(View v) {




                if(tryConnect(true))
                    //   Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

                    try{
                        sql = "DELETE FROM 문의 WHERE 문의번호 =" + num +" AND  작성자 ='"+userid+"' " ;


                        //sql = "select 리뷰번호,사용자ID,매장번호,제목,내용,등록일 from 리뷰";

                        Statement stmt = conn.createStatement();
                        rs1 = stmt.executeUpdate(sql);

                        Toast.makeText(getApplicationContext(),"삭제되었습니다",Toast.LENGTH_SHORT).show();
                        finish();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }







            }
        });










        if(tryConnect(true))
         //   Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

        try{
            sql = "select 문의번호,작성자,분류,제목,내용,답변 from 문의 where 문의번호 = " + num +" and 작성자 = '"+ userid +"' ";
            //sql = "select 리뷰번호,사용자ID,매장번호,제목,내용,등록일 from 리뷰";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {



                user27_2.setText(rs.getString("문의번호"));
                user27_4.setText(rs.getString("작성자"));
                user27_12.setText(rs.getString("분류"));
                user27_6.setText(rs.getString("제목"));
                user27_8.setText(rs.getString("내용"));
                user27_10.setText(rs.getString("답변"));



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
           //     Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
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

