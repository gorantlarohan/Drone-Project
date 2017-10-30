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

import ServiceInterface.FindLocation;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class LocationService implements FindLocation
{
  
@Override
  public  String[] getLatLongPositions(String address) throws MalformedURLException, UnsupportedEncodingException, ParserConfigurationException, SAXException, IOException, XPathExpressionException, Exception 
  {
      //reference from: http://www.santhoshreddymandadi.com/java/java-program-to-get-latitude-longitude-points.html
    int responseCode = 0;
    String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
    URL url = new URL(api);
    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
    httpConnection.connect();
    responseCode = httpConnection.getResponseCode();
    if(responseCode == 200)
    {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = builder.parse(httpConnection.getInputStream());
    
      XPathFactory xPathfactory = XPathFactory.newInstance();
      XPath xpath = xPathfactory.newXPath();
      XPathExpression expr = xpath.compile("/GeocodeResponse/status");
      String status = (String)expr.evaluate(document, XPathConstants.STRING);
      if(status.equals("OK"))
      {
         expr = xpath.compile("//geometry/location/lat");
         String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
         expr = xpath.compile("//geometry/location/lng");
         String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
         return new String[] {latitude, longitude};
      }
      else
      {
         throw new Exception("Error from the API - response status: "+status);
      }
    }
    return null;
  }    

@Override
public String[] getLocation(String place)
{
    try {
        return getLatLongPositions(place);
    } 
    catch (ParserConfigurationException ex) {
        Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SAXException ex) {
        Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
    } catch (XPathExpressionException ex) {
        Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}
}
