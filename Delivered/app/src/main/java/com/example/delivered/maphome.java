package com.example.delivered;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class maphome extends AppCompatActivity {


    private Connection conn = null;
    private ResultSet rs = null;
    ResultSet rs1 = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.maphome);
        setTitle("고객위치");
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String id = pref.getString("reiderID","");
        Intent intent = getIntent();
        String num = intent.getStringExtra("callnumber2");
        //액션바 설정하기//
        //액션바 타이틀 변경하기
        //액션바 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        //홈버튼 표시
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //액션바 숨기기
        //hideActionBar();



        LinearLayout linearLayoutTmap = (LinearLayout)findViewById( R.id.linearLayoutTmap3);
        final TMapView tMapView = new TMapView(this);
        tMapView.setHttpsMode(true);
        tMapView.setSKTMapApiKey("l7xxf4938ed7b59f4928a5bd8de9bfeddcc4");
        linearLayoutTmap.addView( tMapView );




        String sql = "select * from 주문,라이더 where 라이더.ID = 주문.라이더ID and 라이더ID = '" + id + "' and 현재상황 = '배달중' and 주문번호 = " + num;
        tryConnect(true);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                TMapData tmapdata = new TMapData();
                String aaa = rs.getString("배송지").substring(5);
                final Double riderx = rs.getDouble("현재 x좌표");
                final Double ridery = rs.getDouble("현재 y좌표");

                tmapdata.findAddressPOI(aaa, new TMapData.FindAddressPOIListenerCallback() {
                    @Override
                    public void onFindAddressPOI(ArrayList<TMapPOIItem> items) {
                        TMapPOIItem item = items.get(0);
                        String aaa = item.getPOIPoint().toString();
                        String [] z = aaa.split(" ");
                        String a2,a4;
                        a2 = z[1]; a4 = z[3];
                        double xx = Double.parseDouble(a2);
                        double yy = Double.parseDouble(a4);


                        TMapMarkerItem markerItem2 = new TMapMarkerItem();
                        TMapPoint tMapPoint2 = new TMapPoint(xx, yy);
                        Bitmap bitmap2 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_launcher_background);
                        markerItem2.setIcon(bitmap2);
                        markerItem2.setPosition(0.5f,1.0f);
                        markerItem2.setTMapPoint(tMapPoint2);
                        markerItem2.setName("매장");
                        tMapView.addMarkerItem("market",markerItem2);

                        TMapPoint tMapPointEnd = new TMapPoint(xx, yy); // N서울타워(목적지)
                        TMapPoint tMapPointStart = new TMapPoint(riderx, ridery); // SKT타워(출발지)
                        try {
                            TMapPolyLine tMapPolyLine = new TMapData().findPathData(tMapPointStart, tMapPointEnd);
                            tMapPolyLine.setLineColor(Color.BLUE);
                            tMapPolyLine.setLineWidth(2);
                            tMapView.addTMapPolyLine("Line1", tMapPolyLine);
                        }catch (Exception e){
                            e.getMessage();
                        }
                    }
                });

                TMapMarkerItem markerItem1 = new TMapMarkerItem();
                TMapPoint tMapPoint1 = new TMapPoint(rs.getDouble("현재 x좌표"), rs.getDouble("현재 y좌표")); // SKT타워
                Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.io2);
                markerItem1.setIcon(bitmap); // 마커 아이콘 지정
                markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
                markerItem1.setTMapPoint( tMapPoint1 ); // 마커의 좌표 지정
                markerItem1.setName("내위치"); // 마커의 타이틀 지정
                tMapView.setCenterPoint(rs.getDouble("현재 y좌표"), rs.getDouble("현재 x좌표"));
                tMapView.addMarkerItem("rider", markerItem1); // 지도에 마커 추가

            }
        }catch (Exception e){
            e.getMessage();
        }

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
                Toast.makeText( getApplicationContext(), "데이터베이스 연결 실패", Toast.LENGTH_SHORT ).show();
                return false;
            } else {
                //         Toast.makeText( getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT ).show();
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






















