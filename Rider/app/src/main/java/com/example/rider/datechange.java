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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class datechange extends AppCompatActivity {

    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;


    TextView rider_my_id; //아이디
    TextView rider_my_pw; // 비밀번호

    TextView rider_my_name; // 이름름
    TextView rider_my_gg ; // 도로명주소

    Button rider_my_ggg ; //주소 찾기 버튼

    EditText rider_my_gggg; // 상세주소

    EditText rider_my_mo; // 은행 이름


    EditText  rider_my_moy ; //계좌
    EditText  rider_my_call ; // 전번번

    Button   rider_my_ck ; // 수정하기




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.datechange );
        setTitle( "정보수정" );




        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        final String reiderID = pref.getString("reiderID","");

        rider_my_id = (TextView) findViewById( R.id.rider_my_id);
        rider_my_pw = (TextView) findViewById( R.id.rider_my_pw);
        rider_my_name = (TextView) findViewById( R.id.rider_my_name);
        rider_my_gg = (TextView) findViewById( R.id.rider_my_gg);
        rider_my_ggg = (Button) findViewById( R.id.rider_my_ggg);
        rider_my_gggg = (EditText) findViewById( R.id.rider_my_gggg);
        rider_my_mo = (EditText) findViewById( R.id.rider_my_mo);
        rider_my_moy = (EditText) findViewById( R.id.rider_my_moy);
        rider_my_call = (EditText) findViewById( R.id.rider_my_call);
        rider_my_ck = (Button) findViewById( R.id.rider_my_ck);



        //액션바 설정하기//
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));


        if(tryConnect(true))
            // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();
            sql = "select ID,PW,도로명주소,상세주소,이름,은행이름,계좌,전화번호 from 라이더 where ID = '" + reiderID + "'  ";
            try{

                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    rider_my_id.setText(rs.getString("ID"));
                    rider_my_pw.setText(rs.getString("PW"));
                    rider_my_name.setText(rs.getString("이름"));
                    rider_my_gg.setText(rs.getString("도로명주소"));
                    rider_my_gggg.setText(rs.getString("상세주소"));
                    rider_my_mo.setText(rs.getString("은행이름"));
                    rider_my_moy.setText(rs.getString("계좌"));
                    rider_my_call .setText(rs.getString("전화번호"));
                }
            }catch (Exception e){
                e.printStackTrace();
            }



        rider_my_ggg = (Button) findViewById( R.id.rider_my_ggg );
        rider_my_ggg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SearchAddress.class);
                intent.putExtra("for",100);

                startActivity(intent);

                finish();

            }
        } );


        rider_my_ck = (Button) findViewById( R.id.rider_my_ck );
        rider_my_ck.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tryConnect(true)) {
                    try {

                        sql = "update 라이더 set PW= '" + rider_my_pw.getText().toString() +"', 이름 = '" + rider_my_name.getText().toString() + "', 도로명주소=  '" + rider_my_gg.getText().toString() + "',상세주소='" + rider_my_gggg.getText().toString() + "', 은행이름 = '" + rider_my_mo.getText().toString() + "' , 계좌 ='" + rider_my_moy.getText().toString() + ", 전화번호 ='" + rider_my_call.getText().toString() + "' where ID = '" + reiderID + "' ";
                        stmt = conn.createStatement();
                        stmt.executeUpdate( sql );

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Toast.makeText( getApplicationContext(), "수정이완료되었습니다 .", Toast.LENGTH_LONG ).show();
                    finish();

                }

            }
        } );



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
        //        Toast.makeText( getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT ).show();
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