package com.example.java_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Document;

import api.FoodApi;
import object.Topic;

public class bt_3 extends AppCompatActivity {
    String[] rss = {"Proteins", "Grains and Starches", "Vitamins", "Amino acids", "Fats and Oils"};
    String[] sources = {
            "https://www.petfoodindustry.com/rss/topic/292-proteins",
            "https://www.petfoodindustry.com/rss/topic/294-grains-and-starches",
            "https://www.petfoodindustry.com/rss/topic/296-vitamins",
            "https://www.petfoodindustry.com/rss/topic/293-amino-acids",
            "https://www.petfoodindustry.com/rss/topic/300-fats-and-oils"
    };
    ListView listView;
    FoodApi fAPI;
    Topic topic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt3);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.single_list, this.rss);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    fAPI = new FoodApi();
                    Document doc = fAPI.execute(sources[i]).get();
                    topic = new Topic();
                    topic.parseData(doc);
                    topic.show();
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}