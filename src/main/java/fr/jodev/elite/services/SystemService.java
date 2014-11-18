package fr.jodev.elite.services;

import java.util.List;

import fr.jodev.elite.entities.SolarSystem;

public interface SystemService {
	SolarSystem createSolarSystem(String name);
	void deleteSolarSystem(SolarSystem sys);
	List<SolarSystem> getByName(String name);
	SolarSystem getById(long id);
}
