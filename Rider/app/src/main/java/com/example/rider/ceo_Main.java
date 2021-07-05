package com.example.rider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ceo_Main extends AppCompatActivity {

    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;

    private DrawerLayout mDrawerLayout;
    private Context context = this;



    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button btn_Get;
    ListView LV_Data;
    TextView rid;
    TextView ceoname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.ceo_main );

        rid = (TextView) findViewById( R.id.risad );


        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        final String ceoID = pref.getString( "ceoID", "" );
        ceoname = (TextView) findViewById( R.id.ceoname );

        button4 = (Button) findViewById( R.id.ceo_review );
        button4.setOnClickListener( new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent intent = new Intent( getApplicationContext(), ceo_review.class );

                startActivity( intent );
            }
        } );

        button2 = (Button) findViewById( R.id.btn_Get );
        button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent( getApplicationContext(), ceo_reception_check.class );

                startActivity( intent );
            }
        } );

        if (tryConnect( true ))
            // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

            try {
                sql = "select 이름 from 점주 where ID = '" + ceoID + "' ";

                stmt = conn.createStatement();
                rs = stmt.executeQuery( sql );
                while (rs.next()) {
                    ceoname.setText( rs.getString( "이름" ) );

                }
            } catch (Exception e) {
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
