package fr.jodev.elite.services;

import java.util.List;

import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.entities.Station;

public interface StationService {
	Station createStation(long idSolarSystem, String name);
	Station createStation(SolarSystem sys, String name);
	void updateStation(long idStation, String name, boolean isMarket, boolean isBlackMarket, boolean isShipyard, boolean isOutfitting);
	List<Station> getByName(String name);
	Station getById(long id);
}
