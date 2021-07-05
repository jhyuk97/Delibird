package com.example.rider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class user06_mydata extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user06_mydata);






//회원정보 변경
        button1 = (Button) findViewById(R.id.btn_editinfo1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),User20.class);
                startActivity(intent);
            }
        });

//주문내역
        button2 = (Button) findViewById(R.id.btn_orderlist);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),User16.class);
                startActivity(intent);
            }
        });

//내리뷰보기
        button3 = (Button) findViewById(R.id.btn_myreview);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),User21.class);
                startActivity(intent);
            }
        });

//공지사항
            button4 = (Button) findViewById(R.id.btn_notice);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),User22.class);
                startActivity(intent);
            }
        });
//고객센터
        button5 = (Button) findViewById(R.id.btn_servicecenter);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),user8_shopping_basket.class);
                startActivity(intent);
                finish();
           //     Toast.makeText(getApplicationContext(), "아직 서비스를 지원할수없습니다 .", Toast.LENGTH_LONG).show();
            }
        });
//공지사항
        button6 = (Button) findViewById(R.id.btn_preference);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), User17.class);
                startActivity(intent);
         //       Toast.makeText(getApplicationContext(), "언어 재 설정시 모든 프로그램이 종료됩니다   .", Toast.LENGTH_LONG).show();
                finish();

            }
        });

        button7 = (Button) findViewById(R.id.btn_homeinfo);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bc.ac.kr")); startActivity(intent);
          //      Toast.makeText(getApplicationContext(), "아직 서버가 존재 하지 않은 관계로...   .", Toast.LENGTH_LONG).show();
           finish();
            }
        });


        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );

        papago papago = new papago();
        String language = pref.getString("language","");
        button1.setText(papago.papago(button1.getText().toString(),language));
        button2.setText(papago.papago(button2.getText().toString(),language));
        button3.setText(papago.papago(button3.getText().toString(),language));
        button4.setText(papago.papago(button4.getText().toString(),language));
        button5.setText(papago.papago(button5.getText().toString(),language));
        button6.setText(papago.papago(button6.getText().toString(),language));
        button7.setText(papago.papago("홈페이지",language));
    }

}
