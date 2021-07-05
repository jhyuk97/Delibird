package com.example.shop;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User20 extends AppCompatActivity {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    TextView name;
    Button update;
    Button cancel;
    EditText ogpw;
    EditText newPw;
    EditText ChPw;
    EditText firstPh;
    EditText secondPh;
    EditText endPh;
    EditText Email;
    Spinner spinner;



    TextView TextView17;
    TextView TextView18;
    TextView rodlswjdqhtnwjd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user20);

        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        String userid = pref.getString("id","");
        ogpw = (EditText)findViewById(R.id.OgPw);
        newPw = (EditText)findViewById(R.id.ChanPw);
        ChPw = (EditText)findViewById(R.id.CheckPw);
        firstPh = (EditText)findViewById(R.id.FrPh);
        secondPh = (EditText)findViewById(R.id.SePh);
        endPh = (EditText)findViewById(R.id.EndPh);
        Email = (EditText)findViewById(R.id.ReEmail);
        name = (TextView)findViewById(R.id.subID);
        spinner = (Spinner)findViewById(R.id.user20_spinner);
        update = (Button)findViewById(R.id.ChangeClear);
        cancel = (Button)findViewById(R.id.user20_cancel);

        TextView17=(TextView)findViewById(R.id.textView17);
        TextView18=(TextView)findViewById(R.id.textView18);
        rodlswjdqhtnwjd=(TextView)findViewById(R.id.rodlswjdqhtnwjd);
        papago papago = new papago();
        String language = pref.getString("language","");

        TextView17.setText(papago.papago(TextView17.getText().toString(),language));
        TextView18.setText(papago.papago(TextView18.getText().toString(),language));
        update.setText(papago.papago(update.getText().toString(),language));
        cancel.setText(papago.papago(cancel.getText().toString(),language));

        rodlswjdqhtnwjd.setText(papago.papago(rodlswjdqhtnwjd.getText().toString(),language));


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = newPw.getText().toString();
                String b = ChPw.getText().toString();

                if(ogpw.getText().toString().equals(c)){
                    newPw.setText("");
                    ChPw.setText("");
                    newPw.requestFocus();
                    Toast.makeText(getApplicationContext(),"이전비밀번호와 일치합니다",Toast.LENGTH_SHORT).show();
                }
                if(c.equals(b) && !c.equals(ogpw.getText().toString())) {
                    String sql = "update 사용자 set PW = '" + newPw.getText() + "', 전화번호 = "+ firstPh.getText() + secondPh.getText() + endPh.getText() +", 이메일 = '"+ Email.getText() +"', 언어 = '"+ spinner.getSelectedItem() +"' where ID = '" + name.getText() +"'";
                    try{
                        pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();
                        Toast.makeText(getApplicationContext(),"개인정보가 변경되었습니다.",Toast.LENGTH_SHORT).show();
                        finish();
                    }catch (Exception e){
                        e.getMessage();
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tryConnect(true);
        try{
            String sql = "select * from 사용자 where ID = '" + userid +"'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                name.setText(rs.getString("ID"));
            }
        }catch (Exception e){
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
                Toast.makeText(getApplicationContext(),"데이터베이스 연결 실패",Toast.LENGTH_SHORT).show();
                return false;
            } else {
                //     Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
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
