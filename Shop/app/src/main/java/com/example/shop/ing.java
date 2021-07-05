package com.example.shop;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class ing extends AppCompatActivity {
    Button button1;
    Button button2;
    TextView  nacs; // 라이더 이름

    TextView  mknamerer;// 음식점 이름
    TextView  cccccc; // 음식점 위치
    TextView ddasdas; //배달 목적지
    TextView ddassddas;
    TextView  cvx ; //고객 전화번호
   Button foodsave;

    Button   coolsave;
    private Connection conn = null;
    private ResultSet rs = null;
    ResultSet rs1 = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;



  TextView mjghdfsdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.ing);
        setTitle("배달 현황 ");


       // nacs = (TextView)findViewById(R.id.nacs); /*TextView선언*/
        mknamerer = (TextView)findViewById( R.id.mknamerer); /*TextView선언*/
        cccccc = (TextView)findViewById( R.id.cccccc); /*TextView선언*/
        ddasdas = (TextView)findViewById( R.id.ddasdas); /*TextView선언*/
        cvx = (TextView)findViewById( R.id.cvx); /*TextView선언*/

        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        final String reiderID = pref.getString( "reiderID", "" );


        foodsave= (Button) findViewById( R.id.foodsave);

        mjghdfsdf = (TextView)findViewById( R.id.mjghdfsdf); /*TextView선언*/
        Intent intent = getIntent(); /*데이터 수신*/
        final String callnumber = intent.getStringExtra("callnumber"); /*int형*/
        mjghdfsdf.setText(callnumber);

        button1 = (Button) findViewById( R.id.mapfood);
        ddassddas= (TextView)findViewById( R.id.ddassddas);



        coolsave= (Button) findViewById( R.id.coolsave);


        foodsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql="select *fom 주문,사용자";
                try {

                    sql = "update 주문  set 현재상황 ='배달중' where 주문번호 = " +callnumber +"  ";

                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();
                    Toast.makeText(getApplicationContext(),"배달을 시작합니다 ",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.getMessage();
                }


            }
        });
        final String Date = new SimpleDateFormat("yyy-MM-dd").format(new java.util.Date());
        final String Time = new SimpleDateFormat("HH:mm:ss").format( Calendar.getInstance( TimeZone.getTimeZone("Asia/Seoul")).getTime());
final int i =2000;
        coolsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    sql = "update 주문  set 현재상황 ='배달완료' where 주문번호 = " +callnumber +"  ";

                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();



                    sql = "insert into 급여 values (NEXT VALUE FOR 급여_seq , '"+reiderID+"' ," +i+ " ,'" + Date + "','X','"+ Time +"')";
                    stmt.executeUpdate(sql);

                    Intent intent = new Intent(getApplicationContext(), tell.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {
                    e.getMessage();
                }


            }
        });




        button1 = (Button) findViewById( R.id.mapfood);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tryConnect( true ))
                    // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

                    try {
                        sql="  select * from 라이더호출,매장,주문,사용자 where 호출번호 = "+ callnumber +" and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호 and 주문.사용자ID =사용자.ID";

                        stmt = conn.createStatement();
                        rs = stmt.executeQuery( sql );
                        while (rs.next()) {

                            String aaaa = rs.getString("호출번호");

                            Intent intent = new Intent(getApplicationContext(), mapfood.class);

                            intent.putExtra( "callnumber2", rs.getString( "호출번호" ) );


                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }



            }
        });

        button2 = (Button) findViewById( R.id.maphome);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tryConnect( true ))
                    // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

                    try {
                        sql="  select * from 라이더호출,매장,주문,사용자 where 호출번호 = "+ callnumber +" and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호 and 주문.사용자ID =사용자.ID";

                        stmt = conn.createStatement();
                        rs = stmt.executeQuery( sql );
                        while (rs.next()) {

                            String aaaa = rs.getString("호출번호");

                            Intent intent = new Intent(getApplicationContext(), maphome.class);

                            intent.putExtra( "callnumber2", rs.getString( "호출번호" ) );


                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }


        });


        if (tryConnect( true ))
            // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

            try {
                sql=" select 매장이름, 매장주소, 배송지, 주문.상세주소 as 상세주소, 전화번호 from 라이더호출,매장,주문,사용자 where 호출번호 = "+ callnumber +" and (현재상황 ='음식점이동중' or 현재상황 = '배달중') and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호 and 주문.사용자ID =사용자.ID";

                stmt = conn.createStatement();
                rs = stmt.executeQuery( sql );
                while (rs.next()) {
                    mknamerer.setText( rs.getString( "매장이름" ) );
                    cccccc.setText( rs.getString( "매장주소" ) );
                    ddasdas.setText( rs.getString( "배송지" ) );
                    ddassddas.setText( rs.getString( "상세주소" ) );
                    cvx.setText( rs.getString( "전화번호" ) );
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