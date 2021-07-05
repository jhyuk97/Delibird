package com.example.shop;

import android.content.Intent;
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
import java.sql.Statement;
import java.util.Map;

public class current extends AppCompatActivity {
    Button btn_Get;
    ListView List;
Button dspofj; //수락

    Button dlda; //삭제


    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;


    SimpleAdapter AD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.currentstate );
        setTitle( "실시간 배달 현황  " );
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));
        //액션바 설정하기//



        java.util.List<Map<String, String>> MalayaList = null;






        current_list myData = new current_list();

        MalayaList = current_list.getdata();

        List = (ListView) findViewById( R.id.culistview2 );

        final String[] fromwhere = {"호출번호","매장이름"};
        final int[] viewhere = {R.id.textView458222, R.id.textView458};

        AD = new SimpleAdapter( current.this, MalayaList, R.layout.currentstate_listview, fromwhere, viewhere );

        List.setAdapter( AD );

        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), tells.class);


                String bbb = AD.getItem(position).toString();
                String data = bbb.substring(bbb.lastIndexOf("호출번호")+5,bbb.length()-1);




                intent.putExtra("clsd",data);

                startActivity(intent);
            }



        });

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

