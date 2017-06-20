package com.exoplanet.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.exoplanet.init.Initiator;
import com.exoplanet.model.Planet;
import com.exoplanet.service.PlanetService;

/**
 * 
 * @author Yoga
 * Test class to validate exoplanet functionalities
 */
public class ExoplanetTest  {
	
	private static List<Planet> planetList;
	
	private static PlanetService planetService = new PlanetService();
	
	private static Properties properties;
	
	/*
	 * This method initializes the input 
	 */
	@BeforeClass
	public static void initializeData() throws Exception {
		String input = Initiator.initializeInputData();
		properties = Initiator.loadConfiguration("testdata.properties");
		planetList = planetService.loadPlanets(input);
	}
	
	@Before
	public void processPlanetInfoTest(){
		planetService.processPlanetInfo(planetList);
	}
	
	@Test
	public void testOrphanPlanetCount(){
		System.out.println("Running test case for count of orphan planets");
		assertEquals(planetService.getNumberOfOrphanPlanets(), Integer.parseInt(properties.getProperty("orphanplanetscount")));
	}
	
	@Test
	public void testHottestStarPlanet(){
		System.out.println("Running test case for hottest star planet");
		assertEquals(planetService.getHottestStarPlanetName(), properties.getProperty("hotteststarplanetname"));
	}
	
	@Test
	public void testTimeline(){
		System.out.println("Running test case for validating timeline");
		//Map<String, Integer> timeLineMap = planetService.getTimeLine().get(key);
		assertEquals(planetService.getTimeLine().get(properties.getProperty("discoveryyear")).toString(), properties.getProperty("timeline"));
	}
}
