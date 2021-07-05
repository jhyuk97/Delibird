package com.example.delivered;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Riderreference extends AppCompatActivity {

    TextView number;
    ListView Riderreferencelistview;
    Button Riderreferenceok;
    SimpleAdapter AD;
    EditText Riderreference;
    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.riderreference);
        setTitle("배달 완료 항목 조회 ");

        //액션바 설정하기//

        //액션바 배경색 변경
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));
        //액션바 숨기기
        //hideActionBar();

        Riderreference= (EditText) findViewById( R.id.Riderreference );

        final SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        final String reiderID = pref.getString( "reiderID", "" );

        Riderreferencelistview = (ListView) findViewById( R.id.Riderreferencelistview );


        Riderreferenceok = (Button) findViewById( R.id.Riderreferenceok );

        Riderreferenceok.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                final String day = Riderreference.getText().toString();


                List<Map<String, String>> MalayaList = null;
                final Riderreference_listview myData = new Riderreference_listview();


                MalayaList = myData.getdata(reiderID,day);
                final List<Map<String, String>> finalMalayaList = MalayaList;


                final String[] fromwhere = {"날짜", "시간","매장주소","매장이름","배달지"};
                final int[] viewhere = {R.id.riderreferenceday, R.id.riderreferencetime, R.id.riderrefereusermkname2, R.id.riderreferencemkname, R.id.riderrefereuserhome};

                AD = new SimpleAdapter( com.example.delivered.Riderreference.this, finalMalayaList, R.layout.riderreference_listview, fromwhere, viewhere );

                Riderreferencelistview.setAdapter( AD );


            }
        } );

        final String day = Riderreference.getText().toString();


        List<Map<String, String>> MalayaList = null;
        final Riderreference_listview myData = new Riderreference_listview();


        MalayaList = myData.getdata(reiderID);
        final List<Map<String, String>> finalMalayaList = MalayaList;


        final String[] fromwhere = {"날짜", "시간","매장주소","매장이름","배달지"};
        final int[] viewhere = {R.id.riderreferenceday, R.id.riderreferencetime, R.id.riderrefereusermkname2, R.id.riderreferencemkname, R.id.riderrefereuserhome};

        AD = new SimpleAdapter( com.example.delivered.Riderreference.this, finalMalayaList, R.layout.riderreference_listview, fromwhere, viewhere );

        Riderreferencelistview.setAdapter( AD );

    }











    //액션버튼 메뉴 액션바에 집어 넣기
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.order_menu, menu);
        return true;
    }
    @Override
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
            Intent settingIntent = new Intent(this, com.example.delivered.Riderreference.class);
            startActivity(settingIntent);
        }

        if (id == R.id.cxchange) {
            Intent settingIntent = new Intent(this, Rider_cxchange.class);
            startActivity(settingIntent);
        }


        if (id == R.id.out) {
            finish();
        }

        return super.onOptionsItemSelected(item);
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
                //        Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
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

