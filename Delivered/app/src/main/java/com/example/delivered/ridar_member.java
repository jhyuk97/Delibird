package com.example.delivered;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ridar_member extends AppCompatActivity {
    Button riderSt;
    Button button2;
    TextView addr;

protected void onResume(){

    super.onResume();
    global data = (global)getApplication();
    String aaa = data.getAddress();
    addr.setText(data.getAddress());

}
EditText rider_signup_ID;  // 아이디
    Button reider_signup_ck; //중복확인
    EditText rider_signup_pw; //비밀번호
    EditText rider_signup_name; // 이름
    TextView  sig_ii; //상세주소


    EditText  sig_iii ; //일반 주소

    EditText  rider_signup_mo1; //은행이름
    EditText rider_signup_mo2;//  계좌이름

    EditText rider_signup_call; // 전번
    TextView rider_signup_wn0;
    EditText rider_signup_wn1 ; //앞주민
    EditText rider_signup_wn2 ; // 뒷주민
    private Connection conn = null;
    private int rs1;
    private ResultSet rs;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private Connection connection;
    private String wnals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.ridar_member);
        setTitle("라이더 회원가입");





        Intent intent = getIntent();
        addr = (TextView)findViewById( R.id.sig_ii); /*TextView선언*/


        rider_signup_ID = (EditText) findViewById( R.id.rider_signup_ID);
        rider_signup_pw = (EditText) findViewById( R.id.rider_signup_pw);
        rider_signup_name = (EditText) findViewById( R.id.rider_signup_name);


        rider_signup_wn1 = (EditText) findViewById( R.id.rider_signup_wn1); //앞주민
        rider_signup_wn0 = (TextView) findViewById( R.id.rider_signup_wn0);
        rider_signup_wn2 = (EditText) findViewById( R.id.rider_signup_wn2);  //뒷주민





      //  sig_ii = (TextView) findViewById(R.id.sig_ii); //상세주소

     //  SharedPreferences pref = getSharedPreferences("test",MODE_PRIVATE);
    //   String txt_address = pref.getString("txt_address","");
       // sig_ii.setText(addr);



        sig_iii = (EditText) findViewById( R.id.sig_iii); //주소

        rider_signup_call = (EditText) findViewById( R.id.rider_signup_call); //전화번호


        rider_signup_mo1 = (EditText) findViewById( R.id.rider_signup_mo1); //은형명
        rider_signup_mo2 = (EditText) findViewById( R.id.rider_signup_mo2); //계좌명






        button2 = (Button) findViewById( R.id.sig_tt); //상세주소
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchAddress.class);
                intent.putExtra("for",20);

                startActivity(intent);

            }
        });






        reider_signup_ck = (Button) findViewById( R.id.reider_signup_ck);
        reider_signup_ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (tryConnect(true) )
                {


                    try {
                        sql = "SELECT  ID FROM 라이더 WHERE ID = '" + rider_signup_ID.getText().toString() + "' ";
                        Statement stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            Toast.makeText(getApplicationContext(), " ID 중복 되었습니다       ", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "사용가능합니다      ", Toast.LENGTH_SHORT).show();




                    } catch (Exception e) {
                        e.printStackTrace();


                    }


                }






            }
        });


        riderSt = (Button) findViewById( R.id.riderSt);
        riderSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rider_signup_ID.getText().toString().length() == 0) {
                    rider_signup_ID.requestFocus();
                    Toast.makeText(getApplicationContext(), "아이디 를 입력하세요 ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (rider_signup_pw.getText().toString().length() == 0) {
                    rider_signup_pw.requestFocus();
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요 ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (rider_signup_name.getText().toString().length() == 0) {
                    rider_signup_name.requestFocus();
                    Toast.makeText(getApplicationContext(), "이름 을  입력하세요 ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (rider_signup_call.getText().toString().length() == 0) {
                    rider_signup_call.requestFocus();
                    Toast.makeText(getApplicationContext(), "전화번호 를  입력하세요 ", Toast.LENGTH_SHORT).show();
                    return;
                }


                wnals  =rider_signup_wn1.getText().toString()+ rider_signup_wn0 .getText().toString() +rider_signup_wn2.getText().toString();





                if (tryConnect(true))

                    try {

                        sql = "insert into 라이더 values ( '" + rider_signup_ID.getText().toString() + "','" + rider_signup_pw.getText().toString() + "','" + rider_signup_mo2.getText().toString() + "' ,'" + addr.getText().toString()  + "' " +
                                ",'" + wnals + "', '" + rider_signup_name.getText().toString() + "','" + rider_signup_mo1.getText().toString() + "' ,'" + sig_iii.getText().toString() + "'," + rider_signup_call.getText().toString() + " )";

                        sql = sql+"insert into 대여 (라이더ID,대여여부,대여신청,번호판,대여일) values ('" + rider_signup_ID.getText().toString() + "','X','X','X','X')";

                        Statement stmt = conn.createStatement();
                        rs1 = stmt.executeUpdate(sql);

                        Toast.makeText(getApplicationContext(), "회원가입 완료 되었습니다  ", Toast.LENGTH_SHORT).show();
                        finish();

                    } catch (Exception e) {
                        e.printStackTrace();


                    }
            }


        });


    }






    public boolean tryConnect(boolean showMessage) {
        try {
            app_loginDB connClass = new app_loginDB();
            conn = connClass.TestQuery();
            if (conn == null) {
                return false;
            } else if (conn.isClosed()) {
                Toast.makeText(getApplicationContext(), "데이터베이스 연결 실패", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                //         Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (SQLException e) {
            if (showMessage)
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }

    }




}
