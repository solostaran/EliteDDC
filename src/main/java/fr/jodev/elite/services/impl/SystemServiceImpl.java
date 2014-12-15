package fr.jodev.elite.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.dao.SystemDAO;
import fr.jodev.elite.entities.SolarSystem;
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
		SolarSystem sys = new SolarSystem(name);
		List<SolarSystem> list = getByName(name);
		for (SolarSystem s : list) {
			if (s.getName().toLowerCase().equals(name.toLowerCase()))
				throw new SameNameException(SolarSystem.class.getSimpleName(), name);
		}
		systemDAO.addSolarSystem(sys);
		return sys;
	}
	
	@Override
	@Transactional
	public void deleteSolarSystem(SolarSystem sys) {
		systemDAO.removeSolarSystem(sys);
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
}
