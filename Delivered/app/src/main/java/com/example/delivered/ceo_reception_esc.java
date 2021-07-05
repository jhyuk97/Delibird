package com.example.delivered;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class ceo_reception_esc extends AppCompatActivity {
    Button button1;
    Button button2;
    Button escb;

    TextView escMenu;

    TextView nmkum123; // 주문 번호
    TextView esccall_address;
    EditText dldb;


    private Connection conn = null;
    private ResultSet rs = null;
    ResultSet rs1 = null;
    private String sql;

    private Statement stmt = null;
    private PreparedStatement pstmt = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ceo_reception_esc);

        setTitle("주문 취소 ");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));

        escMenu = (TextView) findViewById( R.id.escMenu); //메뉴
        dldb = (EditText) findViewById( R.id.esccall); //주소 사유
        nmkum123 = (TextView) findViewById( R.id.nmkum123);
        esccall_address = (TextView)findViewById( R.id.esccall_address); //주소 주소

        escb = (Button)findViewById( R.id.escb); //주소 주소

        Intent intent = getIntent();
        int aaa =intent.getExtras().getInt( "num" );
        String ccc = intent.getExtras().getString( "moneys" );
        final int moneys = Integer.parseInt(ccc);


final int num= aaa;
final int fff =2000;

        escb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    sql=  "update 사용자 set 잔액 = (select 잔액 from 사용자 where ID =(select 사용자ID from 주문 where 주문번호 ="+num+")) + "+ moneys + " where ID =(select 사용자ID from 주문 where 주문번호 ="+num+")";
                    stmt = conn.createStatement();
                    stmt.executeUpdate( sql );

                    sql = "update 주문 set 현재상황 = '주문취소', 배달안내 ='"+ dldb.getText().toString() +"' where 주문번호 = " + num +" ";
                    stmt = conn.createStatement();
                    stmt.executeUpdate( sql );
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        if(tryConnect(true))
            try{
                sql= "select distinct a.주문번호, a.금액, a.배송지, a.요청사항, a.주문일, a.사용자ID ,STUFF(( SELECT ',' + d.메뉴명 FROM 메뉴 d, 주문한음식 e where d.메뉴번호 = e.메뉴번호 and e.주문번호 = a.주문번호 FOR XML PATH('') ),1,1,'') AS 메뉴 ,a.매장번호 from 주문 a,주문한음식 c,메뉴 b where a.주문번호 = c.주문번호 and c.메뉴번호 = b.메뉴번호 and a.매장번호 = b.매장번호 and a.주문번호 = " + num +" ";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {

                    nmkum123.setText(rs.getString("주문번호"));
                    escMenu.setText(rs.getString("메뉴"));

                    esccall_address.setText(rs.getString("배송지"));
                }
            }catch (Exception e){
                e.printStackTrace();
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
