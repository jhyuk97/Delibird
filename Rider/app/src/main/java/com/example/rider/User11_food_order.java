package com.example.rider;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import java.util.ArrayList;
import java.util.Date;

public class User11_food_order extends AppCompatActivity {
    Button button1;
    EditText addr1;
    EditText addr2;
    EditText ordermore;
    TextView paymoney;
    TextView bbbd;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt;
    CheckBox point;

    CheckBox gusrmarufwp ; //현금 결제
    CheckBox  zkemrufwp ;// 카드 결제
    TextView dlqfurdPtl; //주소 입력 예씨
    TextView order_arr;  //주소
            TextView dycjdtkgkd; //요청 사항
    TextView rufwprmador; //결제금액
    TextView wnansrmador ;// 주문금액
    TextView qoekfdyrma ; //배달 요금
    TextView rufwptneks ;  //결제 수단

    Thread tr;
    String sit;

    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user11_food_order);

        addr1 = (EditText)findViewById(R.id.address1);
        addr2 = (EditText)findViewById(R.id.address2);
        ordermore = (EditText) findViewById(R.id.OrderMore1);
        paymoney = (TextView)findViewById(R.id.PayMoney);
        bbbd= (TextView)findViewById(R.id.bbbd);

        dlqfurdPtl= (TextView)findViewById(R.id.dlqfurdPtl);
        point = (CheckBox)findViewById(R.id.pointCheck);
        gusrmarufwp = (CheckBox)findViewById(R.id.gusrmarufwp);
        zkemrufwp = (CheckBox)findViewById(R.id.zkemrufwp);
        order_arr = (TextView)findViewById(R.id.order_arr);
        dycjdtkgkd = (TextView) findViewById(R.id.dycjdtkgkd);
        rufwprmador = (TextView)findViewById(R.id.rufwprmador);
        wnansrmador= (TextView)findViewById(R.id.wnansrmador);
        qoekfdyrma= (TextView)findViewById(R.id.qoekfdyrma);
        rufwptneks= (TextView)findViewById(R.id.rufwptneks);


        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        final SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        final String getTime = simpleDate.format(mDate);

        Intent intent = getIntent();
        ArrayList<user08_basket_data> list1 = (ArrayList<user08_basket_data>) intent.getSerializableExtra("list");
        final ArrayList<user08_basket_data> list = list1;
        final String number = intent.getStringExtra("number1");
        final int data_money = intent.getExtras().getInt("data_money"); // 요금

        int getMoney = intent.getExtras().getInt("money");
        final int aaa= data_money+2000;
        paymoney.setText(String.valueOf(data_money));
        bbbd.setText(String.valueOf(aaa));
        tryConnect(true);
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        final String test = pref.getString("id","실패");
        final String sql = "select * from 사용자 where id = '" + test + "'";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()){
               addr1.setText(rs.getString("도로명주소"));
               addr2.setText(rs.getString("상세주소"));
            }

        }catch (Exception e){
            e.getMessage();
        }

        button1 = (Button) findViewById(R.id.ok2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (point.isChecked()) {
                    try {
                        int money = 0;
                        String sql = "select * from 사용자 where ID = '" + test +"'";
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        while (rs.next()){
                            money = rs.getInt("잔액");
                        }
                        if(money > aaa) {
                            sql = "insert into 주문 values((select count(*)+1 from 주문), (select 매장번호 from 매장 where 매장전화번호 = " + number + "), '" + test + "', '', '" + point.getText() + "', " + aaa + ", '" + ordermore.getText() + "', '" + getTime + "', '주문옴', '', '" + addr1.getText().toString() + "', '" + addr2.getText().toString() + "')";
                            pstmt = conn.prepareStatement(sql);
                            pstmt.executeUpdate();
                            sql = "update 사용자 set 잔액 = 잔액 - " + aaa + " where ID = '" + test + "'";
                            pstmt = conn.prepareStatement(sql);
                            pstmt.executeUpdate();
                            for (int i = 0; i < list.size(); i++) {
                                String sql1 = "insert into 주문한음식 values((select count(*) from 주문), " + list.get(i).getNumber() + "," + list.get(i).getCount() + " )";
                                pstmt = conn.prepareStatement(sql1);
                                pstmt.executeUpdate();
                            }
                            sql = "select 현재상황 from 주문 where 사용자id = '" + test + "' and 주문번호 = (select max(주문번호) from 주문 where 사용자id = '" + test + "')";
                            rs = stmt.executeQuery(sql);
                            while (rs.next()){
                                sit = rs.getString("현재상황");
                            }
                            tr.start();

                            Intent intent = new Intent(getApplicationContext(), User16.class);
                            startActivity(intent);
                            finish();

                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(User11_food_order.this);
                            builder.setTitle("잔액이 부족합니다");
                            builder.setMessage("충전화면으로 가시겠습니까?");
                            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getApplicationContext(), User05_money.class);
                                    startActivity(intent);
                                }
                            });
                            builder.setNegativeButton("취소",null);
                            builder.create().show();
                        }
                    }
                    catch (Exception e) {
                        e.getMessage();
                    }

                }
                else{
               //     Toast.makeText(getApplicationContext(), "결제방식을 선택해주세요.", Toast.LENGTH_LONG).show();
                }

            }
        });
//

        papago papago = new papago();
        String language = pref.getString("language","");

        dlqfurdPtl.setText(papago.papago(dlqfurdPtl.getText().toString(),language));
        button1.setText(papago.papago(button1.getText().toString(),language));
        point.setText(papago.papago(point.getText().toString(),language));
        gusrmarufwp.setText(papago.papago(gusrmarufwp.getText().toString(),language));
        zkemrufwp.setText(papago.papago(zkemrufwp.getText().toString(),language));
        order_arr.setText(papago.papago(order_arr.getText().toString(),language));
        dycjdtkgkd.setText(papago.papago(dycjdtkgkd.getText().toString(),language));


        rufwprmador.setText(papago.papago(rufwprmador.getText().toString(),language));
        wnansrmador.setText(papago.papago(wnansrmador.getText().toString(),language));
        qoekfdyrma.setText(papago.papago(qoekfdyrma.getText().toString(),language));


        rufwptneks.setText(papago.papago(rufwptneks.getText().toString(),language));



        tr = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                while (!Thread.interrupted())
                    try
                    {
                        Thread.sleep(1000);
                        String sql = "select 현재상황,주문번호 from 주문 where 사용자ID = '" + test + "' and 주문번호 = (select max(주문번호) from 주문 where 사용자ID = '" + test + "')";
                        try{
                            rs = stmt.executeQuery(sql);
                            while (rs.next()){
                                String aaa = rs.getString("현재상황");
                                if(rs.getString("현재상황").equals("조리중")){
                                    NotificationSomethings("주문이 들어갔습니다");
                                    tr.interrupt();
                                }else if(rs.getString("현재상황").equals("주문취소")){
                                    NotificationSomethings("주문이 취소되었습니다.");
                                    tr.interrupt();
                                    //여기서스레드가끝나야되는데안끝난다
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

    }

    void stop(){
        tr.interrupt();
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

    public void NotificationSomethings(String text) {


        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(this, User16.class);
        notificationIntent.putExtra("notificationId", count); //전달할 값
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK) ;
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