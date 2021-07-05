package com.example.rider;

import android.os.Bundle;
import android.widget.ListView;

import androidx.fragment.app.FragmentActivity;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user16);

        /*String[] items = {"매장1", "매장2", "매장3", "매장4"};
        ListAdapter adapter = new listadapter(this, items);
        ListView listView = (ListView) findViewById(R.id.favor_list);
        listView.setAdapter(adapter);*/

        String[] items2 = {"매장1","매장2","매장3","매장4","",":","","",":","","","34","35"};
        orderlistadapter order = new orderlistadapter(this, items2);
        ListView listView1 = (ListView)findViewById(R.id.order_List);
        listView1.setAdapter(order);

    }
}
