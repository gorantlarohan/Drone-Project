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

public class Places {

 
    private String name;
    private Double rating;

    private String vicinity;
    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public  Places Details(JSONObject info) {
        try {
            Places result = new Places();
            JSONObject geometry = (JSONObject) info.get("geometry");
            JSONObject location = (JSONObject) geometry.get("location");
            result.setLatitude((Double) location.get("lat"));
            result.setLongitude((Double) location.get("lng"));
            result.setName(info.getString("name"));
            result.setVicinity(info.getString("vicinity"));
            if(info.has("rating"))
                result.setRating((Double) info.get("rating"));
            return result;
        } catch (JSONException ex) {
           System.out.println(ex);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Place{" +/* "id=" + id + ", icon=" + icon +*/ ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", Rating=" + rating + '}';
    }

}

/* public String getId() {
        return id;
    }
// private String id;
   // private String icon;
// result.setIcon(info.getString("icon"));
// result.setId(info.getString("id"));
    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }*/