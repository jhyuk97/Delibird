package com.example.shop;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

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
    Thread tr;

    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.ceo_call_rider);
        setTitle("주문 상세 보기 ");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        final String id = pref.getString("ceoid","");


        final Intent intent = getIntent();
        final String number = intent.getExtras().getString("mk");

        final int num = Integer.parseInt(number);
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

        final int[] count = new int[1];

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql = "update 주문 set 현재상황 = '조리중' where 주문번호 = " + num +" ";
                try {
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);

                    sql = "insert into 라이더호출 values ("+ num +",(select 매장번호 from 주문 where 주문번호 = " + num+"),'','" + Date + "','" + Time + "')";
                    stmt.executeUpdate(sql);
                    sql = "select count(*) as 갯수 from 주문 where (현재상황 = '배달완료' or 현재상황 = '리뷰작성완료') and 매장번호 = (select 매장번호 from 점주 where ID = '" + id + "')";
                    rs = stmt.executeQuery(sql);
                    while (rs.next()){
                        int count1 = rs.getInt("갯수");
                        count[0] = count1;
                    }
                    tr.start();
                    Intent Intent = new Intent(getApplicationContext(), ceo_reception_check.class);
                    startActivity(Intent);
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        tr = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                while (!Thread.interrupted())
                    try
                    {
                        Thread.sleep(1000);
                        String sql = "select count(*) as 갯수 from 주문 where (현재상황 = '배달완료' or 현재상황 = '리뷰작성완료') and 매장번호 = (select 매장번호 from 점주 where ID = '" + id + "')";
                        try{
                            rs = stmt.executeQuery(sql);
                            while (rs.next()){
                                if(rs.getInt("갯수") > count[0]){
                                    NotificationSomethings("음식이 고객에게 도착했습니다.","0");
                                    tr.interrupt();
                                }
                            }
                        }catch (Exception e){
                            e.getMessage();
                        }
                    }
                    catch (InterruptedException e)
                    {
                        // ooops
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

    public void NotificationSomethings(String text, String number) {


        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent;
        notificationIntent = new Intent(this, ceo_reception_check.class);
            //notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,  PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground)) //BitMap 이미지 요구
                .setContentTitle(text)
                .setContentText(text)
                // 더 많은 내용이라서 일부만 보여줘야 하는 경우 아래 주석을 제거하면 setContentText에 있는 문자열 대신 아래 문자열을 보여줌
                //.setStyle(new NotificationCompat.BigTextStyle().bigText("더 많은 내용을 보여줘야 하는 경우..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent) // 사용자가 노티피케이션을 탭시 ResultActivity로 이동하도록 설정
                .setVibrate(new long[]{100, 0, 100, 0})
                .setAutoCancel(true);

        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.ic_launcher_foreground); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            CharSequence channelName  = "노티페케이션 채널";
            String description = "오레오 이상을 위한 것임";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName , importance);
            channel.setDescription(description);

            // 노티피케이션 채널을 시스템에 등록
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        }else builder.setSmallIcon(R.mipmap.ic_launcher); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남

        assert notificationManager != null;
        notificationManager.notify(1234, builder.build()); // 고유숫자로 노티피케이션 동작시킴

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
