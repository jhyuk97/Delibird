package com.example.shop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class user07_restaurant_listview extends AppCompatActivity {
    Button button1;
    Button button2;
    TextView type1;
    private Connection conn = null;
    private ResultSet rs = null;
    private Statement stmt = null;
    ListView Lv;
    ArrayList<raustarntSampledata> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user07_restaurant_listview);
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        String test = pref.getString("id","실패");

        type1 = (TextView)findViewById(R.id.TypeText);
        String sql = null;
        Lv = (ListView)findViewById(R.id.Lv_Restaurant);

        Intent intent = getIntent();
        String type = intent.getExtras().getString("type");

        papago papago = new papago();
        String language = pref.getString("language","");
        switch (type){
            case "한식": type1.setText(papago.papago("한식",language));
                sql = "select * from 매장 a,사용자 c where c.ID = '"+ test +"' and (6371 * acos(cos(radians(a.x좌표)) * cos(radians(c.x좌표)) * cos(radians(c.y좌표)" +
                        " - radians(a.y좌표)) + sin(radians(a.x좌표)) * sin(radians(c.x좌표)))) < 2 and 카테고리 = '한식'";
                break;
            case "중식": type1.setText(papago.papago("중식",language));
                sql = "select * from 매장 a,사용자 c where c.ID = '"+ test +"' and (6371 * acos(cos(radians(a.x좌표)) * cos(radians(c.x좌표)) * cos(radians(c.y좌표)" +
                        " - radians(a.y좌표)) + sin(radians(a.x좌표)) * sin(radians(c.x좌표)))) < 2 and 카테고리 = '중식'";
                break;
            case "양식": type1.setText(papago.papago("양식",language));
                sql = "select * from 매장 a,사용자 c where c.ID = '"+ test +"' and (6371 * acos(cos(radians(a.x좌표)) * cos(radians(c.x좌표)) * cos(radians(c.y좌표)" +
                        " - radians(a.y좌표)) + sin(radians(a.x좌표)) * sin(radians(c.x좌표)))) < 2 and 카테고리 = '양식'";
                break;
            case "일식": type1.setText(papago.papago("일본은식",language));
                sql = "select * from 매장 a,사용자 c where c.ID = '"+ test +"' and (6371 * acos(cos(radians(a.x좌표)) * cos(radians(c.x좌표)) * cos(radians(c.y좌표)" +
                        "- radians(a.y좌표)) + sin(radians(a.x좌표)) * sin(radians(c.x좌표)))) < 2 and 카테고리 = '일식'";
                break;
            case "아시아": type1.setText(papago.papago("아시아",language));;
                sql = "select * from 매장 a,사용자 c where c.ID = '"+ test +"' and (6371 * acos(cos(radians(a.x좌표)) * cos(radians(c.x좌표)) * cos(radians(c.y좌표)" +
                        " - radians(a.y좌표)) + sin(radians(a.x좌표)) * sin(radians(c.x좌표)))) < 2 and 카테고리 = '아시아'";
                break;
            case "패스트푸드": type1.setText(papago.papago("패스트푸드",language));;
                sql = "select * from 매장 a,사용자 c where c.ID = '"+ test +"' and (6371 * acos(cos(radians(a.x좌표)) * cos(radians(c.x좌표)) * cos(radians(c.y좌표)" +
                        " - radians(a.y좌표)) + sin(radians(a.x좌표)) * sin(radians(c.x좌표)))) < 2 and 카테고리 = '패스트푸드'";
                break;
            case "분식": type1.setText(papago.papago("분식",language));;
                sql = "select * from 매장 a,사용자 c where c.ID = '"+ test +"' and (6371 * acos(cos(radians(a.x좌표)) * cos(radians(c.x좌표)) * cos(radians(c.y좌표)" +
                        " - radians(a.y좌표)) + sin(radians(a.x좌표)) * sin(radians(c.x좌표)))) < 2 and 카테고리 = '분식'";
                break;
            case "디저트":  type1.setText(papago.papago("디저트",language));;
                sql = "select * from 매장 a,사용자 c where c.ID = '"+ test +"' and (6371 * acos(cos(radians(a.x좌표)) * cos(radians(c.x좌표)) * cos(radians(c.y좌표)" +
                        " - radians(a.y좌표)) + sin(radians(a.x좌표)) * sin(radians(c.x좌표)))) < 2 and 카테고리 = '디저트'";
                break;
            case "야식": type1.setText(papago.papago("야식",language));;
                sql = "select * from 매장 a,사용자 c where c.ID = '"+ test +"' and (6371 * acos(cos(radians(a.x좌표)) * cos(radians(c.x좌표)) * cos(radians(c.y좌표)" +
                        " - radians(a.y좌표)) + sin(radians(a.x좌표)) * sin(radians(c.x좌표)))) < 2 and 카테고리 = '야식'";
                break;
            case "1인분": type1.setText(papago.papago("1인분",language));;
                sql = "select * from 매장 a,사용자 c where c.ID = '"+ test +"' and (6371 * acos(cos(radians(a.x좌표)) * cos(radians(c.x좌표)) * cos(radians(c.y좌표)" +
                        " - radians(a.y좌표)) + sin(radians(a.x좌표)) * sin(radians(c.x좌표)))) < 2 and 카테고리 = '1인분'";

                break;
        }






        if(tryConnect(true))
            //Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

        data = new ArrayList<raustarntSampledata>();

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                //여기에 리스트뷰로 집어넣는거 리스트뷰도 커스텀으로 맞춰야할듯
                data.add(new raustarntSampledata(R.drawable.user_07,rs.getString("매장이름"),rs.getString("매장전화번호")));

            }
        }catch (Exception e){
            e.getMessage();
        }

        rastaurantlist_adapter adapter = new rastaurantlist_adapter(this,data);
        Lv.setAdapter(adapter);


        Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),user08_restaurant_data.class);
                String marketphone = data.get(position).getNumber();

                intent.putExtra("number1", marketphone);
                startActivity(intent);

            }



        });

        button1 = (Button) findViewById(R.id.btn_sort);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user07_restaurant_listview.this,user_list_set.class);
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
                Toast.makeText(getApplicationContext(),"데이터베이스 연결 실패",Toast.LENGTH_SHORT).show();
                return false;
            } else {
       //         Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
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
