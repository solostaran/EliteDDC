package fr.jodev.elite.services;

import java.util.List;

import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.model.StationShipyard;

public interface StationService {
	Station createStation(long idSolarSystem, String name);
	Station createStation(SolarSystem sys, String name);
	Station createStation(fr.jodev.elite.model.Station station);
	Station updateStation(long idStation, String name, int distance, boolean isMarket, boolean isBlackMarket, boolean isShipyard, boolean isOutfitting);
	Station updateStation(fr.jodev.elite.model.Station station);
	void removeStationById(long id);
	List<Station> getByName(String name);
	Station getById(long id);
	void addShipBuyable(long idStation, long idShipBuyable);
	void removeShipBuyable(long idStation, long idShipBuyable);
	List<Long> getShipBuyables(long idStation);
	StationShipyard getShipyard(long idStation);
	StationShipyard updateShipyard(StationShipyard shipyard);
}
