package com.example.delivered;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class user27 extends AppCompatActivity {
    Button button1;
    Button button2;
    Button inquiry_wkr;
    TextView snrn;
 ListView inquiry_listview;
    private Connection conn = null;
    private ResultSet rs = null;
    private String sql;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    SimpleAdapter AD;
   ListView LV_Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.user27 );



        LV_Data = (ListView) findViewById( R.id.inquiry_listview);

        inquiry_wkr = (Button) findViewById(R.id.inquiry_wkr);
        inquiry_wkr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),user27_wkr.class);
                startActivity(intent);
                finish();
            }
        });



        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        final String userid = pref.getString("id","");


    List<Map<String, String>> MalayaList = null;
    user27_ListView myData = new user27_ListView();



    MalayaList=myData.getdata(userid);


    final String[] fromwhere = {"문의번호","제목", "내용","답변"};
    final int[] viewhere = {R.id.questionnumber1, R.id.questiotit1, R.id.questiostr, R.id.questiokm};

    AD = new SimpleAdapter( user27.this, MalayaList, R.layout.user27_listvilew, fromwhere, viewhere);

        LV_Data.setAdapter(AD);
        LV_Data.setOnItemClickListener(new AdapterView.OnItemClickListener() {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            Intent intent = new Intent(getApplicationContext(), user27_where.class);



            String bbb = AD.getItem(position).toString();
            String data = bbb.substring(bbb.lastIndexOf("문의번호")+5,bbb.length()-1);


            intent.putExtra("user27mknumber",data);


            startActivity(intent);

finish();



        }


    });






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




}
