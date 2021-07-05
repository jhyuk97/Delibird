package com.example.delivered;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class app_choice extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    TextView snrn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_choice);
        button2 = (Button) findViewById(R.id.btn_user_mode);
        button1 = (Button) findViewById(R.id.btn_rider_mode);
        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );
        snrn= (TextView) findViewById(R.id.snrn);
        papago papago = new papago();
        String language = pref.getString("language","");
        button1.setText(papago.papago(button1.getText().toString(),language));
        button2.setText(papago.papago(button2.getText().toString(),language));
        snrn.setText(papago.papago(snrn.getText().toString(),language));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ridar_member .class);
                //intent.putExtra("addr","");
                startActivity(intent);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), app_signup  .class);
                startActivity(intent);

            }
        });

    }
}
