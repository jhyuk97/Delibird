package com.example.rider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class app_login extends AppCompatActivity {
    private Connection conn = null;
    private String sql;
    private ResultSet rs;
    static Context mContext;
    Button button1;
    Button button2;
    Button button3;
    CheckBox user;
    CheckBox shop;
    CheckBox rider;
    EditText ID;
    EditText PW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_login);
        papago papago1 = new papago();
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
    //    String userid = pref.getString("id","");


//        if(userid != null){
//            Intent intent = new Intent(getApplicationContext(), user05_Main.class);
//            startActivity(intent);
//        }


        if(tryConnect(true))
           // Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();

        user = (CheckBox)findViewById(R.id.checkBox1234);
        user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                shop.setChecked(false);
                rider.setChecked(false);
            }
        });
        shop = (CheckBox) findViewById(R.id.checkBox2);
        shop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                user.setChecked(false);
                rider.setChecked(false);
            }
        });
        rider = (CheckBox) findViewById(R.id.checkBox3);
        rider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                user.setChecked(false);
                shop.setChecked(false);
            }
        });
        ID = (EditText) findViewById(R.id.ID);
        PW = (EditText) findViewById(R.id.PW);
        button1 = (Button) findViewById(R.id.btn_login);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestQuery();
            }
        });
        button2 = (Button) findViewById(R.id.btn_signup);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), app_choice  .class);
                startActivity(intent);

            }
        });
        button3 = (Button) findViewById(R.id.btn_find_id_passwd);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), app_find_id_passwd.class);
                startActivity(intent);
            }
        });

        getHashKey(this);

        String language = pref.getString("language","");
        if(language.equals("korean")){}
        else if(language.equals("english")){
            user.setText(papago1.papago(user.getText().toString(),language));
            shop.setText(papago1.papago(shop.getText().toString(),language));
            rider.setText(papago1.papago(rider.getText().toString(),language));
            button1.setText(papago1.papago(button1.getText().toString(),language));
            button2.setText(papago1.papago(button2.getText().toString(),language));
            button3.setText(papago1.papago(button3.getText().toString(),language));
        }
        else if(language.equals("japanese")){
            user.setText(papago1.papago(user.getText().toString(),language));
            shop.setText(papago1.papago(shop.getText().toString(),language));
            rider.setText(papago1.papago(rider.getText().toString(),language));
            button1.setText(papago1.papago(button1.getText().toString(),language));
            button2.setText(papago1.papago(button2.getText().toString(),language));
            button3.setText(papago1.papago(button3.getText().toString(),language));
        }
        else if(language.equals("chinese")){
            user.setText(papago1.papago(user.getText().toString(),language));
            shop.setText(papago1.papago(shop.getText().toString(),language));
            rider.setText(papago1.papago(rider.getText().toString(),language));
            button1.setText(papago1.papago(button1.getText().toString(),language));
            button2.setText(papago1.papago(button2.getText().toString(),language));
            button3.setText(papago1.papago(button3.getText().toString(),language));
        }
    }

    @Nullable

    public String getHashKey(Context context) {

        final String TAG = "KeyHash";

        String keyHash = null;

        try {

            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);



            for (Signature signature : info.signatures) {

                MessageDigest md;

                md = MessageDigest.getInstance("SHA");

                md.update(signature.toByteArray());

                keyHash = new String(Base64.encode(md.digest(), 0));

                Log.d(TAG, "hash hash hash" +  keyHash);
              //  Toast.makeText(getApplicationContext(),keyHash,Toast.LENGTH_SHORT).show();


            }

        } catch (Exception e) {

            Log.e("name not found", e.toString());

        }



        if (keyHash != null) {

            return keyHash;

        } else {

            return null;

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

                    return true;
                }

        } catch (SQLException e) {
            if (showMessage)
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
    }

    public void TestQuery(){
        try{
            if(user.isChecked() == true){
                sql = "select * from 사용자 where id = '" + ID.getText().toString() + "' and pw = '" + PW.getText().toString() + "'";
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if(rs.next()){
                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("id",rs.getString("id"));
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(),user05_Main .class);
                    startActivity(intent);
                    finish();
                }
            }else if(shop.isChecked() == true){
                sql = "select * from 점주 where id = '" + ID.getText().toString() + "' and pw = '" + PW.getText().toString() + "'";
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if(rs.next()){
                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("ceoid",rs.getString("id"));
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(),ceo_reception_check .class);
                    startActivity(intent);
                    finish();
                }
            }else if(rider.isChecked() == true){
                sql = "select * from 라이더 where id = '" + ID.getText().toString() + "' and pw = '" + PW.getText().toString() + "'";
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if(rs.next()){
                    Intent intent = new Intent(getApplicationContext(),tell.class);
                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("reiderID",rs.getString("id"));
                    startActivity(intent);

                    editor.commit();

                    finish();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
