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
import ServiceInterface.Report;
import MainPlaces.*;
import Services.*;
import java.util.*;
import Services.*;
import java.io.File;
import java.io.IOException;

public class TouristGuide extends Drone {

        String city,zone;
	int choose;
	String[] placeName;
	String interest;
	Report report = new ReportService();
	Scanner scanner = new Scanner(System.in);
	TouristService touristService = new TouristService();
	double lat, lot;
	PlacesService placesService;
	String origins;
	Places placeDetail;
	List<Places> findplaces;
	List<Places> filterplaces = new ArrayList<Places>();
	String destinations;
        boolean loop=true;

	public void touristGuide() throws Exception {
		System.out.println("Welcome to Tourist Guide service.....");
		System.out.println("In which city are you?");
		city=scanner.next();
		System.out.println("Which zone you are in "+city);
		System.out.println("1.north"+"\n"+"2.south"+"\n"+"3.east"+"\n"+"4.west");
		
                System.out.println("enter the value (ex:north)");
		zone=scanner.next();
		
                System.out.println("enter the area in "+city);
                scanner.nextLine();
		location = scanner.nextLine();
		latlog = touristService.getLocation(location);//array to store latitude and longitude 
		lat = Double.parseDouble(latlog[0]);
		lot = Double.parseDouble(latlog[1]);
	

		placesService = new PlacesService(appKeyForPlaceService);

	while(loop) {
	System.out.println("select your place of interest..."+"\n"+"1.restaurant"+"\n"+"2. hospital"+"\n"+"3. cafe"+"\n"+"4. adventure"+"\n"+"5. school");
		
	choose = scanner.nextInt();
		switch (choose) {
		case 1:
		interest="restaurant";
                break;
		case 2:
		interest="hospital";
                break;
		case 3:
		interest="cafe";
		break;
		case 4:
		interest="park";
		break;
                case 5:
		interest="school";
		break;
		default:System.out.println("please enter a valid choice");
		break;
		}
		findplaces = placesService.findPlaces(lat, lot, interest);
		placeName = new String[findplaces.size()];
		double rating[] = new double[100];

		if (findplaces.isEmpty()) {
			System.out.println("Sorry. interest of place is invalid. Try again");
		} else {

			//This code is for best  rated places
			for (int i = 0, j=0; i < findplaces.size(); i++) {
				placeDetail = findplaces.get(i);
				if (placeDetail.getRating() != null){
					rating[j] = placeDetail.getRating();
					j++;
				}
				if(j >= 30){
					break;
				}
			}
			for (int j = 0; j < rating.length; j++) {
				for (int k = j + 1; k < rating.length; k++) {
					if (rating[j] < rating[k]) {
						Double tem = rating[j];
						rating[j] = rating[k];
						rating[k] = tem;
					}
				}
			}

			try {
			for (int i = 0, j = 0; i < findplaces.size(); i++) {
				placeDetail = findplaces.get(i);
				if(placeDetail.getRating()!=null) {
				if (placeDetail.getRating() == rating[j]) {
						filterplaces.add(placeDetail);
						j++;
				
				}
				}
				if(j >= 3){
					break;
				}
			}
			}
			catch (Exception e) {
                            System.out.println(e.getMessage());
			}
		
			
			System.out.println("\n\n******** Top Rated Places Near You ********\n\n");
			for (int i = 0; i < filterplaces.size(); i++) {
				placeDetail = filterplaces.get(i);
				System.out.println("Place -->" + placeDetail.getName() + "   rating -->" + placeDetail.getRating()+"  landmark-->"+placeDetail.getVicinity());
				destinations = placeDetail.getLatitude() + "," + placeDetail.getLongitude();
				origins = latlog[0] + "," + latlog[1];
				System.out.println(findDistanceAPI(origins, destinations).toString());
				System.out.println("\n");
			}
			System.out.println("\n----------------------------------------------------------\n");
	filterplaces.clear();
		System.out.println("have any places to visit ? "+"\n"+"1.yes"+"\n"+"2.no");
	choose=scanner.nextInt();
	
	switch (choose) {
	case 1:break;
	case 2:loop=false;
	break;
	default:
		break;
	}
		}



		}
		System.out.println("Drone:Will you help me if you find any crimes?"+"\n"+"1.yes"+"\n"+"2.no");
		choose=scanner.nextInt();
		if(choose==1)
		{

			System.out.println("Crimes found?");

			if (city.toLowerCase().equals("delhi") || city.toLowerCase().equals("mumbai") || city.toLowerCase().equals("hyderabad") || city.toLowerCase().equals("chennai")){
				{
                                    System.out.println("yes");
				StringBuilder sb=new StringBuilder("");
				String dir="C:\\Users\\LENOVO\\Desktop\\crime_places";

				File file = new File(dir);
				String[] names = file.list();
				File arr[]=new File[names.length];
			for(int i=0;i<names.length;i++)
			{
			String imageFolder = dir+"\\"+names[i];
			arr[i]=new File(imageFolder);
			String[] imageNames= arr[i].list();
			for(int j=0;j<imageNames.length;j++)
			{
				String folderPath = imageFolder + "\\" + imageNames[j];
				File f=new File(folderPath);
				String imgs[]=f.list();
				for(int k=0;k<imgs.length;k++)
				{
					String path=folderPath+"\\"+imgs[k];
					sb.append(path);
					sb.append(",");
					
				}

			}
			}
			String crimeImage[]=sb.toString().split(",");
			
				System.out.println("\n========= Emergency cop is on his way. He is coming for rescue.... =========\n");
				Random rand = new Random();
				int count=0;
             			StringBuilder sb1=new StringBuilder();
				for(int i=0;i<crimeImage.length;i++)
				{
			if(crimeImage[i].contains(city)&&crimeImage[i].contains(zone)) {
				sb1.append(crimeImage[i]);
				sb1.append(",");
				count++;
			}
					}
			String ab[]=	sb1.toString().split(",");
				if(ab.length>=2)
				{
					System.out.println(ab[rand.nextInt(2)]);
                                        report.sendReport(location.toLowerCase(), crimeImage[rand.nextInt(2)],city,zone);

				}
				else {
				
					System.out.println(ab[rand.nextInt(0)]);
                                        report.sendReport(location.toLowerCase(), crimeImage[rand.nextInt(0)],city,zone);
				}
			}

		}else {
			System.out.println("no");
		}
			
		}
		else {
                    
return;
		}
                	System.out.println("----------tourist service is ended-----------");
	
		}


	public Distance findDistanceAPI(String origins, String destination) throws IOException, Exception {
		DistanceService ds = new DistanceService(appKeyForDistService);
		return ds.findDistance(origins, destination);
	}
}


