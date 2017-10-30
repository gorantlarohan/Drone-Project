/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainInterface;

/**
 *
 * @author LENOVO
 */
import MainDrone.*;
import java.io.Console;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class LoginAuthentication extends Drone {
    public void validate(String username, String password) throws Exception {
        if (username.equals("guide") && password.equals("guide")) {
        	TouristGuide tourist = new TouristGuide();
        	tourist.touristGuide();
        }
        else if(username.equals("cop") && password.equals("cop")){
        	System.out.println("\nSuccessfully logged in. Welcome to Cop...");
                Cop cop=new Cop();
                cop.crimeDetails();
        
        }
        else{
        	System.out.println("\nPlease provide correct username and password");
        }
        
    }

    public static void main(String[] args) throws Exception {
    	
        Scanner scanner = new Scanner(System.in);
        String username, password;
        System.out.print("username:");
        username=scanner.next();
        System.out.print("password:");
     //   password=scanner.next();
       Console console = System.console(); 
        if( System.console() == null ) 
		{
		  final JPasswordField pf = new JPasswordField();
		  password = JOptionPane.showConfirmDialog(null, pf, "Enter password",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION ? new String( pf.getPassword() ) : "";
                
                }
        else{
        	char[] passwordChar = console.readPassword();
        	password = passwordChar.toString();
        }
        
        LoginAuthentication login = new LoginAuthentication();
        login.validate(username, password);

    }

}
