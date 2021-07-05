package com.example.delivered;

import android.content.Intent;
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


public class ceo_review_Answer extends AppCompatActivity {
    Button button1;
    Button button2;
    ImageView reviewImg;// 리뷰 사진
    TextView mknumber;// 매장번호
    TextView ReviewNumber;//리뷰번호
    TextView UserID; //유저아이디
    TextView ReviewName; //제목
    TextView Reviewday; //작성일
    TextView reviewcontent; //내용


    private Connection conn = null;
    private ResultSet rs= null ;

    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private Instant Glide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ceo_review_answer);
        Intent intent = getIntent();







       // reviewImg = (ImageView) findViewById(R.id.reviewImg);
      //  answer = (EditText)findViewById(R.id.review_answer);

        mknumber = (TextView)findViewById(R.id.mknumber);
        UserID = (TextView)findViewById(R.id.UserID);
        ReviewName = (TextView)findViewById(R.id.ReviewName);
        button2 = (Button) findViewById(R.id.review_ck);
        Reviewday = (TextView) findViewById(R.id.Reviewday);
        reviewcontent = (TextView)findViewById(R.id.reviewcontent);
        TextView ReviewNumber = findViewById(R.id.ReviewNumber);
        ImageView reviewImg = (ImageView)findViewById(R.id.reviewImg);


         final EditText answer = (EditText) findViewById(R.id.review_answer) ;


        final String number = intent.getExtras().getString("aaa");
        ReviewNumber.setText(number);


        button1 = (Button) findViewById(R.id.esc);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });






        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tryConnect(true))
                    finish();
                try {
                    sql = "update 리뷰 set 답변 ='" + answer.getText().toString() +"' where 리뷰번호 = " + number ;
                    rs = stmt.executeQuery(sql);

                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
        });






        if(tryConnect(true))
            Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

        try{
            sql = "select 사진,사용자ID,매장번호,제목,내용,등록일 from 리뷰 where 리뷰번호 = " + number;
            //sql = "select 리뷰번호,사용자ID,매장번호,제목,내용,등록일 from 리뷰";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {


                String str = rs.getString("사진");


             //   reviewImg.setImageBitmap(BitmapFactory.decodeFile(str));
                //Glide.with();
            //    reviewImg.setImageURI(Uri.fromFile(new File(rs.getString("사진"))));
                UserID.setText(rs.getString("사용자ID"));
                mknumber.setText(rs.getString("매장번호"));
                ReviewName.setText(rs.getString("제목"));
                reviewcontent.setText(rs.getString("내용"));
          //      URL url = new URL(rs.getString("사진"));
                Reviewday.setText(rs.getString("등록일"));
                answer.setText(rs.getString("답변"));


            //    answer.setText(rs.getString("답변"));
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

