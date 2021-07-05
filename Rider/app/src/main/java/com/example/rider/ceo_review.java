package com.example.rider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class ceo_review extends AppCompatActivity {
    Button btn_Get;
    ListView List;

    SimpleAdapter AD;

    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ceo_review);
        String a;

        final SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        final String ceoid = pref.getString( "ceoid", "" );

        if (tryConnect( true ))
            try {
                sql = "select 매장이름 from 매장 where 매장번호 =(select 매장번호 from 점주 where ID ='" + ceoid + "')";
                stmt = conn.createStatement();
                rs = stmt.executeQuery( sql );
                while (rs.next()) {
                    a = rs.getString("매장이름" );
                    setTitle( a );
                }

            } catch (Exception e) {

                e.printStackTrace();
            }





        List = (ListView) findViewById(R.id.review_management_List_view);


        List<Map<String, String>> MalayaList = null;

        ceo_review_ListView myData = new ceo_review_ListView();

        MalayaList = ceo_review_ListView.getdata();


        final String[] fromwhere = {"리뷰번호", "제목", "등록일"};

        final int[] viewhere = {R.id.ReviewNumber, R.id.ReviewName, R.id.Reviewday};

        AD = new SimpleAdapter(ceo_review.this, MalayaList, R.layout.ceo_review_listview, fromwhere, viewhere);


        List.setAdapter(AD);

        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              //  Intent intent = new Intent(getApplicationContext(),ceo_review_Answer.class);

                Intent intent = new Intent(getApplicationContext(), ceo_review_Answer.class);

                String number = AD.getItem(position).toString();
                String data = number.substring(number.lastIndexOf("리뷰번호")+5,number.indexOf(",",number.lastIndexOf("리뷰번호")+5));
                intent.putExtra("aaa", data);

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
                Toast.makeText( getApplicationContext(), "데이터베이스 연결 실패", Toast.LENGTH_SHORT ).show();
                return false;
            } else {
                //    Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (SQLException e) {
            if (showMessage)
                Toast.makeText( getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT ).show();
            e.printStackTrace();
            return false;
        }
    }
}


