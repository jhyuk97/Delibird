package com.example.shop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Language extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language);

        Button button1 = (Button) findViewById(R.id.lang_korean);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("language","korean").apply();
                editor.commit();
                Intent intent = new Intent(getApplicationContext(),app_login .class);
                startActivity(intent);

            }
        });

        Button en = (Button)findViewById(R.id.lang_english);
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("language","english");
                editor.commit();
                String sss = pref.getString("language","");
                Intent intent = new Intent(getApplicationContext(),app_login .class);
                startActivity(intent);
            }
        });

        Button chn = (Button)findViewById(R.id.lang_chinese);
        chn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("language","chinese").apply();
                editor.commit();
                Intent intent = new Intent(getApplicationContext(),app_login .class);
                startActivity(intent);
            }
        });

        Button jap = (Button)findViewById(R.id.lang_japanese);
        jap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("language","japanese").apply();
                editor.commit();
                Intent intent = new Intent(getApplicationContext(),app_login .class);
                startActivity(intent);
            }
        });


    }



}

