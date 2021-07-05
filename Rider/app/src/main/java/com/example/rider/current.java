package com.example.rider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

public class current extends AppCompatActivity {
    Button btn_Get;
    Button dspofj; //수락

    Button dlda; //삭제


    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    current_adapter adapter;
    ArrayList<current_data> data;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.currentstate );
        setTitle( "실시간 배달 현황  " );
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));
        //액션바 설정하기//
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String ID = pref.getString("reiderID","");

        data = new ArrayList<current_data>();
        lv = (ListView)findViewById(R.id.culistview2);
        tryConnect(true);
        String Date = new SimpleDateFormat("yyy-MM-dd").format(new java.util.Date());

        String sql = "select * from 라이더호출,매장,주문 where 주문일 = '"+ Date +"' and 현재상황 = '조리중' and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호";

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                data.add(new current_data(rs.getString("호출번호"),rs.getString("매장이름")));
            }
        }catch (Exception e){
            e.getMessage();
        }
        adapter = new current_adapter(data,this);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), tells.class);
                intent.putExtra("clsd", data.get(position).getCallNumber());
                startActivity(intent);
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


    //액션버튼 메뉴 액션바에 집어 넣기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.order_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        //or switch문을 이용하면 될듯 하다.
//개인정보 수정
        if (id == R.id.datechange) {
            Intent settingIntent = new Intent(this, datechange.class);
            startActivity(settingIntent);
        }
//오토바이정보
        if (id == R.id.motorcycle) {
            Intent settingIntent = new Intent(this, motorcycle.class);
            startActivity(settingIntent);
        }

//급여조회

        if (id == R.id.money) {
            Intent settingIntent = new Intent(this, money.class);
            startActivity(settingIntent);
        }

//배달이력조회

        if (id == R.id.Riderreference) {
            Intent settingIntent = new Intent(this, Riderreference.class);
            startActivity(settingIntent);
        }


        if (id == R.id.out) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



    }

