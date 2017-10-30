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
import MainDrone.Admin;
import ServiceInterface.Report;

public class ReportService implements Report {
	Admin ad = new Admin();
        @Override
	public void sendReport(String loc, String image,String city, String zone) {
	    try {
	        ad.getReports(loc, image,city,zone);
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	    }
	    }
}
