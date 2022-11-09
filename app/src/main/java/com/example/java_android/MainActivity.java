package com.example.java_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

// EXERCISE 1
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                changeBackgroundColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                changeBackgroundColor();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                changeBackgroundColor();
            }
        });
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.info:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Dialog example").setTitle("Dialog title").setCancelable(false).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setTitle("Example");
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void changeBackgroundColor() {
        String[] colors = {
                "#39add1", // light blue
                "#3079ab", // dark blue
                "#c25975", // mauve
                "#e15258", // red
                "#f9845b", // orange
                "#838cc7", // lavender
                "#7d669e", // purple
                "#53bbb4", // aqua
                "#51b46d", // green
                "#e0ab18", // mustard
                "#637a91", // dark gray
                "#f092b0", // pink
                "#b7c0c7",
                "#d4e157",
                "#6d4c41"
        };
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            LinearLayout l = (LinearLayout) linearLayout.getChildAt(i);
            for (int j = 0; j < l.getChildCount(); j++) {
                int randomIndex = (int) (Math.random() * colors.length);
                GradientDrawable g = (GradientDrawable) l.getChildAt(j).getBackground();
                g.setColor(Color.parseColor(colors[randomIndex]));
            }
        }
    }
}