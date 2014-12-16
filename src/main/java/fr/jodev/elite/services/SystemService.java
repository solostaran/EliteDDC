package fr.jodev.elite.services;

import java.util.List;

import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.entities.Station;

public interface SystemService {
	SolarSystem createSolarSystem(String name);
	SolarSystem createSolarSystem(String name, Long x, Long y, Long z);
	SolarSystem updateSystem(fr.jodev.elite.model.SolarSystem system);
	void deleteSolarSystem(SolarSystem sys);
	List<SolarSystem> getByName(String name);
	SolarSystem getById(long id);
	List<Station> getStations(long id);
}
