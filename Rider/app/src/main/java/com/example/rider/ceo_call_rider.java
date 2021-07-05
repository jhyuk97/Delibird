package com.example.rider;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class ceo_call_rider extends AppCompatActivity {
    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    TextView number1;
    TextView menu;
    TextView more;
    TextView money;
    TextView address;
    Button call;
    Button cesosdfg;
   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.ceo_call_rider);
        setTitle("주문 상세 보기 ");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));


        final Intent intent = getIntent();
      final String number = intent.getExtras().getString("mk");

        final   int num = Integer.parseInt(number);
        final String Date = new SimpleDateFormat("yyy-MM-dd").format(new java.util.Date());
        final String Time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul")).getTime());


 final  int esc=num;

        number1 = (TextView) findViewById( R.id.number);
        menu = (TextView)findViewById( R.id.Menu);
        more = (TextView) findViewById( R.id.More);
        money = (TextView)findViewById( R.id.money);
        address = (TextView)findViewById( R.id.call_address);
        cesosdfg = (Button)findViewById( R.id.cesosdfg);
        call = (Button)findViewById( R.id.riderCall);

        cesosdfg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tryConnect(true))
                    try{
                        sql= "select distinct a.주문번호, a.금액, a.배송지, a.요청사항, a.주문일, a.사용자ID ,STUFF(( SELECT ',' + d.메뉴명 FROM 메뉴 d, 주문한음식 e where d.메뉴번호 = e.메뉴번호 and e.주문번호 = a.주문번호 FOR XML PATH('') ),1,1,'') AS 메뉴 ,a.매장번호 from 주문 a,주문한음식 c,메뉴 b where a.주문번호 = c.주문번호 and c.메뉴번호 = b.메뉴번호 and a.매장번호 = b.매장번호 and a.주문번호 = " + num +" ";
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        while (rs.next()) {
                            number1.setText(rs.getString("주문번호"));
                            menu.setText(rs.getString("메뉴"));
                            more.setText(rs.getString("요청사항"));
                            money.setText(rs.getString("금액"));
                            address.setText(rs.getString("배송지"));


                            Intent intent = new Intent( ceo_call_rider.this, ceo_reception_esc.class );
                            intent.putExtra("num", esc);
                            intent.putExtra("moneys",rs.getString("금액"));
                            startActivity( intent );
                            finish();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        e.getMessage();
                    }




            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql = "update 주문 set 현재상황 = '조리중' where 주문번호 = " + num +" ";
                try {
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);

                    sql = "insert into 라이더호출 values ("+ num +",(select 매장번호 from 주문 where 주문번호 = " + num+"),'','" + Date + "','" + Time + "')";
                    stmt.executeUpdate(sql);
                    Intent Intent = new Intent(getApplicationContext(), ceo_reception_check.class);
                    startActivity(Intent);
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        if(tryConnect(true))
        try{
         sql= "select distinct a.주문번호, a.금액, a.배송지, a.요청사항, a.주문일, a.사용자ID ,STUFF(( SELECT ',' + d.메뉴명 FROM 메뉴 d, 주문한음식 e where d.메뉴번호 = e.메뉴번호 and e.주문번호 = a.주문번호 FOR XML PATH('') ),1,1,'') AS 메뉴 ,a.매장번호 from 주문 a,주문한음식 c,메뉴 b where a.주문번호 = c.주문번호 and c.메뉴번호 = b.메뉴번호 and a.매장번호 = b.매장번호 and a.주문번호 = " + num +" ";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                number1.setText(rs.getString("주문번호"));
                menu.setText(rs.getString("메뉴"));
                more.setText(rs.getString("요청사항"));
                money.setText(rs.getString("금액"));
                address.setText(rs.getString("배송지"));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
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
     //           Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
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
