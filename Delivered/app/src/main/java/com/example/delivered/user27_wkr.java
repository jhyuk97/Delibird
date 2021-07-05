package com.example.delivered;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class user27_wkr extends AppCompatActivity {

    Button app_inquiryok;
    TextView snrn;

    EditText app_inquiry_ed1;
    EditText app_inquiry_ed2;
    EditText app_inquiry_ed3;



    private Connection conn = null;
    private int rs1;
    private ResultSet rs;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.user27_wkr );




        app_inquiry_ed1 = (EditText) findViewById(R.id.app_inquiry_ed1);
        app_inquiry_ed2 = (EditText) findViewById(R.id.app_inquiry_ed2);
        app_inquiry_ed3 = (EditText) findViewById(R.id.app_inquiry_ed3);



        app_inquiryok = (Button) findViewById(R.id.app_inquiryok);






        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        final String userid = pref.getString( "id", "" );

        app_inquiryok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (tryConnect(true))

                    try {
                        sql = "insert into 문의 values ( NEXT VALUE  FOR 문의번호_seq ,'" + userid + "','" + app_inquiry_ed1.getText().toString() + "' ,'" + app_inquiry_ed2.getText().toString() + "','" + app_inquiry_ed3.getText().toString() + "','X','X')";
                        Statement stmt = conn.createStatement();
                        rs1 = stmt.executeUpdate(sql);

                   //     Toast.makeText(getApplicationContext(), "작성이 완료되었습니다   ", Toast.LENGTH_SHORT).show();
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