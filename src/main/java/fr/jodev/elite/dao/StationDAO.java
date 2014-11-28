package fr.jodev.elite.dao;

import java.util.List;

import fr.jodev.elite.entities.ShipBuyable;
import fr.jodev.elite.entities.Station;

public interface StationDAO {
	void addStation(Station s);
	Station getById(long id);
	Station getByIdNow(long id);
	List<Station> getByName(String name);
	void addShipBuyable(Station s, ShipBuyable ship);
	void removeShipBuyable(Station s, ShipBuyable ship);	
}
