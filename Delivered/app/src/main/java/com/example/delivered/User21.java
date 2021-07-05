package com.example.delivered;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User21 extends AppCompatActivity {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    ListView lv;
    ArrayList<user23_data> data;
    TextView editText4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user21);
        lv = (ListView)findViewById(R.id.user21_list);
        data = new ArrayList<user23_data>();
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        String userid = pref.getString("id","");

        editText4= (TextView) findViewById(R.id.editText4);

        papago papago = new papago();
        String language = pref.getString("language","");
        editText4.setText(papago.papago(editText4.getText().toString(),language));


        tryConnect(true);
        String sql = "select * from 리뷰, 매장 where 리뷰.매장번호 = 매장.매장번호 and 사용자ID = '" + userid + "'";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                String aaa = rs.getString("사진");
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
             //   Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
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
