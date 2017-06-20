package com.exoplanet.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.exoplanet.model.Planet;

/**
 * 
 * @author Yoga
 * This class performs all the logic to obtain the results.
 *
 */
public class PlanetService {

	private List<Planet> orphanPlanets;
	
	private Planet hottestStarPlanet = new Planet();
	
	private Map<String,Map<String, Integer>> timeLineMap = new TreeMap<String, Map<String, Integer>>();
	
	/**
	 * This method converts the input json to planet objects
	 * @param inputData - inputjson from the url
	 * @return
	 */
	public List<Planet> loadPlanets(String inputData){

		ObjectMapper objMapper = new ObjectMapper();
		List<Planet> planetList = new ArrayList<Planet>();
		try {
			objMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			planetList = objMapper.readValue(inputData, new TypeReference<List<Planet>>(){});
		} catch (JsonParseException e) {
			System.err.println("JsonParseException while processing the input"+ e);
		} catch (JsonMappingException e) {
			System.err.println("JsonMappingException while processing the input"+e);
		} catch (IOException e) {
			System.err.println("IOException while processing the input"+e);
		}
		return planetList;
	}

	/**
	 * This method iterates the list of planets once to determine all the required values.
	 * @param inputPlanets - list of planets
	 */
	public void processPlanetInfo(List<Planet> inputPlanets){
		float maxHottestStar = 0.0F;
		int count;
		orphanPlanets = new ArrayList<Planet>();
		for(Planet planet : inputPlanets){
			//identifying the orphan planet using the type flag 3
			if(planet.getTypeFlag() == 3){
				orphanPlanets.add(planet);
			}
			if(planet.getHostStarTemp()>maxHottestStar){
				maxHottestStar = planet.getHostStarTemp();
				hottestStarPlanet = planet;
			}
			//not considering the planet input if there is no discovery year
			if(planet.getDiscoveryYear()== null || planet.getDiscoveryYear().isEmpty())
				continue;
			Map<String, Integer> sizeMap = timeLineMap.get(planet.getDiscoveryYear());
			if(sizeMap == null){
				sizeMap = new HashMap<String,Integer>();
				sizeMap.put("small", 0);
				sizeMap.put("medium", 0);
				sizeMap.put("large", 0);
			}
			if(planet.getRadiusJupiter()>0.0F && planet.getRadiusJupiter()<1.0F){
				count = sizeMap.get("small");
				sizeMap.put("small", ++count);
			}
			else if(planet.getRadiusJupiter()>1.0F && planet.getRadiusJupiter()<2.0F){
				count = sizeMap.get("medium");
				sizeMap.put("medium", ++count);
			}
			else if(planet.getRadiusJupiter()>2.0F){
				count = sizeMap.get("large");
				sizeMap.put("large", ++count);
			}
			timeLineMap.put(planet.getDiscoveryYear(), sizeMap);
		}
	}
	
	/**
	 * 
	 * @returns the number of orphan planets
	 */
	public int getNumberOfOrphanPlanets(){
		System.out.println("Number of orphan planets(no star) is "+getOrphanPlanets().size());
		return getOrphanPlanets().size();
	}
	
	/**
	 * 
	 * @returns the orphan planets
	 */
	public List<Planet> getOrphanPlanets(){
		return orphanPlanets;
	}
	
	/**
	 * 
	 * @returns the hottest star planet 
	 */
	public Planet getHottestStarPlanet(){
		return hottestStarPlanet;
	}
	
	/**
	 * 
	 * @returns the hottest star planet name
	 */
	public String getHottestStarPlanetName(){
		System.out.println("Planet orbitting the hottest star "+getHottestStarPlanet().getPlanetIdentifier());
		return getHottestStarPlanet().getPlanetIdentifier();
	}
	
	/**
	 * 
	 * @returns the timeline of the planets 
	 */
	public Map<String, Map<String, Integer>> getTimeLine(){
		
		for(Map.Entry<String, Map<String,Integer>> entry :timeLineMap.entrySet()){
			Map<String, Integer> sizeMap = entry.getValue();
			System.out.println(sizeMap.toString());
			System.out.println("In "+entry.getKey()+" we discovered "+sizeMap.get("small") +" small planets, "+sizeMap.get("medium")+" medium planets, "+sizeMap.get("large")+" large planets.");
		}
		return timeLineMap;
	}
	
}

