package com.example.shop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;


public class ceo_Status extends AppCompatActivity {
    TextView number;
    TextView ceoname2;
    TextView Status12;
    TextView Status22;
    TextView Status32;
    TextView Status42;

    ListView LV_Data;
    Button button4;
    SimpleAdapter AD;
    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private String io;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.ceo_status);
        LV_Data = (ListView) findViewById( R.id.Stlistview234);




        List<Map<String, String>> MalayaList = null;
        ceo_Status_listview myData = new ceo_Status_listview();



        final   SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        final String ceoid = pref.getString( "ceoid", "" );

        MalayaList=myData.getdata(ceoid);


        final String[] fromwhere = {"배송지", "메뉴","금액","현재상황"};
        final int[] viewhere = {R.id.Status22, R.id.Status32, R.id.Status52,R.id.Status42};

        AD = new SimpleAdapter( ceo_Status.this, MalayaList, R.layout.ceo_status_listview, fromwhere, viewhere);
        LV_Data.setAdapter(AD);



    }

}

