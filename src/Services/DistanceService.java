/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author LENOVO
 */
import MainPlaces.Distance;
import MainPlaces.Places;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DistanceService {

    private String API_KEY;

    public DistanceService(String apikey) {
        this.API_KEY = apikey;
    }

    public void setApiKey(String apikey) {
        this.API_KEY = apikey;
    }

    public Distance findDistance(String origins, String destination) throws IOException {
        String urlString = makeUrl(origins, destination);

        try {
            String json = getJSON(urlString);
//            System.out.println(json);
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("rows");//rows
//            System.out.println(array);
            Distance dis = new Distance();
            for (int i = 0; i < array.length(); i++) {
                try {
                    dis = dis.Details((JSONObject) array.get(i));
//                    System.out.println(dis.toString());
                } catch (Exception e) {
                }
            }
            return dis;
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    // https://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC|Seattle&destinations=San+Francisco|Victoria+BC&key=YOUR_API_KEY
    private String makeUrl(String origins, String destinations) {
        StringBuilder urlString = new StringBuilder("https://maps.googleapis.com/maps/api/distancematrix/json?");
        urlString.append("&origins=");
        urlString.append(origins);
        urlString.append("&destinations=");
        urlString.append(destinations);
        urlString.append("&key=" + API_KEY);
        return urlString.toString();
    }

    protected String getJSON(String url) throws MalformedURLException, IOException {
        return getUrlContents(url);
    }

    private String getUrlContents(String theUrl) throws MalformedURLException, IOException {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()), 8);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
