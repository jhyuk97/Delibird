package com.example.rider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class user8_shopping_basket extends AppCompatActivity {
    Button button1;
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    ListView Lv_basket;
    ArrayList<user08_basket_data> data;
    TextView MarketName;
    Button GoPay;
    TextView DeleteItem;
    user08_basket_adpater adapter;
    TextView count_money;
    TextView qoekf;
    TextView wkdqkrnsl;

    TextView  chdrmador;
    @Override
    protected void onResume() {
        super.onResume();
        String money;
        String count;
        int total = 0;
        for(int i = 0; i < data.size(); i ++){
            money = data.get(i).getMoney();
            count = data.get(i).getCount();
            total = total + (Integer.parseInt(money) * Integer.parseInt(count));
        }
        count_money.setText(total + "￦");
    }

    public void aaa(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user08_shopping_basket);

        Intent intent = getIntent();
        count_money = (TextView)findViewById(R.id.Count_money);

        chdrmador= (TextView)findViewById(R.id.chdrmador);
        wkdqkrnsl= (TextView)findViewById(R.id.wkdqkrnsl); //장바구니
        qoekf= (TextView)findViewById(R.id.qoekf); //배달달

       global test = (global)getApplication();
        data = test.getArr();
        String money;
        String count;
        int total = 0;

        for(int i = 0; i < data.size(); i ++){
            money = data.get(i).getMoney();
            count = data.get(i).getCount();
            total = total + (Integer.parseInt(money) * Integer.parseInt(count));
        }

        final int data_money = total;
        count_money.setText(total + "원");
        String number = "";
        if(tryConnect(true))
        try{
            String sql = "select * from 매장 where 매장번호 = (select 매장번호 from 메뉴 where 메뉴번호 = " + data.get(0).getNumber() + ")";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                MarketName.setText(rs.getString("매장이름"));
                number = rs.getString("매장전화번호");
            }
        }catch (Exception e){
            e.getMessage();
        }

        final String number3 = number;

        Lv_basket = (ListView)findViewById(R.id.basketList);
        MarketName = (TextView)findViewById(R.id.MarketID);

        GoPay = (Button)findViewById(R.id.pay);
        GoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                Intent intent = new Intent(getApplicationContext(), User11_food_order.class);
                intent.putExtra("list",data);
                intent.putExtra("number1", number3);
                intent.putExtra("data_money",data_money);
                intent.putExtra("money",300);
                adapter.notifyDataSetChanged();
                startActivity(intent);}
                catch (Exception e){
                    String aaa = e.getMessage();
                    String bbb = "";
                }finally {
                    data.clear();
                }
            }
        });

        DeleteItem = (TextView) findViewById(R.id.DeleteAllItem);
        DeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.clear();
                adapter.notifyDataSetChanged();
                count_money.setText(("0원"));
            }
        });

        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );

        papago papago = new papago();
        String language = pref.getString("language","");

        MarketName.setText(papago.papago(MarketName.getText().toString(),language));
        GoPay.setText(papago.papago(MarketName.getText().toString(),language));
        DeleteItem.setText(papago.papago(MarketName.getText().toString(),language));
        wkdqkrnsl.setText(papago.papago(wkdqkrnsl.getText().toString(),language));
        qoekf.setText(papago.papago(qoekf.getText().toString(),language));
        chdrmador.setText(papago.papago(chdrmador.getText().toString(),language));
        //DeleteItem.setText(papago.papago(DeleteItem.getText().toString(),language));
        GoPay.setText(papago.papago(GoPay.getText().toString(),language));






       //     Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

        adapter = new user08_basket_adpater(this,data);
        Lv_basket.setAdapter(adapter);

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
         //       Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (SQLException e) {
            if (showMessage)
             //   Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
    }
}
