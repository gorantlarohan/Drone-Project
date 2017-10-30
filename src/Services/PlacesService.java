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
import MainPlaces.Places;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.*;

public class PlacesService extends Places {

    private String API_KEY;

    public PlacesService(String apikey) {
        this.API_KEY = apikey;
    }

    public void setApiKey(String apikey) {
        this.API_KEY = apikey;
    }

    public ArrayList<Places> findPlaces(double latitude, double longitude, String placeType) throws IOException {
       
        String urlString = buildUrl(latitude, longitude, placeType);

        try {
            String json = getJSON(urlString);
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("results");
            ArrayList<Places> arrayList = new ArrayList<Places>();
            for (int i = 0; i < array.length(); i++) {
                try {
                    Places place = new Places();
                    place = place.Details((JSONObject) array.get(i));
                    if (place.getName().toLowerCase().contains(placeType.toLowerCase())) {
                        arrayList.add(place);
                    }

                } catch (Exception e) {
                }
            }
           
           return arrayList;
           
        } catch (JSONException ex) {

        }
        return null;
    }
    //here are we are bulding url
    // https://maps.googleapis.com/maps/api/place/search/json?location=28.632808,77.218276&radius=500&types=atm&sensor=false&key=apikey
    private String buildUrl(double latitude, double longitude, String place) {
        StringBuilder urlString = new StringBuilder("https://maps.googleapis.com/maps/api/place/search/json?");

        if (place.equals("")) {
            urlString.append("&location=");
            urlString.append(Double.toString(latitude));
            urlString.append(",");
            urlString.append(Double.toString(longitude));
            urlString.append("&radius=6000");
            urlString.append("&types="+place);
            urlString.append("&sensor=false&key=" + API_KEY);
        } else {
            urlString.append("&location=");
            urlString.append(Double.toString(latitude));
            urlString.append(",");
            urlString.append(Double.toString(longitude));
            urlString.append("&radius=6000");
            urlString.append("&types=" + place);
            urlString.append("&sensor=false&key=" + API_KEY);
        }
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
