package com.example.rider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
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


public class Rider_cxchange extends AppCompatActivity {
    TextView number;
    ListView ch;
    Button chqwe;
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
        setContentView( R.layout.rider_exchange);
        setTitle("환전하기");
        ch = (ListView) findViewById( R.id.chooooooo);



        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));

        List<Map<String, String>> MalayaList = null;
        Rider_cxchange_listview myData = new Rider_cxchange_listview();


        final   SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        final String reiderID = pref.getString( "reiderID", "" );

        MalayaList=myData.getdata(reiderID);


        chqwe = (Button) findViewById( R.id.chqwe);
        chqwe.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if(tryConnect(true)){
                try {
                    sql = "update 급여 set 환전 ='O' where 라이더ID = '"+reiderID+"'  ";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();
                    Toast.makeText(getApplicationContext(),"환전이 완료었습니다",Toast.LENGTH_SHORT).show();
                    finish();
                } catch (Exception e) {
                    e.getMessage();
                }

                }
            }
        });





        final String[] fromwhere = {"급여번호", "돈","날짜","시간"};
        final int[] viewhere = {R.id.cxchangenumber, R.id.cxchangemoy, R.id.cxchangemoyday, R.id.cxchangemoytime};

        AD = new SimpleAdapter( Rider_cxchange.this, MalayaList, R.layout.rider_exchange_listview, fromwhere, viewhere);
       // View header = getLayoutInflater().inflate(R.layout.exchange_header, null, false) ;
        //final CheckBox checkBox = header.findViewById(R.id.header_checkbox);
       /* checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){

                }
            }
        });
*/
     //   ch.addHeaderView(header);
    //    ch.setAdapter(AD);

        ch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Rider_cxchange_d.class);
                String bbb = AD.getItem(position).toString();
                String data = bbb.substring(bbb.lastIndexOf("급여번호")+5,bbb.length()-24);
                intent.putExtra("minho",data);
                startActivity(intent);
                finish();
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
                //Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
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

