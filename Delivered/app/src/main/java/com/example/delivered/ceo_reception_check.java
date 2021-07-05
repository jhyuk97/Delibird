package com.example.delivered;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;


public class ceo_reception_check extends AppCompatActivity {
    TextView number;
    TextView ceoname2;
    ListView LV_Data;
    Button button4;
    SimpleAdapter AD;

    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private String io;
    int count1 = 0;

    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.ceo_reception_check);
        LV_Data = (ListView) findViewById( R.id.Lv_Data);
        number = (TextView) findViewById( R.id.textView2);
        button4 = (Button) findViewById( R.id.button4);

        ceoname2 = (TextView) findViewById( R.id.ceoname2);
        List<Map<String, String>> MalayaList = null;
        ceo_reception_check2 myData = new ceo_reception_check2();

        final   SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        final String ceoid = pref.getString( "ceoid", "" );

        MalayaList=myData.getdata(ceoid);

        final String[] fromwhere = {"주문번호","배송지", "메뉴"};
        final int[] viewhere = {R.id.textView2, R.id.textView4, R.id.textView6};

        AD = new SimpleAdapter( ceo_reception_check.this, MalayaList, R.layout.ceo_reception_check_2, fromwhere, viewhere);

        LV_Data.setAdapter(AD);
        LV_Data.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ceo_call_rider.class);



                String bbb = AD.getItem(position).toString();
                String data = bbb.substring(bbb.lastIndexOf("주문번호")+5,bbb.length()-1);


                intent.putExtra("mk",data);


                startActivity(intent);
            }



        });
        if (tryConnect( true ))
            // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

            try {
                sql = "select 이름 from 점주 where ID = '" + ceoid + "' ";

                stmt = conn.createStatement();
                rs = stmt.executeQuery( sql );
                while (rs.next()) {
                    ceoname2.setText( rs.getString( "이름" ) );

                }
                sql = "select max(주문번호) as 주문수 from 주문 where 매장번호 = (select 매장번호 from 점주 where ID = '" + ceoid + "')";
                rs = stmt.executeQuery(sql);
                while (rs.next()){
                    count1 = rs.getInt("주문수");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        button4 = (Button) findViewById(R.id.ceo_review2 );
        button4.setOnClickListener( new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent intent = new Intent( getApplicationContext(), ceo_review.class );

                startActivity( intent );
            }
        } );


        new Thread(new Runnable()
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
                                String sql = "select max(주문번호) as 주문수 from 주문 where 매장번호 = (select 매장번호 from 점주 where ID = '" + ceoid + "')";
                                try{
                                    rs = stmt.executeQuery(sql);
                                    while (rs.next()){
                                        if(rs.getInt("주문수") > count1){
                                            NotificationSomethings("주문왔습니다", rs.getString("주문수"));
                                            count1 = rs.getInt("주문수");
                                            AD.notifyDataSetChanged();
                                        }
                                        else if(rs.getInt("주문수") < count1){
                                            NotificationSomethings("주문취소됬습니다", rs.getString("주문수"));
                                            count1 = rs.getInt("주문수");
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
        }).start();

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
            //    Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (SQLException e) {
            if (showMessage)
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
    }

    public void NotificationSomethings(String text, String number) {


        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent;
        if(text.equals("주문취소됬습니다")) {
            notificationIntent = new Intent(this, ceo_reception_check.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }else{
            notificationIntent = new Intent(this, ceo_call_rider.class);
            notificationIntent.putExtra("mk", number);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,  PendingIntent.FLAG_UPDATE_CURRENT);

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
}

