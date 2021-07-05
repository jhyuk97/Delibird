package com.example.shop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class app_signup extends AppCompatActivity {
    Button sig;
    Button sig_gg;
    EditText id;
    EditText pw;
    EditText name;
    EditText call;


    private Connection conn = null;
    private int rs1;
    private ResultSet rs;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_signup);


        id = (EditText) findViewById(R.id.user_signup_ID);
        pw = (EditText) findViewById(R.id.user_signup_pw);
        name = (EditText) findViewById(R.id.user_signup_name);
        call = (EditText) findViewById(R.id.user_signup_call);

        sig_gg = (Button) findViewById(R.id.sig_gg);
        sig_gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (tryConnect(true) )
                {


                    try {
                        sql = "SELECT  ID FROM 사용자 WHERE ID = '" + id.getText().toString() + "' ";
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





                sig = (Button) findViewById(R.id.sig);
                sig.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (id.getText().toString().length() == 0) {
                            pw.requestFocus();
                            Toast.makeText(getApplicationContext(), "아이디 를 입력하세요 ", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (pw.getText().toString().length() == 0) {
                            pw.requestFocus();
                            Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요 ", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (name.getText().toString().length() == 0) {
                            pw.requestFocus();
                            Toast.makeText(getApplicationContext(), "이름 을  입력하세요 ", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (call.getText().toString().length() == 0) {
                            pw.requestFocus();
                            Toast.makeText(getApplicationContext(), "전화번호 를  입력하세요 ", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        if (tryConnect(true))

                            try {
                                sql = "insert into 사용자 values ( '" + id.getText().toString() + "','" + pw.getText().toString() + "','" + name.getText().toString() + "' ,'" + call.getText().toString() + "'," + 0 +" ,'?','?','0','0','true','true','true','')";
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
