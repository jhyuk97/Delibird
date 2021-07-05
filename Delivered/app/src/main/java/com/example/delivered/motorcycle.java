package com.example.delivered;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class motorcycle extends AppCompatActivity {
    Button button1;
    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;

    TextView reot; //기종
    EditText retr; //사고유무
    TextView rmm;  //번호판
    EditText riqnl; //부가 설명

    TextView riss;

    Button motosave; //저장하기
    Button riderC;   //대여하기

    String reiderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.motorcycle );
        setTitle( "오토바이정보  " );


        reot = (TextView) findViewById( R.id.reot );
        retr = (EditText) findViewById( R.id.retr );
        rmm = (TextView) findViewById( R.id.rmm );

        riqnl = (EditText) findViewById( R.id.riqnl );

        riss = (TextView) findViewById( R.id.riss );

        motosave = (Button) findViewById( R.id.motosave );
        riderC = (Button) findViewById( R.id.riderC );



       SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
       reiderID = pref.getString( "reiderID", "" );


        if (tryConnect( true ))
            // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

            try {
                sql = "select 대여여부 from 대여 where 라이더ID = '" + reiderID + "' ";
                stmt = conn.createStatement();
                rs = stmt.executeQuery( sql );
                while (rs.next()) {
                    riss.setText( rs.getString( "대여여부" ) );


                }
            } catch (Exception e) {
                e.printStackTrace();
            }




        motosave = (Button) findViewById( R.id.motosave );
        motosave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tryConnect( true )) {

                    try {
                        sql = "update 오토바이 set 부가설명 = '" + riqnl.getText().toString() + "' , 사고유무 = '" + retr.getText().toString() + "'  where 라이더id =  '" + reiderID + "'  ";
                        stmt = conn.createStatement();
                        stmt.executeUpdate( sql );
                        finish();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }

            }

        } );





        riderC = (Button) findViewById( R.id.riderC );
        riderC.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TestQuery();

            }

        } );


        if (tryConnect( true ))
            // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

            try {
                sql = "select 번호판,사고유무,기종,부가설명 from 오토바이 where 라이더id = '" + reiderID + "' ";
                stmt = conn.createStatement();
                rs = stmt.executeQuery( sql );
                while (rs.next()) {
                    reot.setText( rs.getString( "기종" ) );
                    retr.setText( rs.getString( "사고유무" ) );
                    rmm.setText( rs.getString( "번호판" ) );
                    riqnl.setText( rs.getString( "부가설명" ) );

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


//        button1 = (Button) findViewById( R.id.riderC );
//        button1.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent( getApplicationContext(), rental_motorcycle.class );
//                startActivity( intent );
//            }
//        } );

        //액션바 설정하기//

        //액션바 배경색 변경
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));
        //액션바 숨기기
        //hideActionBar();
    }

    //액션버튼 메뉴 액션바에 집어 넣기
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.order_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //or switch문을 이용하면 될듯 하다.
//개인정보 수정
        if (id == R.id.datechange) {
            Intent settingIntent = new Intent( this, datechange.class );
            startActivity( settingIntent );
        }
//오토바이정보
        if (id == R.id.motorcycle) {
            Intent settingIntent = new Intent( this, motorcycle.class );
            startActivity( settingIntent );
        }

//급여조회

        if (id == R.id.money) {
            Intent settingIntent = new Intent( this, money.class );
            startActivity( settingIntent );
        }

//배달이력조회

        if (id == R.id.Riderreference) {
            Intent settingIntent = new Intent( this, Riderreference.class );
            startActivity( settingIntent );
        }


        if (id == R.id.out) {
            finish();
        }

        return super.onOptionsItemSelected( item );
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








    public void TestQuery() {
        if (tryConnect( true )) {
            try {
                {

                    sql = "select  대여여부,대여신청 from 대여 where 라이더ID ='" + reiderID + "' and 대여여부 = 'X'  ";
                    Statement stmt = conn.createStatement();
                    rs = stmt.executeQuery( sql );
                    if (rs.next()) {
                        Intent intent = new Intent( getApplicationContext(), rental_motorcycle.class );
                        startActivity( intent );
                        finish();
                    }
                }













            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }







}