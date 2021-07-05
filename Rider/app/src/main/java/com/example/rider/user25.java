package com.example.rider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapGeocodingInfo;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class user25 extends AppCompatActivity {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    Thread tr;
    String OrderNum1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user25_wherefood);
        tryConnect(true);
        Context mContext = getApplicationContext();

        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.linearLayoutTmap);
        final TMapView tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey( "l7xxc9e74ec06111499f87aa799b48429008" );
        linearLayoutTmap.addView( tMapView );
        Intent intent = getIntent();
        final String OrderNum = intent.getStringExtra("OrderNum");
        OrderNum1 = OrderNum;
        TMapGeocodingInfo geo = new TMapGeocodingInfo();

        try{
            String sql = "select * from 주문, 라이더 where 주문.라이더ID = 라이더.ID and 주문번호 = " + OrderNum;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                String aaa = rs.getString("배송지").substring(5);
                TMapData tmapdata = new TMapData();
                tmapdata.findAddressPOI(aaa, new TMapData.FindAddressPOIListenerCallback() {
                    @Override
                    public void onFindAddressPOI(ArrayList<TMapPOIItem> items) {
                        TMapPOIItem item = items.get(0);
                        String aaa = item.getPOIPoint().toString();
                        String [] z = aaa.split(" ");
                        String a2,a4;
                        a2 = z[1]; a4 = z[3];
                        double xx = Double.parseDouble(a2);

                        TMapMarkerItem marker1 = new TMapMarkerItem();
                        TMapPoint point1 = new TMapPoint(Double.parseDouble(a2),Double.parseDouble(a4));
                        Bitmap bitmap1 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_launcher_background);

                        marker1.setIcon(bitmap1); // 마커 아이콘 지정
                        marker1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
                        marker1.setTMapPoint( point1 ); // 마커의 좌표 지정
                        marker1.setName("목적지"); // 마커의 타이틀 지정
                        tMapView.addMarkerItem("markerItem1", marker1); // 지도에 마커 추가
                        tMapView.setCenterPoint( Double.parseDouble(a4),Double.parseDouble(a2));
                    }
                });


                TMapMarkerItem rider = new TMapMarkerItem();
                TMapPoint point2 = new TMapPoint(rs.getDouble("현재 x좌표"),rs.getDouble("현재 y좌표"));
                Bitmap bitmap2 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.io2);
                rider.setIcon(bitmap2);
                rider.setPosition(0.5f,1.0f);
                rider.setTMapPoint(point2);
                tMapView.addMarkerItem("rider",rider);
                tMapView.setCenterPoint(rs.getDouble("현재 y좌표"),rs.getDouble("현재 x좌표"));
            }
        }catch (Exception e){
            e.getMessage();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        tr = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                while (!Thread.interrupted())
                    try
                    {
                        Thread.sleep(15000);
                        runOnUiThread(new Runnable() // start actions in UI thread
                        {

                            @Override
                            public void run()
                            {
                                String sql = "select * from 라이더 where ID = (select 라이더ID from 주문 where 주문번호 = " + OrderNum1 + ")";
                                try {
                                    stmt = conn.createStatement();
                                    rs = stmt.executeQuery(sql);
                                    while(rs.next()){
                                        Double x = rs.getDouble("현재 x좌표");
                                        Double y = rs.getDouble("현재 y좌표");
                                        if((x != rs.getDouble("현재 x좌표")) || (y != rs.getDouble("현재 y좌표"))){
                                            Intent intent = new Intent(getApplicationContext(), user25.class);
                                            startActivity(intent);
                                            finish();

                                        }
                                    }
                                }catch (Exception e){
                                    e.getMessage();
                                }
                            }
                        });
                    }
                    catch (InterruptedException e)
                    {
                        // ooops
                    }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        tr.interrupt();
    }



    private boolean tryConnect(boolean showMessage) {
        try {
            app_loginDB connClass = new app_loginDB();
            conn = connClass.TestQuery();
            if (conn == null) {
                return false;
            } else if (conn.isClosed()) {
                return false;
            } else {
                //     Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (SQLException e) {
            if (showMessage)
                e.printStackTrace();
            return false;
        }
    }



}
