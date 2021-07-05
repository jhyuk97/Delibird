package com.example.shop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public  class User12_grade extends Fragment {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    ArrayList<user12_grade_data> data;
    ListView lv;
   TextView vudwja;
    TextView   user12_grds;
    TextView user12_Phone;
    User12_MainActivity activity;


    TextView grade_title;
            TextView grade_Phone;
    TextView grade_gr;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //이 메소드가 호출될떄는 프래그먼트가 엑티비티위에 올라와있는거니깐 getActivity메소드로 엑티비티참조가능
        activity = (User12_MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //이제 더이상 엑티비티 참초가안됨
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //프래그먼트 메인을 인플레이트해주고 컨테이너에 붙여달라는 뜻임
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.user12_grade, container, false);
        SharedPreferences pref =  this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String userid = pref.getString("id","");

        String sql = "select distinct a.매장전화번호, a.매장이름, a.평점," +
                "convert(numeric(5,2), (6371 * acos(cos(radians(a.x좌표)) * cos(radians(c.x좌표)) * cos(radians(c.y좌표) " +
                "- radians(a.y좌표)) + sin(radians(a.x좌표)) * sin(radians(c.x좌표))))) as distance" +
                " from 매장 a,사용자 c where c.ID = '"+ userid +"' and (6371 * acos(cos(radians(a.x좌표)) * cos(radians(c.x좌표)) * cos(radians(c.y좌표) " +
                "- radians(a.y좌표)) + sin(radians(a.x좌표)) * sin(radians(c.x좌표)))) < 2  order by 평점 desc";

        tryConnect(true);
        data = new ArrayList<user12_grade_data>();

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                data.add(new user12_grade_data(rs.getString("매장전화번호"), rs.getString("매장이름"), rs.getString("평점")));
            }

        }catch (Exception e){
            e.getMessage();
        }

        final View header = getLayoutInflater().inflate(R.layout.user12_grade_header, null, false) ;

        user12_grade_adapter adapter = new user12_grade_adapter(getActivity(),data);
        lv = (ListView) rootView.findViewById(R.id.gradeview);
        lv.addHeaderView(header);
        lv.setAdapter(adapter);
        user12_grds =(TextView)rootView.findViewById(R.id.user12_grds);
        user12_Phone =(TextView)rootView.findViewById(R.id.user12_Phone);
        vudwja =(TextView)rootView.findViewById(R.id.vudwja);
        grade_title =(TextView)rootView.findViewById(R.id.grade_title);
        grade_Phone =(TextView)rootView.findViewById(R.id.grade_Phone);
        grade_gr =(TextView)rootView.findViewById(R.id.grade_gr);



        papago papago = new papago();
        String language = pref.getString("language","");
        vudwja.setText(papago.papago(vudwja.getText().toString(),language));


        grade_title.setText(papago.papago(grade_title.getText().toString(),language));
        grade_Phone.setText(papago.papago(grade_Phone.getText().toString(),language));
        grade_gr.setText(papago.papago(grade_gr.getText().toString(),language));

        return rootView;
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