/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainDrone;

/**
 *
 * @author LENOVO
 */
public class Drone {
    String location;
    String latlog[];
    public String appKeyForPlaceService = "AIzaSyDS6FxE6gOGdUA6qo65wb79btiXksS5pA0";
  
            //AIzaSyC2fjTx97Ylcm3pm3NK4YQ2ME9nlhBKVaQ
    public String appKeyForDistService ="AIzaSyDuvXmB0klvifUEo01UxFyoDHuOrTB2dTw";
  //  AIzaSyB3xhWqSFqoijDObIqJ3qjhPn-lXx1Oua0
        

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String[] getLatlog() {
        return latlog;
    }

    public void setLatlog(String[] latlog) {
        this.latlog = latlog;
    }

   
}
