package api;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class CivicApi extends AsyncTask<Void, Void, Void> {
    private String url = "https://www.googleapis.com/civicinfo/v2/representatives?key=AIzaSyCpWaLlomJIwts8lMJU-Z4B7GgRMH5ufJQ&address=address=1263%20Pacific%20Ave.%20Kansas%20City%20KS";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://www.googleapis.com/civicinfo/v2/representatives?key=AIzaSyCpWaLlomJIwts8lMJU-Z4B7GgRMH5ufJQ&address=address=1263%20Pacific%20Ave.%20Kansas%20City%20KS");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestMethod("GET");
            int resStatus = httpCon.getResponseCode();
            System.out.println("status code: "+ resStatus);
            String inputline;
            StringBuffer response = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            while ((inputline = in.readLine()) != null) {
                response.append(inputline);
            }
            in.close();
            JSONObject resData = new JSONObject(response.toString());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

class Officals {
    private String name, party;
    private String[] phones, urls;
    private Map<String, String> address;
    public Officals() {}
    public Officals(String name, String party, Map<String, String> address, String[] urls) {
        this.name = name;
        this.party = party;
        this.urls = urls;
        this.address = address;
    }

    public void setAddress(Map<String, String> address) {
        this.address = address;
    }
}
