package fr.jodev.elite.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.dao.ShipBuyableDAO;
import fr.jodev.elite.dao.StationDAO;
import fr.jodev.elite.dao.SystemDAO;
import fr.jodev.elite.entities.ShipBuyable;
import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.exceptions.SameNameException;
import fr.jodev.elite.exceptions.SolarSystemNotFoundException;
import fr.jodev.elite.services.StationService;

@Service
@Scope("singleton")
public class StationServiceImpl implements StationService {
	
	@Autowired
	private StationDAO stationDAO;
	
	@Autowired
	private SystemDAO systemDAO;
	
	@Autowired
	private ShipBuyableDAO shipBuyableDAO;

	@Override
	@Transactional
	public Station createStation(long idSolarSystem, String name) {
		SolarSystem sys = systemDAO.getById(idSolarSystem);
		Station s = null;
		try {
			s = new Station(sys, name);
			stationDAO.addStation(s);
		} catch(ObjectNotFoundException e) {
			throw new SolarSystemNotFoundException(idSolarSystem);
		}
		return s;
	}
	
	@Override
	@Transactional
	public Station getById(long id) {
		return stationDAO.getById(id);
	}

	@Override
	@Transactional
	public List<Station> getByName(String name) {
		return stationDAO.getByName(name);
	}

	@Override
	@Transactional
	public Station createStation(SolarSystem sys, String name) {
		if (sys == null) return null;
		List<Station> list = getByName(name);
		Iterator<Station> l = list.iterator();
		while (l.hasNext()) {
			if (l.next().getName().toLowerCase().equals(name.toLowerCase()))
				throw new SameNameException(Station.class.getSimpleName(), name);
		}
		Station s = new Station(sys, name);
		stationDAO.addStation(s);
		return s;
	}

	@Override
	@Transactional
	public void updateStation(long idStation, String name,
			boolean isMarket, boolean isBlackMarket,
			boolean isShipyard, boolean isOutfitting) {
		Station s = stationDAO.getById(idStation);
		s.setName(name);
		s.setIsMarket(isMarket);
		s.setIsBlackMarket(isBlackMarket);
		s.setIsShipyard(isShipyard);
		s.setIsOutfitting(isOutfitting);
	}

	@Override
	@Transactional
	public void addShipBuyable(long idStation, long idShipBuyable) {
		Station s = stationDAO.getById(idStation);
		ShipBuyable ship = shipBuyableDAO.getById(idShipBuyable);
		stationDAO.addShipBuyable(s, ship);
	}

	@Override
	@Transactional
	public void removeShipBuyable(long idStation, long idShipBuyable) {
		Station s = stationDAO.getById(idStation);
		ShipBuyable ship = shipBuyableDAO.getById(idShipBuyable);
		stationDAO.removeShipBuyable(s, ship);
	}

	@Override
	@Transactional
	public List<Long> getShipBuyables(long idStation) {
		Station s = stationDAO.getById(idStation);
		List<ShipBuyable> list = s.getStationShipyard();
		List<Long> ret = new ArrayList<Long>();
		for (ShipBuyable ship : list)
			ret.add(ship.getIdShipBuyable());
		return ret;
	}
}
