package com.example.shop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class user08_restaurant_data extends AppCompatActivity {
    Button button1;
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    ListView Lv_menu;
    ArrayList<user08_data> data;
    TextView MarketName;
    TextView qoekfql;
    TextView chlthwnansrmador;
    Button goReview;
    Button goMarket;
    Button goMenu;
    TextView btn_call_order;
    TextView  btn_share;
    Button btn_pick;
    user08_test fragment1;
    user23_test fragment2;
    User19 fragment3;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user08_restaurant_data);
        Intent intent = getIntent();



        final String number = intent.getExtras().getString("number1");
        fragment1 = new user08_test();
        fragment2 = new user23_test();
        fragment3 = new User19();

        initFragment();

        //Lv_menu = (ListView)findViewById(R.id.menuList);



        goMenu = (Button)findViewById(R.id.goMenu);
        goMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                /*Intent intent = new Intent(user08_restaurant_data.this, User19.class);
                intent.putExtra("number1", number);
                startActivity(intent);*/
                transaction.replace(R.id.user08_frame, fragment1).commit();
            }
        });

        goReview = (Button)findViewById(R.id.goReview);
        goReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                /*Intent intent = new Intent(user08_restaurant_data.this, user23_reviewlist.class);
                intent.putExtra("number1", number);
                startActivity(intent);*/
                transaction.replace(R.id.user08_frame, fragment2).commit();
            }
        });

        goMarket = (Button)findViewById(R.id.goMarket);
        goMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                /*Intent intent = new Intent(user08_restaurant_data.this, User19.class);
                intent.putExtra("number1", number);
                startActivity(intent);*/
                transaction.replace(R.id.user08_frame, fragment3).commit();
            }
        });

        btn_call_order= (Button)findViewById(R.id.btn_call_order);
        btn_share= (Button)findViewById(R.id.btn_share);
        btn_pick= (Button)findViewById(R.id.btn_pick);
        qoekfql= (TextView) findViewById(R.id.qoekfql);

        chlthwnansrmador= (TextView) findViewById(R.id.chlthwnansrmador);

        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        papago papago = new papago();
        String language = pref.getString("language","");
        goMarket.setText(papago.papago(goMarket.getText().toString(),language));
        goReview.setText(papago.papago(goReview.getText().toString(),language));
        goMenu.setText(papago.papago(goMenu.getText().toString(),language));


        chlthwnansrmador .setText(papago.papago(chlthwnansrmador.getText().toString(),language));
        qoekfql.setText(papago.papago(qoekfql.getText().toString(),language));


 btn_call_order.setText(papago.papago(btn_call_order.getText().toString(),language));
        btn_share.setText(papago.papago(btn_share.getText().toString(),language));
        btn_pick.setText(papago.papago(btn_pick.getText().toString(),language));









    }

    public void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.user08_frame, fragment1);
        transaction.addToBackStack(null);
        transaction.commit();
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
       //         Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (SQLException e) {
            if (showMessage)
       //         Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
    }
}
