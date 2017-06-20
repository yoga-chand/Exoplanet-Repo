package com.exoplanet.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class Planet implements Serializable{

	private static final long serialVersionUID = 6214777961684744553L;

	@JsonProperty("PlanetIdentifier")
	private String planetIdentifier;
	
	@JsonProperty("TypeFlag")
	private int typeFlag;
	
	@JsonProperty("PlanetaryMassJpt")
	private float planetaryMass;

	@JsonProperty("RadiusJpt")
	private float radiusJupiter;
	
	@JsonProperty("PeriodDays")
	private float periodDays;
	
	@JsonProperty("SurfaceTempK")
	private float surfaceTemp;
	
	@JsonProperty("HostStarTempK")
	private float hostStarTemp;
	
	@JsonProperty("HostStarMassSlrMass")
	private float hostStarMass;
	
	@JsonProperty("HostStarRadiusSlrRad")
	private float hostStarRadius;
	
	@JsonProperty("DiscoveryYear")
	private String discoveryYear;
	
	public String getPlanetIdentifier() {
		return planetIdentifier;
	}

	public void setPlanetIdentifier(String planetIdentifier) {
		this.planetIdentifier = planetIdentifier;
	}
	
	public int getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(int typeFlag) {
		this.typeFlag = typeFlag;
	}

	public float getPlanetaryMass() {
		return planetaryMass;
	}

	public void setPlanetaryMass(float planetaryMass) {
		this.planetaryMass = planetaryMass;
	}

	public float getRadiusJupiter() {
		return radiusJupiter;
	}

	public void setRadiusJupiter(float radiusJupiter) {
		this.radiusJupiter = radiusJupiter;
	}

	public float getPeriodDays() {
		return periodDays;
	}

	public void setPeriodDays(float periodDays) {
		this.periodDays = periodDays;
	}

	public float getSurfaceTemp() {
		return surfaceTemp;
	}

	public void setSurfaceTemp(float surfaceTemp) {
		this.surfaceTemp = surfaceTemp;
	}

	public float getHostStarTemp() {
		return hostStarTemp;
	}

	public void setHostStarTemp(float hostStarTemp) {
		this.hostStarTemp = hostStarTemp;
	}

	public float getHostStarMass() {
		return hostStarMass;
	}

	public void setHostStarMass(float hostStarMass) {
		this.hostStarMass = hostStarMass;
	}

	public float getHostStarRadius() {
		return hostStarRadius;
	}

	public void setHostStarRadius(float hostStarRadius) {
		this.hostStarRadius = hostStarRadius;
	}

	public String getDiscoveryYear() {
		return discoveryYear;
	}

	public void setDiscoveryYear(String discoveryYear) {
		this.discoveryYear = discoveryYear;
	}
	
}
