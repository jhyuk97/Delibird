package com.example.rider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class User12_MainActivity extends AppCompatActivity {
    User12_review fragment1;
    User12_grade fragment2;
    User12_distance fragment3;
    TextView dnflehdspcncjs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user12_activity_main);
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        String userid = pref.getString("id","");
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar1);


        dnflehdspcncjs = (TextView) findViewById(R.id.dnflehdspcncjs);
        fragment1 = new User12_review();
        fragment2 = new User12_grade();
        fragment3 = new User12_distance();

        initFragment();

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                if(tabId == R.id.tab_review){
                    transaction.replace(R.id.frameLayout, fragment1).commit();
                }else if(tabId == R.id.tab_grade){
                    transaction.replace(R.id.frameLayout, fragment2).commit();
                }

            }
        });


        papago papago = new papago();
        String language = pref.getString("language","");

        dnflehdspcncjs.setText(papago.papago("주변 추천",language));




}
public void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, fragment1);
        transaction.addToBackStack(null);
        transaction.commit();
}
}
