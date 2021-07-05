package com.example.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class user08_test extends Fragment {
    user08_restaurant_data activity;
    Button button1;
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    ListView Lv_menu;
    ArrayList<user08_data> data;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (user08_restaurant_data) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.user08_test, container, false);
        Intent intent = this.activity.getIntent();
        final String number = intent.getExtras().getString("number1");
        Lv_menu = (ListView)rootView.findViewById(R.id.user08_testlist);
        data = new ArrayList<user08_data>();

        if(tryConnect(true))
            Toast.makeText(getContext(), "오신것 을 환영 합니다 ", Toast.LENGTH_SHORT).show();

        String sql = "select * from 매장 a,메뉴 b where a.매장번호 = b.매장번호 and  매장전화번호 = " + number;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                data.add(new user08_data(rs.getString("메뉴명"),rs.getString("금액")));
            }
        }catch (Exception e){
            String error = e.getMessage();
            e.printStackTrace();
        }


        user08_adapter adapter = new user08_adapter(getContext(),data);

        Lv_menu.setAdapter(adapter);

        Lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),user09_menu_one_pick.class);
                String menu = data.get(position).getName();

                intent.putExtra("menu_name", menu);
                intent.putExtra("phoneNum", number);
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
