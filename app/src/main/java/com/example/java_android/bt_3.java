package com.example.java_android;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt3);
        fragmentManager = getSupportFragmentManager();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.single_list, this.rss);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDetail(i);
            }
        });
    }
    public void showDetail(int i) {
        try {
            fAPI = new FoodApi();
            Document doc = fAPI.execute(sources[i]).get();
            topic = new Topic();
            topic.parseData(doc);
            topic.show();
            DialogContent dContent = new DialogContent();
            dContent.setTopic(topic);
            dContent.show(getFragmentManager(), "detail");
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage() + e.getCause());
        }
    }
}