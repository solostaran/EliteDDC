package fr.jodev.elite.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.dao.SystemDAO;
import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.exceptions.SameNameException;
import fr.jodev.elite.exceptions.SolarSystemNotFoundException;
import fr.jodev.elite.services.SystemService;

@Service
@Scope("singleton")
public class SystemServiceImpl implements SystemService {

	@Autowired
	private SystemDAO systemDAO;
	
	@Override
	@Transactional
	public SolarSystem createSolarSystem(String name) {
		List<SolarSystem> list = getByName(name);
		for (SolarSystem s : list) {
			if (s.getName().toLowerCase().equals(name.toLowerCase()))
				throw new SameNameException(SolarSystem.class.getSimpleName(), name);
		}
		SolarSystem sys = new SolarSystem(name);
		systemDAO.addSolarSystem(sys);
		return sys;
	}
	
	@Override
	@Transactional
	public SolarSystem createSolarSystem(String name, Double x, Double y, Double z) {
		List<SolarSystem> list = getByName(name);
		for (SolarSystem s : list) {
			if (s.getName().toLowerCase().equals(name.toLowerCase()))
				throw new SameNameException(SolarSystem.class.getSimpleName(), name);
		}
		SolarSystem sys = new SolarSystem(name, x, y, z);
		systemDAO.addSolarSystem(sys);
		return sys;
	}
	
	@Override
	@Transactional
	public SolarSystem updateSystem(fr.jodev.elite.model.SolarSystem system) {
		SolarSystem sys = systemDAO.getById(system.getIdSolarSystem());
		try {
			sys.getIdSolarSystem();
		} catch (ObjectNotFoundException e) {
			throw new SolarSystemNotFoundException(system.getIdSolarSystem());
		}
		if (system.getName() != null && !system.getName().isEmpty()) sys.setName(system.getName());
		if (system.getX() != null) sys.setX(system.getX());
		if (system.getY() != null) sys.setY(system.getY());
		if (system.getZ() != null) sys.setZ(system.getZ());
		systemDAO.addSolarSystem(sys);
		return sys;
	}
	
	@Override
	@Transactional
	public void removeSolarSystemById(long id) {
		systemDAO.removeSolarSystemById(id);
	};

	@Override
	@Transactional
	public List<SolarSystem> getByName(String name) {
		return systemDAO.getByName(name);
	}

	@Override
	@Transactional
	public SolarSystem getById(long id) {
		// TODO: Lazy loading doesn't work in Jackson 2.4.3
		// TODO: maybe it will be fixed in Jackson 2.5.0
		// see https://github.com/FasterXML/jackson-datatype-hibernate/issues/25
		// see https://github.com/FasterXML/jackson-datatype-hibernate/pull/58
		// So for now, I don't call systemDAO.getById but systemDAO.getByIdNow fix instead.
		SolarSystem sys = systemDAO.getByIdNow(id);
		try {
			sys.getIdSolarSystem();
		} catch (Exception e) {
			throw new SolarSystemNotFoundException(id);
		}
		return sys;
	}

	@Override
	@Transactional
	public List<Station> getStations(long id) {
		SolarSystem sys = getById(id);
		List<Station> list = sys.getStations();
		List<Station> ret = new ArrayList<Station>();
		for (Station s : list) ret.add(s);
		return ret;
	}

	@Override
	@Transactional
	public List<SolarSystem> getByProximity(long id, float distance) {
		SolarSystem sys = getById(id);
		return systemDAO.getByProximity(sys, distance);
	}
}
