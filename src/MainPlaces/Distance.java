/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MainPlaces;

/**
 *
 * @author LENOVO
 */
import org.json.*;

public class Distance {

    private String distance;
    private String duration;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
//https://developers.google.com/maps/documentation/distance-matrix/
    public Distance Details(JSONObject info) {
        try {
            Distance result = new Distance();
            JSONObject elements = (JSONObject) info.getJSONArray("elements").get(0);
            JSONObject location = (JSONObject) elements.get("distance");
            JSONObject duration = (JSONObject) elements.get("duration");
            result.setDistance(location.getString("text"));
            result.setDuration(duration.getString("text"));
            return result;
        } catch (JSONException ex) {
              System.out.println(ex);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Distance is " + distance + " will take " + duration + ".";
    }
}
