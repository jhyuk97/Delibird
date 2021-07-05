package com.example.rider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;


public class SearchAddress extends AppCompatActivity {
    Button button1;
    Button button2;
    private WebView webView;
//    public TextView sig_tt;

    public TextView  txt_address;
    private Handler handler;
    public TextView  text1;
    private Connection conn ;
    public int rs;
    private Statement stmt ;
    private PreparedStatement pstmt ;
    private static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));
        mContext = getApplicationContext();
       // sig_tt = findViewById(R.id.sig_tt);
       txt_address = findViewById(R.id.txt_address);
        // WebView 초기화
        init_webView();

        // 핸들러를 통한 JavaScript 이벤트 반응
        handler = new Handler();

    }

    public void init_webView() {
        // WebView 설정
       webView = (WebView) findViewById(R.id.webView_address);

        // JavaScript 허용
        webView.getSettings().setJavaScriptEnabled(true);

        // JavaScript의 window.open 허용
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        webView.addJavascriptInterface(new AndroidBridge(), "TestApp");

        // web client 를 chrome 으로 설정
        webView.setWebChromeClient(new WebChromeClient());

        // webview url load. php 파일 주소
        webView.loadUrl("http://10.0.2.2:8020/Delibird/admin/php.jsp");

    }


    private class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Intent intent = getIntent(); /*데이터 수신*/
                    text1 = (TextView) findViewById(R.id.reea);

                    int age = intent.getExtras().getInt("for"); /*int형*/
                    text1.setText(String.valueOf(age)); // 5

                    if (age == 20 ) {
                        txt_address.setText( String.format("%s %s %s",arg1 ,arg2, arg3 ) );
                        global data = (global) getApplication();
                        data.setAddress( txt_address.getText().toString() );
                        init_webView();
                        finish();

                    }
                    else if(age == 100){
                        if (tryConnect( true )) {
                            String sql;
                            SharedPreferences pref = getSharedPreferences("reiderID",MODE_PRIVATE);
                            final String reiderID = pref.getString("reiderID","");
                            txt_address.setText( String.format( "%s %s %s", arg1, arg2, arg3 ) );

                            try {
                                sql = "update 라이더 set 도로명주소 = '" + txt_address.getText().toString() + "'  where ID =  '" + reiderID + "'  ";
                                stmt = conn.createStatement();
                                stmt.executeUpdate(sql);

                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                            init_webView();

                            Intent Intent;
                            Intent = new Intent(getApplicationContext(), datechange.class);
                            startActivity(Intent);

                        }

                        finish();
//                sql = "update 주문 set 현재상황 = '조리중' where 주문번호 = " + number;

                    }
                    else {
                        if (tryConnect( true )) {

                            String sql;
                            SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
                            final String userid = pref.getString( "id", "" ); //유저 아이디  가져오는것
                            double lat = 0;
                            double lng = 0;

                            txt_address.setText( String.format( "%s %s %s", arg1, arg2, arg3 ) );


                            //좌표시작
                            Geocoder coder = new Geocoder(mContext);

                            try {
                                List<Address> addrList = coder.getFromLocationName(txt_address.getText().toString(),1);
                                Iterator<Address> addrs = addrList.iterator();

                                String infoAddr = "";

                                while(addrs.hasNext()) {
                                    Address loc = addrs.next();
                                    infoAddr += String.format("Coord : %f, %f", loc.getLatitude(), loc.getLongitude());
                                    lat = loc.getLatitude();//x
                                    lng = loc.getLongitude();//y 좌표까지 가져왔음
                                    //여기서 이제 버튼눌러서 회원가입할때 데이터 들어가는거로 만들면됨,
                                }

                            } catch (Exception e) {
                                String aaa  = e.getMessage();
                                Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생" + aaa);

                            }//좌표끝

                            sql = "update 사용자 set 도로명주소 = '" + txt_address.getText().toString() + "', x좌표 = "+ lat +", y좌표 = "+ lng +"  where ID =  '" + userid + "'  ";
                            try {

                                stmt = conn.createStatement();
                                stmt.executeUpdate(sql);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            init_webView();
                        }
                        finish();

                    }
                }
            });

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
                //     Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (Exception e) {
            if (showMessage)
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
    }


}

