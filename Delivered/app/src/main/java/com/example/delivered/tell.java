package com.example.delivered;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Trace;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class tell extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    Button tellnew;
    private Connection conn = null;
    private ResultSet rs = null;
    ResultSet rs1 = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    String reiderID;
    TextView riename;
    String callnumber="";
    TextView foname;
    TextView foodkkk;
    Thread tr;

    TextView rikkk1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tell);
        setTitle("Delivered");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4E8DF5")));


        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        reiderID = pref.getString("reiderID", "");

        riename = (TextView) findViewById(R.id.riename);


        button3 = (Button) findViewById(R.id.Current1);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), current.class);
                startActivity(intent);
            }
        });

        tellnew = (Button) findViewById(R.id.tellnew);
        tellnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sql = "select * from 라이더호출,매장,주문,사용자 where 호출번호 = (select max(호출번호) from 라이더호출 where 라이더호출.라이더ID = '" + reiderID + "') and (현재상황 ='음식점이동중' or 현재상황 ='배달중') and 주문.라이더ID='aa'  and 호출번호 =(select max(호출번호) from 라이더호출)  and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호 and 주문.사용자ID =사용자.ID and 매장.매장번호 = 주문.매장번호";
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        Intent intent = new Intent(getApplicationContext(), ing.class);
                        intent.putExtra("callnumber", rs.getString("호출번호"));
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    e.getMessage();
                }


            }
        });
        button1 = (Button) findViewById(R.id.idyy);
        button1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Click();
            }
        });


        foname = (TextView) findViewById(R.id.foname);
        foodkkk = (TextView) findViewById(R.id.foodkkk);
        rikkk1 = (TextView) findViewById(R.id.rikkk);
        button2 = (Button) findViewById(R.id.tellN);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (tryConnect(true))
            // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

            try {
                sql = "select 이름 from 라이더 where ID = '" + reiderID + "' ";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    riename.setText(rs.getString("이름"));
                }
            } catch (Exception e) {
                e.getMessage();
            }


        if (tryConnect(true))
            // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

            try {
                sql = "  select * from 라이더호출,매장,주문,사용자 where 호출번호 = (select max(호출번호) from 라이더호출) and 현재상황 ='조리중' and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호 and 주문.사용자ID =사용자.ID";

                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    foname.setText(rs.getString("매장이름"));
                    foodkkk.setText(rs.getString("매장주소"));
                    rikkk1.setText(rs.getString("배송지"));
                    String ttt = rs.getString("호출번호");
                    callnumber = rs.getString("호출번호");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        //액션바 설정하기//
        //액션바 타이틀 변경하기



       /* (new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                while (!Thread.interrupted())
                    try
                    {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() // start actions in UI thread
                        {

                            @Override
                            public void run()
                            {
                                try{
                                    sql = "select * from 라이더호출,매장,주문,사용자 where 호출번호 = (select max(호출번호) from 라이더호출) and 현재상황 ='조리중' and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호 and 주문.사용자ID =사용자.ID";
                                    stmt = conn.createStatement();
                                    rs = stmt.executeQuery(sql);
                                    while (rs.next()){
                                        String bbb = rs.getString("호출번호");
                                        String qqq = callnumber;
                                        if(callnumber.equals(rs.getString("호출번호"))){
                                        }
                                        else{
                                            callnumber = rs.getString("호출번호");
                                            Intent intent = new Intent(getApplicationContext(),tell.class);
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
        })).start();*/
        //액션바 숨기기
        //hideActionBar();

    }

    @Override
    protected void onPause() {
        super.onPause();
        tr.interrupt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tr = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted())
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() // start actions in UI thread
                        {

                            @Override
                            public void run() {
                                try {
                                    sql = "select * from 라이더호출,매장,주문,사용자 where 호출번호 = (select max(호출번호) from 라이더호출) and 현재상황 ='조리중' and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호 and 주문.사용자ID =사용자.ID";
                                    stmt = conn.createStatement();
                                    rs = stmt.executeQuery(sql);
                                    while (rs.next()) {
                                        String bbb = rs.getString("호출번호");
                                        String qqq = callnumber;
                                        if (callnumber.equals(rs.getString("호출번호"))) {
                                        } else {
                                            callnumber = rs.getString("호출번호");
                                            Intent intent = new Intent(getApplicationContext(), tell.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                } catch (Exception e) {
                                    e.getMessage();
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        // ooops
                    }
            }
        });

        tr.start();
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
//환전하기
        if (id == R.id.cxchange) {
            Intent settingIntent = new Intent(this, Rider_cxchange.class);
            startActivity(settingIntent);
        }




        if (id == R.id.out) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void Click() {
        if (foname.getText().toString().equals( "" )) {
            button1.setEnabled( false );
        }

        else {

            try {

                sql = "update 라이더호출  set 라이더ID ='" + reiderID + "' where 호출번호 = (select max(호출번호) from 라이더호출)   ";

                pstmt = conn.prepareStatement( sql );
                pstmt.executeUpdate();

                sql = "update 주문 set 라이더ID ='" + reiderID + "' , 현재상황='음식점이동중'  where 주문번호 = (select max(호출번호) from 라이더호출) ";
                pstmt = conn.prepareStatement( sql );
                pstmt.executeUpdate();

                sql = "  select * from 라이더호출,매장,주문,사용자 where 호출번호 = (select max(호출번호) from 라이더호출) and 현재상황 ='음식점이동중' and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호 and 주문.사용자ID =사용자.ID";
                stmt = conn.createStatement();
                rs = stmt.executeQuery( sql );

                while (rs.next()) {

                    Intent intent = new Intent( getApplicationContext(), ing.class );

                    intent.putExtra( "callnumber", rs.getString( "호출번호" ) );

                    startActivity( intent );
                }

            } catch (Exception e) {
                e.getMessage();
            }
        }

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



