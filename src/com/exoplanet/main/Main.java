package com.exoplanet.main;

import java.util.List;

import com.exoplanet.init.Initiator;
import com.exoplanet.model.Planet;
import com.exoplanet.service.PlanetService;

/**
 * 
 * @author Yoga
 * Main class which takes the input and provides output
 */
public class Main {

	public static void main(String[] args) {
		
		//load the input data from the provided URL
		String input = Initiator.initializeInputData();
		PlanetService planetService = new PlanetService();
		//parses the planet list json to list of planets
		List<Planet> planetList = planetService.loadPlanets(input);
		//Processing of planet details
		planetService.processPlanetInfo(planetList);
		//returns the number of orphan planets
		planetService.getNumberOfOrphanPlanets();
		//returns the hottest star planet identifier
		planetService.getHottestStarPlanetName();
		//returns timeline of 
		planetService.getTimeLine();
	}

}
