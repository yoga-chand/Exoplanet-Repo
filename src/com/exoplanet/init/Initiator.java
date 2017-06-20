package com.exoplanet.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * This class initializes the input data
 * @author Yoga
 *
 */
public class Initiator {

	/**
	 * 
	 * @returns the json string from the provided url
	 */
	public static String initializeInputData(){
		//loads the configuration
		String fileName = "config.properties";
		Properties properties = loadConfiguration(fileName);
		if(properties!=null && properties.getProperty("url")!=null){
			return loadExoplanetData(properties.getProperty("url"));
		}
		return null;
	}

	/**
	 * loads the configuration
	 * @returns Properties
	 */
	public static Properties loadConfiguration(String fileName){

		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = Initiator.class.getClassLoader().getResourceAsStream(fileName);
			if(input==null){
				System.out.println("Sorry, unable to find " + fileName);
				return null;
			}
			prop.load(input);
			
		} catch (IOException ex) {
			System.err.println("IOException while loading configuration"+ ex);
		} finally{
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					System.err.println("IOException while closing Inputstream"+ e);
				}
			}
		}
		return prop;
	}
	
	/**
	 * Method performs a rest call and gets the input json
	 * @param urlString
	 * @return jsonstring
	 */
	public static String loadExoplanetData(String urlString){
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(urlString);
			HttpURLConnection httpUrlConnection = (HttpURLConnection)url.openConnection();
			httpUrlConnection.setRequestMethod("GET");
			httpUrlConnection.setRequestProperty("Accept", "application/json");
			
			if (httpUrlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ httpUrlConnection.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
			String output;
			while((output = br.readLine())!=null){
				buffer.append(output);
			}
			
		} catch (MalformedURLException e) {
			System.err.println("MalformedURLException while invoking url "+urlString+" "+e);
		} catch (IOException e) {
			System.err.println("IOException loading input data "+e);
		}
		return buffer.toString();
		
	}
 
	
}