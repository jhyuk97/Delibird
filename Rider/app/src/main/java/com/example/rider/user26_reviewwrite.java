package com.example.rider;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class user26_reviewwrite extends AppCompatActivity {
    TextView TitleName;
    RatingBar user26_ratingbar;
    EditText user26_EditText;
    Button Insert_IMG;
    Button Complete;
    TextView addr;
    TextView nothing;

    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;

    private static final int PICK_FROM_ALBUM = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user26_reviewwrite);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#4E8DF5")));
        papago papago = new papago();

        TitleName = (TextView)findViewById(R.id.reviewfoood);
        user26_ratingbar = (RatingBar)findViewById(R.id.reviewBar5);
        user26_EditText = (EditText)findViewById(R.id.reviewpa);
        Insert_IMG = (Button)findViewById(R.id.review_IMG);
        Complete = (Button)findViewById(R.id.review_ch);
        addr = (TextView)findViewById(R.id.IMG_address);
        nothing = (TextView)findViewById(R.id.nothing);

        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        final String id = pref.getString("id","");
        String language = pref.getString("language","");
        Intent intent = getIntent();
        final String OrderNum = intent.getStringExtra("OrderNum");
        TitleName.setText(OrderNum);

        nothing.setText(papago.papago(nothing.getText().toString(),language));
        Complete.setText(papago.papago(Complete.getText().toString(),language));
        Insert_IMG.setText(papago.papago(Insert_IMG.getText().toString(),language));
        user26_EditText.setText(papago.papago(user26_EditText.getText().toString(),language));

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        final String getTime = simpleDate.format(mDate);

        tryConnect(true);

        Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String sql = "update 주문 set 현재상황 = '리뷰작성완료' where 주문번호 = " + OrderNum;
                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();

                    sql = "insert into 리뷰 values(" + OrderNum + ", '" + id + "', (select 매장번호 from 주문 where 주문번호 = " + OrderNum + "), '', '" + addr.getText() + "', '" + user26_EditText.getText() + "', '" + getTime + "','', " + user26_ratingbar.getRating() + ")";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        Insert_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTakeAlbumAction();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Toast.makeText(getBaseContext(), "resultCode : "+resultCode,Toast.LENGTH_SHORT).show();

        if(requestCode == PICK_FROM_ALBUM)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    String name_Str = getImageNameToUri(intent.getData());

                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(getContentResolver(), intent.getData());
                    ImageView image = (ImageView)findViewById(R.id.user26_ImageView);

                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);

                    addr = (TextView)findViewById(R.id.IMG_address);
                    addr.setText(name_Str);


                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();

                } catch (Exception e){
                    e.getMessage();
                }
            }
        }
    }

    public void doTakeAlbumAction() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent,PICK_FROM_ALBUM);
    }


    public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        return imgPath;
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
