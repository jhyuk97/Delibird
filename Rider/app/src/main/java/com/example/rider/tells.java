package com.example.rider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class tells extends AppCompatActivity {
    Button idyysad;
    Button button2;

    private Connection conn = null;
    private ResultSet rs = null;
    ResultSet rs1 = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    String reiderID;
    TextView abcd;
    TextView efg;
    TextView hijk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.tells);
        setTitle("배달 내용 ");

        Intent intent = getIntent(); /*데이터 수신*/



        final String clsd = intent.getExtras().getString("clsd");
        final   int scv = Integer.parseInt(clsd);


        abcd = (TextView) findViewById( R.id.abcd);
        efg = (TextView) findViewById( R.id.efg);
        hijk = (TextView) findViewById( R.id.hijk);


        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        reiderID = pref.getString( "reiderID", "" );

        idyysad = (Button) findViewById( R.id.idyysad);
        idyysad.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                try {

                    sql = "update 라이더호출  set 라이더ID ='" + reiderID + "' where 호출번호 =  " + scv + "   ";

                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();

                    sql ="update 주문 set 라이더ID ='" + reiderID + "' , 현재상황='음식점이동중'   where 주문번호  =  " + scv + " ";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();

                    sql = "  select * from 라이더호출,매장,주문,사용자 where 호출번호 = " + scv + "  and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호 and 주문.사용자ID =사용자.ID";
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery( sql );

                    while (rs.next()) {

                        Intent intent = new Intent( getApplicationContext(), ing.class );

                        intent.putExtra( "callnumber", rs.getInt( "호출번호" ) );

                        startActivity( intent );
                    }
                  //  finish();

                } catch (Exception e) {
                    e.getMessage();
                }


            }
        });





        if (tryConnect( true ))
            // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

            try {
                sql="  select * from 라이더호출,매장,주문,사용자 where 호출번호 =  " + scv + "  and 현재상황 = '조리중' and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호 and 주문.사용자ID =사용자.ID";

                stmt = conn.createStatement();
                rs = stmt.executeQuery( sql );
                while (rs.next()) {
                    abcd.setText( rs.getString( "매장이름" ) );
                    efg.setText( rs.getString( "매장주소" ) );
                    hijk.setText( rs.getString( "배송지" ) );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }






        //액션바 설정하기//
        //액션바 타이틀 변경하기

        //액션바 배경색 변경
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));
        //액션바 숨기기
        //hideActionBar();
    }
    //액션버튼 메뉴 액션바에 집어 넣기
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



