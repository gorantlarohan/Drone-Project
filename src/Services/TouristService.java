/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import ServiceInterface.FindLocation;
import Services.LocationService;

/**
 *
 * @author LENOVO
 */
public class TouristService {

   //linked with location service
   FindLocation findLocation=new LocationService();
   
    public String[] getLocation(String place) throws Exception
    {
        return findLocation.getLatLongPositions(place);
    }

}
