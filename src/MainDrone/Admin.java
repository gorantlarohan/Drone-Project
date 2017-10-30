 package MainDrone;

import java.util.List;

import MainPlaces.Distance;
import MainPlaces.Places;
import Services.DistanceService;
import Services.LocationService;
import Services.PlacesService;

public class Admin extends Drone{

	EmergencyCop ecop;
	PlacesService placeService;
	List<Places> findEmePolices;
	Places emeLoc;
	DistanceService ds = new DistanceService(appKeyForDistService);
	double[] distanceKM = new double[30];
	double resultKM = 0;
	String timeTakenInMin;
	LocationService ls = new LocationService();
	String origins;
	String destinations;
	Distance result;
	boolean flag = false;
	
	public void checkNearestCop(String loc, String image,String city,String zone) throws Exception {
		System.out.println("Searching... Nearest emergency cop");
		placeService = new PlacesService("AIzaSyC2fjTx97Ylcm3pm3NK4YQ2ME9nlhBKVaQ");
		String[] latlog = ls.getLocation(loc);
		findEmePolices = placeService.findPlaces(Double.parseDouble(latlog[0]), Double.parseDouble(latlog[1]), "police");
		origins = latlog[0] + "," + latlog[1];
		for(int i = 0; i < findEmePolices.size(); i++){
			emeLoc = findEmePolices.get(i);
			destinations = emeLoc.getLatitude() + "," + emeLoc.getLongitude();
			result = ds.findDistance(origins, destinations);
			distanceKM[i] = Double.parseDouble(result.getDistance().substring(0, 3));
		}
		for (int j = 0; j < 30; j++) {
			for (int k = j + 1; k < distanceKM.length; k++) {
				if (distanceKM[j] > distanceKM[k]) {
					Double tem = distanceKM[j];
					distanceKM[j] = distanceKM[k];
					distanceKM[k] = tem;
				}
			}
		}
		for (int j = 0; j < 30; j++) {
			if(resultKM != distanceKM[j]){
				resultKM = distanceKM[j];
				break;
			}
		}
		for(int i = 0; i < findEmePolices.size(); i++){
			emeLoc = findEmePolices.get(i);
			destinations = emeLoc.getLatitude() + "," + emeLoc.getLongitude();
			result = ds.findDistance(origins, destinations);
			if(resultKM == Double.parseDouble(result.getDistance().substring(0, 3))){
				System.out.println("Found nearest cop place -->" + findEmePolices.get(i).getName() + " with distance --> " + resultKM);
				ecop = new EmergencyCop(findEmePolices.get(i).getName());
				flag = true;
				break;
			}
		}
		if(flag){
			ecop.takeAction(loc, image,city,zone);
		}
		else{
			System.out.println("Sorry.. Didn't find nearest cop");
		}
		
	}

	public void getReports(String loc, String image,String city,String zone) throws Exception {
		//System.out.println("crime location ---> " + loc + "---> " + image);
                System.out.println("crime location ---> " + loc );
		checkNearestCop(loc, image,city,zone);
	}
}
