package com.example.shop;

import android.content.SharedPreferences;
import android.os.Bundle;
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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


public class ceo_money_stat extends AppCompatActivity {
    TextView vksaotn;
    TextView chddor;




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
        setContentView( R.layout.ceo_money_stat);



        LV_Data = (ListView) findViewById( R.id.Moneystatistics);

        vksaotn= (TextView) findViewById( R.id.vksaotn);

        chddor= (TextView) findViewById( R.id.chddor);

        final   SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        final String ceoid = pref.getString( "ceoid", "" );



        List<Map<String, String>> MalayaList = null;
        ceo_money_stat_listview myData = new ceo_money_stat_listview();



        MalayaList=myData.getdata(ceoid);


        final String[] fromwhere = {"주문번호", "메뉴","배송지","금액"};
        final int[] viewhere = {R.id.statistics1, R.id.statistics2, R.id.statistic3,R.id.statistic4};

        AD = new SimpleAdapter( ceo_money_stat.this, MalayaList, R.layout.ceo_money_stat_listview, fromwhere, viewhere);
        LV_Data.setAdapter(AD);

        final String Date = new SimpleDateFormat("yyy-MM-dd").format(new java.util.Date());


        if (tryConnect( true ))
            // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

            try {
                sql = "select count(*) as 판매수, sum(금액) as 총액 from 주문 where  매장번호 = (select 매장번호 from 점주 where ID = '"+ ceoid +"') and 주문일 = '"+ Date +"' and 현재상황 ='배달완료' ";

                stmt = conn.createStatement();
                rs = stmt.executeQuery( sql );
                while (rs.next()) {
                    String all = rs.getString("총액");
                    String co = rs.getString("판매수");
                    vksaotn.setText( rs.getString( "판매수" ) );
                    chddor.setText( rs.getString( "총액" ) );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

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
                //    Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
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

