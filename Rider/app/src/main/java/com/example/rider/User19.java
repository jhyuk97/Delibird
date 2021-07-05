package com.example.rider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static android.content.Context.MODE_PRIVATE;

public class User19 extends Fragment {
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    static Context mContext;
    user08_restaurant_data activity;
    TextView num;
    private Handler handler;
    //private WebView webView;
  TextView  textView11;

    TextView   textView12;
    TextView  textView13;
    TextView  user19_where;
    TextView textView9;

    TextView textView14;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (user08_restaurant_data) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.user19, container, false);
        Intent intent = this.getActivity().getIntent();
        String number = intent.getStringExtra("number1");
        TMapView tMapView = new TMapView(getActivity());
        tMapView.setSKTMapApiKey( "l7xxc9e74ec06111499f87aa799b48429008" );
        ViewGroup mapViewContainer = (ViewGroup) rootView.findViewById(R.id.mapView2);
        mapViewContainer.addView(tMapView);
        TMapMarkerItem markerItem1 = new TMapMarkerItem();


        num = (TextView)rootView.findViewById(R.id.user19_number);
        textView11= (TextView)rootView.findViewById(R.id.textView11);


        textView12= (TextView)rootView.findViewById(R.id.textView12);
        user19_where= (TextView)rootView.findViewById(R.id.user19_where);
        textView13= (TextView)rootView.findViewById(R.id.textView13);
        textView9= (TextView)rootView.findViewById(R.id.textView9);
        textView14= (TextView)rootView.findViewById(R.id.textView14);

        mContext = getContext();
        handler = new Handler();




        SharedPreferences pref = mContext.getSharedPreferences("pref", MODE_PRIVATE);
        papago papago = new papago();
        String language = pref.getString("language","");


        textView11.setText(papago.papago(textView11.getText().toString(),language));
        textView12.setText(papago.papago(textView12.getText().toString(),language));
        user19_where.setText(papago.papago(user19_where.getText().toString(),language));
        textView13.setText(papago.papago(textView13.getText().toString(),language));
        textView9.setText(papago.papago(textView9.getText().toString(),language));
        textView14.setText(papago.papago(textView14.getText().toString(),language));




        String sql = "select * from 매장 where 매장전화번호 = " + number;

        tryConnect(true);
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                num.setText(rs.getString("매장전화번호"));
                TMapPoint tMapPoint1 = new TMapPoint(rs.getDouble("x좌표"), rs.getDouble("y좌표"));
                Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher_background);

                markerItem1.setIcon(bitmap); // 마커 아이콘 지정
                markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
                markerItem1.setTMapPoint( tMapPoint1 ); // 마커의 좌표 지정
                markerItem1.setName("매장 위치"); // 마커의 타이틀 지정
                tMapView.addMarkerItem("markerItem1", markerItem1);
                tMapView.setCenterPoint( rs.getDouble("y좌표"), rs.getDouble("x좌표") );
            }
        }catch (Exception e){
            e.getMessage();
        }

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
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
