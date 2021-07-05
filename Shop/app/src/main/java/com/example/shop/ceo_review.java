package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Map;

public class ceo_review extends AppCompatActivity {
    Button btn_Get;
    ListView List;

    SimpleAdapter AD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ceo_review);


        List = (ListView) findViewById(R.id.review_management_List_view);


        List<Map<String, String>> MalayaList = null;

        ceo_review_ListView myData = new ceo_review_ListView();

        MalayaList = ceo_review_ListView.getdata();


        final String[] fromwhere = {"리뷰번호", "사용자ID", "제목", "등록일"};

        final int[] viewhere = {R.id.ReviewNumber, R.id.UserID, R.id.ReviewName, R.id.Reviewday};

        AD = new SimpleAdapter(ceo_review.this, MalayaList, R.layout.ceo_review_listview, fromwhere, viewhere);


        List.setAdapter(AD);

        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              //  Intent intent = new Intent(getApplicationContext(),ceo_review_Answer.class);

                Intent intent = new Intent(getApplicationContext(), ceo_review_Answer.class);

                String number = AD.getItem(position).toString();
                String data = number.substring(number.lastIndexOf("리뷰번호")+5,number.indexOf(",",number.lastIndexOf("리뷰번호")+5));
                intent.putExtra("aaa", data);

                startActivity(intent);


            }


        });
    }
}


