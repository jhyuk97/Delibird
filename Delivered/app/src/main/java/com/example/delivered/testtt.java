package com.example.delivered;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class testtt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testtt);
        ImageView img = (ImageView)findViewById(R.id.testImg);
        Bitmap bit = BitmapFactory.decodeFile("@drawable/cap2");
        img.setImageBitmap(bit);
    }
}
