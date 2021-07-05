package com.example.delivered;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;
import com.skt.Tmap.TmapAuthentication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mapfood extends AppCompatActivity {
    Connection conn;
    Statement stmt;
    ResultSet rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.mapfood);
        setTitle("음식점 지도  ");
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String id = pref.getString("reiderID","");
        Intent intent = getIntent();
        String num = intent.getStringExtra("callnumber2");
        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.mapfoodLinear);
        TMapView tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey( "l7xxc9e74ec06111499f87aa799b48429008" );
        linearLayoutTmap.addView( tMapView );

        String sql = "select * from 주문,매장,라이더 where 라이더.ID = 주문.라이더ID and 주문.매장번호 = 매장.매장번호 and 라이더ID = '" + id + "' and 현재상황 = '음식점이동중' and 주문번호 = " + num;
        tryConnect(true);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                TMapPoint tMapPointStart = new TMapPoint(rs.getDouble("현재 x좌표"), rs.getDouble("현재 y좌표")); // SKT타워(출발지)
                TMapPoint tMapPointEnd = new TMapPoint(rs.getDouble("x좌표"), rs.getDouble("y좌표")); // N서울타워(목적지)
                TMapPolyLine tMapPolyLine = new TMapData().findPathData(tMapPointStart, tMapPointEnd);
                tMapPolyLine.setLineColor(Color.BLUE);
                tMapPolyLine.setLineWidth(2);
                tMapView.setCenterPoint(rs.getDouble("현재 y좌표"),rs.getDouble("현재 x좌표"));
                tMapView.addTMapPolyLine("Line1", tMapPolyLine);

                TMapMarkerItem markerItem1 = new TMapMarkerItem();
                TMapPoint tMapPoint1 = new TMapPoint(rs.getDouble("현재 x좌표"), rs.getDouble("현재 y좌표")); // SKT타워
                Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.io2);
                markerItem1.setIcon(bitmap); // 마커 아이콘 지정
                markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
                markerItem1.setTMapPoint( tMapPoint1 ); // 마커의 좌표 지정
                markerItem1.setName("내위치"); // 마커의 타이틀 지정
                tMapView.addMarkerItem("rider", markerItem1); // 지도에 마커 추가

                TMapMarkerItem markerItem2 = new TMapMarkerItem();
                TMapPoint tMapPoint2 = new TMapPoint(rs.getDouble("x좌표"), rs.getDouble("y좌표"));
                Bitmap bitmap2 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_launcher_background);
                markerItem2.setIcon(bitmap2);
                markerItem2.setPosition(0.5f,1.0f);
                markerItem2.setTMapPoint(tMapPoint2);
                markerItem2.setName("매장");
                tMapView.addMarkerItem("market",markerItem2);

            }
        }catch (Exception e){
            e.getMessage();
        }

        //액션바 설정하기//
        //액션바 타이틀 변경하기

        //액션바 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        //홈버튼 표시
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //액션바 숨기기
        //hideActionBar();
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
            Intent settingIntent = new Intent(this, Riderreference.class);
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
                //     Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
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