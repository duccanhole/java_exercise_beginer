package api;

import android.os.AsyncTask;

import org.w3c.dom.Document;

import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FoodApi extends AsyncTask<String, String, Document> {
    @Override
    protected Document doInBackground(String... sources) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            URL url = new URL(sources[0]);
            InputStream inputStream = url.openConnection().getInputStream();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(inputStream);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
