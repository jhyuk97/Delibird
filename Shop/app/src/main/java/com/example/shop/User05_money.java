package com.example.shop;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User05_money extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    TextView moneychor;
    EditText EditText1;
    TextView moneyFFF;
    private Connection conn;
    private ResultSet rs;
    String sql;
    private Statement stmt;
    private PreparedStatement pstmt;

CheckBox user05_ph;
    CheckBox user05_dmsgod;
    CheckBox user05_anstkd;
    CheckBox user05_zkzkdh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.user05_money );

        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );

        //  Intent intent = getIntent();
        //      //  final String userID = intent.getStringExtra("userID");  //***************
        papago papago = new papago();

        TextView guswowksdor1  = (TextView)findViewById(R.id.guswowksdor1);
        TextView guswowksdor2  = (TextView)findViewById(R.id.guswowksdor2);

        user05_ph= (CheckBox)findViewById(R.id.user05_ph);
        user05_dmsgod= (CheckBox)findViewById(R.id.user05_dmsgod);
        user05_anstkd= (CheckBox)findViewById(R.id.user05_anstkd);
        user05_zkzkdh= (CheckBox)findViewById(R.id.user05_zkzkdh);

        TextView dlqfurgkrl  = (TextView)findViewById(R.id.dlqfurgkrl);


        String language = pref.getString("language","");

        guswowksdor1.setText(papago.papago("잔액",language));
        user05_dmsgod.setText(papago.papago(user05_dmsgod.getText().toString(),language));
        user05_ph.setText(papago.papago(user05_ph.getText().toString(),language));
        user05_anstkd.setText(papago.papago(user05_anstkd.getText().toString(),language));
;     user05_zkzkdh.setText(papago.papago(user05_zkzkdh.getText().toString(),language));

        dlqfurgkrl.setText(papago.papago(dlqfurgkrl.getText().toString(),language));


        final String userid = pref.getString( "id", "" );


        moneyFFF = (TextView) findViewById( R.id.moneyF );

        EditText1 = (EditText) findViewById( R.id.moneyF );
        button5 = (Button) findViewById( R.id.moneyFF );


        moneychor = (TextView) findViewById( R.id.moneychor );

        button1 = (Button) findViewById( R.id.money5 );
        button2 = (Button) findViewById( R.id.money3 );
        button3 = (Button) findViewById( R.id.money1 );
        button4 = (Button) findViewById( R.id.money50 );


        button1.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {


                String str1 = String.valueOf( 50000 );
                String str2 = moneychor.getText().toString();
                int num1 = Integer.parseInt( str1 );
                int num2 = Integer.parseInt( str2 );
                int hap = num1 + num2;


                if (tryConnect( true ))
              //      Toast.makeText( getApplicationContext(), "충전이 완료되었습니다 로그아웃 됩니다 다시 로그인 해주세요  ", Toast.LENGTH_SHORT ).show();
                try {
                    sql = "update 사용자 set 잔액 ='" + hap + "' where ID = '" + userid + "' ";
                    rs = stmt.executeQuery( sql );
                    while (rs.next()) {
                        moneychor.setText( rs.getString( "잔액" ) );
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
                // global data = (global)getApplication();

            }
        } );


        button2.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String str1 = String.valueOf( 30000 );
                String str2 = moneychor.getText().toString();
                int num1 = Integer.parseInt( str1 );
                int num2 = Integer.parseInt( str2 );
                int hap = num1 + num2;
                if (tryConnect( true ))
                //    Toast.makeText( getApplicationContext(), "충전이 완료되었습니다 로그아웃 됩니다 다시 로그인 해주세요  ", Toast.LENGTH_SHORT ).show();
                try {
                    sql = "update 사용자 set 잔액 ='" + hap + "' where ID = '" + userid + "' ";
                    rs = stmt.executeQuery( sql );
                    while (rs.next()) {
                        moneychor.setText( rs.getString( "잔액" ) );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
            }
        } );


        button3.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String str1 = String.valueOf( 10000 );
                String str2 = moneychor.getText().toString();
                int num1 = Integer.parseInt( str1 );
                int num2 = Integer.parseInt( str2 );
                int hap = num1 + num2;
                if (tryConnect( true ))
                 //   Toast.makeText( getApplicationContext(), "충전이 완료되었습니다 로그아웃 됩니다 다시 로그인 해주세요  ", Toast.LENGTH_SHORT ).show();
                try {
                    sql = "update 사용자 set 잔액 ='" + hap + "' where ID = '" + userid + "' ";
                    rs = stmt.executeQuery( sql );
                    while (rs.next()) {
                        moneychor.setText( rs.getString( "잔액" ) );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
            }
        } );


        button4.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String str1 = String.valueOf( 5000 );
                String str2 = moneychor.getText().toString();
                int num1 = Integer.parseInt( str1 );
                int num2 = Integer.parseInt( str2 );
                int hap = num1 + num2;
                if (tryConnect( true ))
                 //   Toast.makeText( getApplicationContext(), "충전이 완료되었습니다 로그아웃 됩니다 다시 로그인 해주세요  ", Toast.LENGTH_SHORT ).show();
                try {
                    sql = "update 사용자 set 잔액 ='" + hap + "' where ID = '" + userid + "' ";
                    rs = stmt.executeQuery( sql );
                    while (rs.next()) {
                        moneychor.setText( rs.getString( "잔액" ) );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
            }
        } );


        button5.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String str1 = EditText1.getText().toString();
                String str2 = moneychor.getText().toString();
                int num1 = Integer.parseInt( str1 );
                int num2 = Integer.parseInt( str2 );


                int hap = num1 + num2;
                if (tryConnect( true ))
                 //   Toast.makeText( getApplicationContext(), "충전이 완료되었습니다 로그아웃 됩니다 다시 로그인 해주세요  ", Toast.LENGTH_SHORT ).show();
                try {
                    sql = "update 사용자 set 잔액 ='" + hap + "' where ID = '" + userid + "' ";
                    rs = stmt.executeQuery( sql );
                    while (rs.next()) {
                        moneychor.setText( rs.getString( "잔액" ) );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();


            }
        } );


        if (tryConnect( true ))
            //  Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

            try {

                sql = "select 잔액 from 사용자 where ID = '" + userid + "' ";
                stmt = conn.createStatement();
                rs = stmt.executeQuery( sql );
                while (rs.next()) {

                    moneychor.setText( "" + rs.getString( "잔액" ) );

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
             //   Toast.makeText(getApplicationContext(),"데이터베이스 연결 실패",Toast.LENGTH_SHORT).show();
                return false;
            } else {
          //      Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
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