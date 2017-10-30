/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceInterface;

/**
 *
 * @author LENOVO
 */

public interface FindLocation {
 public  String[] getLatLongPositions(String address)throws Exception;
 public String[] getLocation(String place);

}
