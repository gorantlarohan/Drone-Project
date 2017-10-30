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
import MainInterface.DisplayCrime;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EmergencyCop extends Cop{
	public String policeStationName;
	   DisplayCrime dc;
        public EmergencyCop(String name){
		this.policeStationName = name;
	}
	public void takeAction(String loc, String img,String city,String zone){

            dc=new DisplayCrime();
            for (int i = 0; i < crimes.length; i++) {
                if(zone.equals("east")&&crimes[i].equals("robery"))
                {
                   crimename=crimes[i];
                }
                else if(zone.equals("north")&&crimes[i].equals("killer"))
                {
                   crimename=crimes[i];
                }
                else if(zone.equals("south")&&crimes[i].equals("bomb threat"))
                {
                   crimename=crimes[i];
                }
                
               else if(zone.equals("west")&&crimes[i].equals("teasing"))
                {
                   crimename=crimes[i];
                }
               else{
                   continue;
               }
            }
            dc.crimeDisplay(img, crimename, loc, zone);
            dc.setVisible(true);
            System.out.println("\n--------------------- Emergency Cop Action --------------------- \n");
		System.out.println("Emergency cop is from " + policeStationName +" taking action on crime " + " in location --> " + loc  );
			
	}

	
	}

