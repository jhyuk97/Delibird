package com.example.delivered;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class user23_adapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<user23_data> data;
    TextView title;
    TextView story;
    RatingBar ratingBar1;
    ImageView img;

    public user23_adapter(Context mContext, ArrayList<user23_data> data) {
        this.mContext = mContext;
        this.data = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.user23_acc,null);
        title = (TextView)view.findViewById(R.id.user23_title);
        story = (TextView)view.findViewById(R.id.user23_story);
        ratingBar1 = (RatingBar)view.findViewById(R.id.user23_rating);
        img = (ImageView)view.findViewById(R.id.user23_img);

        title.setText(data.get(position).getTitle());
        story.setText(data.get(position).getStory());

        ratingBar1.setRating(data.get(position).getRating());
        String aaaa = data.get(position).getImg();
        Bitmap bitmap = BitmapFactory.decodeFile(data.get(position).getImg());

        img.setImageBitmap(bitmap);

        SharedPreferences pref = mContext.getSharedPreferences("pref", MODE_PRIVATE);
        papago papago = new papago();
        String language = pref.getString("language","");

        title.setText(papago.papago(title.getText().toString(),language));
        story.setText(papago.papago(story.getText().toString(),language));







        return view;
    }
}
