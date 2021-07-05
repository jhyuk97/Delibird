package com.example.delivered;

import android.content.Intent;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class user23_reviewlist extends AppCompatActivity {
    ListView lv;
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    ArrayList<user23_data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user23_reviewlist);
        Intent intent = getIntent();
        String number = intent.getStringExtra("number1");
        lv = (ListView)findViewById(R.id.user23_reviewList);
        data = new ArrayList<user23_data>();

        tryConnect(true);
        String sql = "select * from 리뷰, 매장 where 리뷰.매장번호 = 매장.매장번호 and 매장전화번호 = " + number;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                data.add(new user23_data(rs.getString("제목"), rs.getFloat("평점"),rs.getString("사진"),rs.getString("내용"), rs.getInt("리뷰번호")));
            }
        }catch (Exception e){
            e.getMessage();
        }

        user23_adapter adapter = new user23_adapter(this,data);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), User18.class);
                intent.putExtra("number", data.get(position).getReviewnum());
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
                Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
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
