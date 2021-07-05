package com.example.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class user23_test extends Fragment {
    ListView lv;
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    user08_restaurant_data activity;
    ArrayList<user23_data> data;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (user08_restaurant_data) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.user23_reviewlist, container, false);
        Intent intent = this.getActivity().getIntent();
        String number = intent.getStringExtra("number1");
        lv = (ListView)rootView.findViewById(R.id.user23_reviewList);
        data = new ArrayList<user23_data>();

        tryConnect(true);
        String sql = "select * from 리뷰, 매장 where 리뷰.매장번호 = 매장.매장번호 and 매장전화번호 = " + number;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                data.add(new user23_data(rs.getString("제목"), rs.getFloat("평점"),rs.getString("사진"),rs.getString("내용"), rs.getInt("리뷰번호")));
            }
        }catch (Exception e){
            e.getMessage();
        }

        user23_adapter adapter = new user23_adapter(getContext(),data);        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), User18.class);
                intent.putExtra("number", data.get(position).getReviewnum());
                startActivity(intent);
            }
        });

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
