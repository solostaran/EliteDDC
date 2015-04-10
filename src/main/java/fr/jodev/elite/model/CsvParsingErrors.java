package fr.jodev.elite.model;

import java.util.ArrayList;
import java.util.List;

public class CsvParsingErrors {
	private String message;
	private List<String> unknownSolarSystem;
	private List<String> notUniqueSolarSystem;
	private List<String> unknownStation;
	private List<String> notUniqueStation;
	private List<String> unknownGoodsDesignation;
	
	public CsvParsingErrors() {
		unknownSolarSystem = new ArrayList<String>();
		notUniqueSolarSystem = new ArrayList<String>();
		unknownStation = new ArrayList<String>();
		notUniqueStation = new ArrayList<String>();
		unknownGoodsDesignation = new ArrayList<String>();
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getUnknownSolarSystem() {
		return unknownSolarSystem;
	}
	public void setUnknownSolarSystem(List<String> unknownSolarSystem) {
		this.unknownSolarSystem = unknownSolarSystem;
	}
	public void addUnknownSolarSystem(String s) {
		unknownSolarSystem.add(s);
	}
	
	public List<String> getNotUniqueSolarSystem() {
		return notUniqueSolarSystem;
	}
	public void setNotUniqueSolarSystem(List<String> notUniqueSolarSystem) {
		this.notUniqueSolarSystem = notUniqueSolarSystem;
	}
	public void addNotUniqueSolarSystem(String s) {
		notUniqueSolarSystem.add(s);
	}
	
	public List<String> getUnknownStation() {
		return unknownStation;
	}
	public void setUnknownStation(List<String> unknownStation) {
		this.unknownStation = unknownStation;
	}
	public void addUnknownStation(String s) {
		unknownStation.add(s);
	}
	
	public List<String> getNotUniqueStation() {
		return notUniqueStation;
	}
	public void setNotUniqueStation(List<String> notUniqueStation) {
		this.notUniqueStation = notUniqueStation;
	}
	public void addNotUniqueStation(String s) {
		notUniqueStation.add(s);
	}
	
	public List<String> getUnknownGoodsDesignation() {
		return unknownGoodsDesignation;
	}
	public void setUnknownGoodsDesignation(List<String> unknownGoodsDesignation) {
		this.unknownGoodsDesignation = unknownGoodsDesignation;
	}
	public void addUnknownGoodsDesignation(String s) {
		unknownGoodsDesignation.add(s);
	}
}
