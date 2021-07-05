package com.example.delivered;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
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

public class User22 extends AppCompatActivity {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    ListView lv;
    user22_adapter adapter;
    ArrayList<user22_data> data;
    TextView editText14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user22);
        lv = (ListView)findViewById(R.id.user22_lv);
        editText14= (TextView)findViewById(R.id.editText14);
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);

        papago papago = new papago();
        String language = pref.getString("language","");
        editText14.setText(papago.papago(editText14.getText().toString(),language));


        data = new ArrayList<user22_data>();
        tryConnect(true);
        try{
            String sql = "select * from 공지사항";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                data.add(new user22_data(rs.getString("제목"),rs.getString("날짜"),rs.getString("공지번호")));
            }
        }catch (Exception e){
            e.getMessage();
        }
        adapter = new user22_adapter(data, this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),user24.class);
                String noticeNumber = data.get(position).getNumber();
                intent.putExtra("noticeNumber",noticeNumber);
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
