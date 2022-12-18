package com.example.java_android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import api.LoadImage;
import object.Topic;


public class DialogContent extends DialogFragment {
    private Topic topic;

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        try {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View v = inflater.inflate(R.layout.dialog_content, null);
            builder.setView(v)
                    .setPositiveButton("Back", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            DialogContent.this.getDialog().cancel();
                        }
                    }).setNegativeButton("More", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            TextView title = v.findViewById(R.id.dialog_title);
            TextView description = v.findViewById(R.id.dialog_description);
            ImageView image = v.findViewById(R.id.dialog_image);
            title.setText(topic.getTitle());
            description.setText("asdadsa");
            new LoadImage(image).execute(topic.getImageSource());
            image.setImageBitmap(getBitmapFromURL(topic.getImageSource()));

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage() + e.getCause());
        }
        return builder.create();
    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
//            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
//            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
//            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}
