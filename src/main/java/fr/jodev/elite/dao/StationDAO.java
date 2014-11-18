package fr.jodev.elite.dao;

import java.util.List;

import fr.jodev.elite.entities.Station;

public interface StationDAO {
	void addStation(Station s);
	Station getById(long id);
	List<Station> getByName(String name);
}
