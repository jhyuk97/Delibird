package com.example.delivered;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User17 extends AppCompatActivity {
    CheckBox all;
    CheckBox order;
    CheckBox dat;
    CheckBox dom;
    Button check;
    Button cancel;
    Spinner spinner;
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
   TextView editText2;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user17);
        setTitle("환경설정");
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        final String userid = pref.getString("id","");

        spinner = (Spinner)findViewById(R.id.user17_spinner);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.language, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        order = (CheckBox)findViewById(R.id.checkBox6);
        dat = (CheckBox)findViewById(R.id.checkBox5);
        dom = (CheckBox)findViewById(R.id.checkBox4);
        check = (Button)findViewById(R.id.button3);
        all = (CheckBox)findViewById(R.id.checkBox7);
        cancel = (Button)findViewById(R.id.button4);
        editText2 = (TextView) findViewById(R.id.editText2);


        papago papago = new papago();
        String language = pref.getString("language","");
        order.setText(papago.papago(order.getText().toString(),language));
        dat.setText(papago.papago(dat.getText().toString(),language));
        dom.setText(papago.papago(dom.getText().toString(),language));
        check.setText(papago.papago(check.getText().toString(),language));
        all.setText(papago.papago(all.getText().toString(),language));
        cancel.setText(papago.papago(cancel.getText().toString(),language));
        editText2.setText(papago.papago(cancel.getText().toString(),language));



        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(all.isChecked()){
                    order.setChecked(true);
                    dat.setChecked(true);
                    dom.setChecked(true);
                    order.setEnabled(false);
                    dat.setEnabled(false);
                    dom.setEnabled(false);
                }
                else{
                    order.setEnabled(true);
                    dat.setEnabled(true);
                    dom.setEnabled(true);
                }
            }
        });



        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String order1;
                String dat1;
                String dom1;
                String lang = spinner.getSelectedItem().toString();
                String Lng = "";
                if(lang.equals("English")){
                    Lng = "english";
                }else if(lang.equals("日本語")){
                    Lng = "japanese";
                }else if(lang.equals("汉语")){
                    Lng = "chinese";
                }else if(lang.equals("한국어")){
                    Lng = "korean";
                }
                if(order.isChecked() == true) order1 = "true";
                else order1 = "false";

                if(dat.isChecked() == true) dat1 = "true";
                else dat1 = "false";

                if(dom.isChecked() == true) dom1 = "true";
                else  dom1 = "false";

                String sql = "update 사용자 set 주문알림 = '" + order1 + "', 리뷰댓글알림 = '" + dat1 + "', 리뷰도움알림 = '" + dom1 + "', 언어 = '" + lang + "' where ID = '" + userid + "'";
                tryConnect(true);
                try{
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                }catch (Exception e){
                    e.getMessage();
                }
                SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("language",Lng);
                editor.commit();

                Intent intent = new Intent(User17.this, user05_Main.class);
                startActivity(intent);
                finish();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean tryConnect(boolean showMessage) {
        try {
            app_loginDB connClass = new app_loginDB();
            conn = connClass.TestQuery();
            if (conn == null) {
                return false;
            } else if (conn.isClosed()) {
                return false;
            } else {
                //     Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (SQLException e) {
            if (showMessage)
                e.printStackTrace();
            return false;
        }
    }

}