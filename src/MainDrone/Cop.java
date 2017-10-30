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
import java.io.File;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cop extends Drone{
        DisplayCrime displayCrime=new DisplayCrime();
        String crimes[]={"robery","teasing","killer","bomb threat"};
        String crimename;
        String city;
	String zone;
	String dir="C:\\Users\\LENOVO\\Desktop\\crime_places";
        JFrame jf=new JFrame();
        JPanel jPanel=new JPanel();
        Scanner scanner=new Scanner(System.in);
	public void crimeDetails()
	{
		System.out.println("which city are you in ?");

		System.out.println("1.hyderabad"+"\t"+"2.mumbai"+"\t"+"3.delhi"+"\t"+"4.chennai");
		System.out.println("enter the city of duty (ex: pune)");

		city=scanner.next();

		System.out.println("which zone are you posted?");

		System.out.println("1.north"+"\t"+"2.south"+"\t"+"3.east"+"\t"+"4.west");
		System.out.println("enter your zone (ex: north)");

		zone=scanner.next();
		StringBuilder sb=new StringBuilder("");
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
	
	System.out.println("\n"+"=============please find the crime details================ ");
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
        if(ab.length>=2)
	{
		
            displayCrime.crimeDisplay(ab[rand.nextInt(2)], crimename,city,zone );
            System.out.println(ab[rand.nextInt(2)]		);
            displayCrime.setVisible(true);

	}
	else {
	    displayCrime.crimeDisplay(ab[0], crimename,city,zone );
            System.out.println(ab[rand.nextInt(2)]		);
            displayCrime.setVisible(true);

		System.out.println(ab[0]);
	}
	}
			
		
	}
