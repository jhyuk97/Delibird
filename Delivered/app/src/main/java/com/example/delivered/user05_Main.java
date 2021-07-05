package com.example.delivered;

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
import android.view.Menu;
import android.view.MenuItem;
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




public class user05_Main extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    Button chinese;
    Button western;
    Button japanese;
    Button asian;
    Button fast;
    Button boon;
    Button dessert;
    Button night;
    Button one;
    Button usermoney;
    Button myhome;
    Button kfood;
    Button myvillage;
    TextView btn_adr;
    TextView Moneyuser;
    TextView dnlcltjfwjd;
    Thread tr;
    Thread t;
    String Hidden;

    String sql;


    Button sheet;

    TextView namename;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private int count = 0;

    private Connection conn;
    private ResultSet rs;
    private ResultSet rs2;

    private Statement stmt;
    private PreparedStatement pstmt;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        String userid = pref.getString( "id", "" );
        try {

            sql = "select 잔액,도로명주소 from 사용자 where ID = '" + userid + "' ";
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while (rs.next()) {
                Moneyuser.setText( rs.getString( "잔액" ) );
                btn_adr.setText( rs.getString( "도로명주소" ) );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.user05__main );
        setTitle( "Delivered" );
        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        final String userid = pref.getString( "id", "" );

        Moneyuser = (TextView) findViewById( R.id.Moneyuser1 );
        dnlcltjfwjd= (TextView) findViewById( R.id.dnlcltjfwjd );

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));

                //    namename.setText(userID);
        btn_adr = (TextView) findViewById( R.id.btn_adr );
        btn_adr.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( user05_Main.this, SearchAddress.class );
                intent.putExtra( "for", 10 );

                startActivity( intent );

            }
        } );

        myhome = (Button) findViewById( R.id.myhome );
        myhome.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( user05_Main.this, user05_Main.class );

                startActivity( intent );
                finish();
            }
        } );

        kfood = (Button) findViewById( R.id.kfood );
        kfood.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( getApplicationContext(), User13_kfood.class );
                startActivity( intent );


            }
        } );


        myvillage = (Button) findViewById( R.id.myvillage );
        myvillage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( user05_Main.this, User12_MainActivity.class );
                startActivity( intent );


            }
        } );

        sheet = (Button) findViewById( R.id.sheet );
        sheet.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( getApplicationContext(), User16.class );
                startActivity( intent );


            }
        } );


        usermoney = (Button) findViewById( R.id.usermoney );
        usermoney.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( user05_Main.this, User05_money.class );

                //   intent.putExtra("userID",userID);
                startActivity( intent );


            }
        } );




        button3 = (Button) findViewById( R.id.btn_korean );
        button3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( user05_Main.this, user07_restaurant_listview.class );
                intent.putExtra( "type", "한식" );
                startActivity( intent );
            }
        } );

        chinese = (Button) findViewById( R.id.btn_chinese );
        chinese.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( user05_Main.this, user07_restaurant_listview.class );
                intent.putExtra( "type", "중식" );
                startActivity( intent );
            }
        } );

        western = (Button) findViewById( R.id.btn_west );
        western.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( user05_Main.this, user07_restaurant_listview.class );
                intent.putExtra( "type", "양식" );
                startActivity( intent );
            }
        } );

        japanese = (Button) findViewById( R.id.btn_japanese );
        japanese.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( user05_Main.this, user07_restaurant_listview.class );
                intent.putExtra( "type", "일식" );
                startActivity( intent );
            }
        } );

        asian = (Button) findViewById( R.id.btn_asian );
        asian.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( user05_Main.this, user07_restaurant_listview.class );
                intent.putExtra( "type", "아시아" );
                startActivity( intent );
            }
        } );

        fast = (Button) findViewById( R.id.btn_fast );
        fast.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( user05_Main.this, user07_restaurant_listview.class );
                intent.putExtra( "type", "패스트푸드" );
                startActivity( intent );
            }
        } );

        boon = (Button) findViewById( R.id.btn_boon );
        boon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( user05_Main.this, user07_restaurant_listview.class );
                intent.putExtra( "type", "분식" );
                startActivity( intent );
            }
        } );

        dessert = (Button) findViewById( R.id.btn_dessert );
        dessert.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( user05_Main.this, user07_restaurant_listview.class );
                intent.putExtra( "type", "디저트" );
                startActivity( intent );
            }
        } );

        night = (Button) findViewById( R.id.btn_night );
        night.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( user05_Main.this, user07_restaurant_listview.class );
                intent.putExtra( "type", "야식" );
                startActivity( intent );
            }
        } );

        one = (Button) findViewById( R.id.btn_one );
        one.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( user05_Main.this, user07_restaurant_listview.class );
                intent.putExtra( "type", "1인분" );
                startActivity( intent );


            }
        } );




        if (tryConnect( true ))
            // Toast.makeText(getApplicationContext(), "데이터베이스 연결 성공", Toast.LENGTH_SHORT).show();

            try {

                sql = "select 잔액,도로명주소 from 사용자 where ID = '" + userid + "' ";
                stmt = conn.createStatement();
                rs = stmt.executeQuery( sql );
                while (rs.next()) {
                    Moneyuser.setText( rs.getString( "잔액" ) );
                    btn_adr.setText( rs.getString( "도로명주소" ) );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        papago papago = new papago();
        TextView text_korean = (TextView)findViewById(R.id.text_korean);
        TextView text_chinese = (TextView)findViewById(R.id.text_chinese);
        TextView text_japanese = (TextView)findViewById(R.id.text_japanese);
        TextView text_asian = (TextView)findViewById(R.id.text_asian);
        TextView text_western = (TextView)findViewById(R.id.text_western);
        TextView text_fast = (TextView)findViewById(R.id.text_fastfood);
        TextView text_snackbar = (TextView)findViewById(R.id.text_snackbar);
        TextView text_dessert = (TextView)findViewById(R.id.text_dessert);
        TextView text_night = (TextView)findViewById(R.id.text_nightfood);
        TextView text_one = (TextView)findViewById(R.id.text_1serving);
        TextView text_lunchbox = (TextView)findViewById(R.id.text_lunchbox);
        TextView text_salad = (TextView)findViewById(R.id.salad);
        TextView  Dma = (TextView)findViewById(R.id.Dma);



        String language = pref.getString("language","");
        if(language.equals("korean")){}
        else if(language.equals("english")){
            Dma.setText(papago.papago("돈",language));
            text_korean.setText(papago.papago(text_korean.getText().toString(),language));
            text_japanese.setText(papago.papago("일본음식",language));
            text_chinese.setText(papago.papago(text_chinese.getText().toString(),language));
            text_asian.setText(papago.papago(text_asian.getText().toString(),language));
            text_western.setText(papago.papago("서양음식",language));
            text_fast.setText(papago.papago(text_fast.getText().toString(),language));
            text_snackbar.setText(papago.papago(text_snackbar.getText().toString(),language));
            text_dessert.setText(papago.papago(text_dessert.getText().toString(),language));
            text_night.setText(papago.papago(text_night.getText().toString(),language));
            text_one.setText(papago.papago(text_one.getText().toString(),language));
            text_lunchbox.setText(papago.papago(text_lunchbox.getText().toString(),language));
            text_salad.setText(papago.papago(text_salad.getText().toString(),language));
            dnlcltjfwjd.setText(papago.papago("↓ 나의 위치 설정 ↓",language));
            myvillage.setText(papago.papago(myvillage.getText().toString(),language));
            myhome.setText(papago.papago(myhome.getText().toString(),language));
            kfood.setText(papago.papago(kfood.getText().toString(),language));
            sheet.setText(papago.papago(sheet.getText().toString(),language));
        }
        else if(language.equals("japanese")){
            Dma.setText(papago.papago("돈",language));
            text_korean.setText(papago.papago(text_korean.getText().toString(),language));
            text_japanese.setText(papago.papago("일본음식",language));
            text_chinese.setText(papago.papago(text_chinese.getText().toString(),language));
            text_asian.setText(papago.papago(text_asian.getText().toString(),language));
            text_western.setText(papago.papago("서양음식",language));
            text_fast.setText(papago.papago(text_fast.getText().toString(),language));
            text_snackbar.setText(papago.papago(text_snackbar.getText().toString(),language));
            text_dessert.setText(papago.papago(text_dessert.getText().toString(),language));
            text_night.setText(papago.papago(text_night.getText().toString(),language));
            text_one.setText(papago.papago(text_one.getText().toString(),language));
            text_lunchbox.setText(papago.papago(text_lunchbox.getText().toString(),language));
            text_salad.setText(papago.papago(text_salad.getText().toString(),language));
            dnlcltjfwjd.setText(papago.papago("↓ 나의 위치 설정 ↓",language));
            myvillage.setText(papago.papago(myvillage.getText().toString(),language));
            myhome.setText(papago.papago(myhome.getText().toString(),language));
            kfood.setText(papago.papago(kfood.getText().toString(),language));
            sheet.setText(papago.papago(sheet.getText().toString(),language));
        }
        else if(language.equals("chinese")){
            Dma.setText(papago.papago("돈",language));
            text_korean.setText(papago.papago(text_korean.getText().toString(),language));
            text_japanese.setText(papago.papago("일본음식",language));
            text_chinese.setText(papago.papago(text_chinese.getText().toString(),language));
            text_asian.setText(papago.papago(text_asian.getText().toString(),language));
            text_western.setText(papago.papago("서양음식",language));
            text_fast.setText(papago.papago(text_fast.getText().toString(),language));
            text_snackbar.setText(papago.papago(text_snackbar.getText().toString(),language));
            text_dessert.setText(papago.papago(text_dessert.getText().toString(),language));
            text_night.setText(papago.papago(text_night.getText().toString(),language));
            text_one.setText(papago.papago(text_one.getText().toString(),language));
            text_lunchbox.setText(papago.papago(text_lunchbox.getText().toString(),language));
            text_salad.setText(papago.papago(text_salad.getText().toString(),language));
            dnlcltjfwjd.setText(papago.papago("↓ 나의 위치 설정 ↓",language));
            myvillage.setText(papago.papago(myvillage.getText().toString(),language));
            myhome.setText(papago.papago(myhome.getText().toString(),language));
            kfood.setText(papago.papago(kfood.getText().toString(),language));
            sheet.setText(papago.papago(sheet.getText().toString(),language));
        }


    }

    public void NotificationSomethings(String text, String why) {


        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String check = why;
        Intent notificationIntent = new Intent(this, User16.class);
        notificationIntent.putExtra("notificationId", count); //전달할 값
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,  PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground)) //BitMap 이미지 요구
                .setContentTitle(text)
                .setContentText(why)
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



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.user_main_menu, menu );
        return true;

    }
        @Override
        public boolean onOptionsItemSelected (MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.usermain) {
                Intent settingIntent = new Intent( this, user06_mydata.class );
                startActivity( settingIntent );

            }
            return true;
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
           //     Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
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
