package fr.jodev.elite.dao;

import java.util.List;

import fr.jodev.elite.entities.SolarSystem;

public interface SystemDAO {
	void addSolarSystem(SolarSystem sys);
	void removeSolarSystem(SolarSystem sys);
	void removeSolarSystemById(long id);
	SolarSystem getById(long id);
	SolarSystem getByIdNow(long id);
	List<SolarSystem> getByName(String name);
	List<SolarSystem> getByProximity(SolarSystem sys, float distance);
}
