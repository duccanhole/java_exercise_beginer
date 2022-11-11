package com.example.java_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter sa;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        HashMap<String, String> item_test = new HashMap<String, String>();
        item_test.put("title", "aaaa");
        item_test.put("content", "lorem ipsum");
        list.add(item_test);
        sa = new SimpleAdapter(this, list, R.layout.list_item, new String[]{"title", "content"}, new int[]{R.id.title, R.id.content});
        listView = findViewById(R.id.list_view);
        listView.setAdapter(sa);
    }
}