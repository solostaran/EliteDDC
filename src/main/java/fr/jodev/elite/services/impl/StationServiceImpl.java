package fr.jodev.elite.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.Constants;
import fr.jodev.elite.dao.ShipBuyableDAO;
import fr.jodev.elite.dao.StationDAO;
import fr.jodev.elite.dao.SystemDAO;
import fr.jodev.elite.entities.ShipBuyable;
import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.exceptions.EmptyArgumentException;
import fr.jodev.elite.exceptions.SameNameException;
import fr.jodev.elite.exceptions.SolarSystemNotFoundException;
import fr.jodev.elite.exceptions.StationNotFoundException;
import fr.jodev.elite.model.ShipBuyableChecked;
import fr.jodev.elite.model.StationShipyard;
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
	public Station getById(long id) {
		Station sta = stationDAO.getByIdNow(id);
		try {
			sta.getIdStation();
		} catch (Exception e) {
			throw new StationNotFoundException(id);
		}
		return sta;
//		return stationDAO.getByIdNow(id);
	}

	@Override
	@Transactional
	public List<Station> getByName(String name) {
		return stationDAO.getByName(name);
	}
	
	@Override
	@Transactional
	public Station createStation(long idSolarSystem, String name) {
		SolarSystem sys = systemDAO.getById(idSolarSystem);
		try {
			sys.getIdSolarSystem();
		} catch (ObjectNotFoundException e) {
			throw new SolarSystemNotFoundException(idSolarSystem);
		}
		return createStation(sys, name);
	}

	@Override
	@Transactional
	public Station createStation(SolarSystem sys, String name) {
		if (sys == null) {
			throw new EmptyArgumentException("SolarSystem");
		}
		List<Station> list = sys.getStations();
		for(Station s : list) {
			if (s.getName().toLowerCase().equals(name.toLowerCase()))
				throw new SameNameException(Station.class.getSimpleName(), name);
		}
		Station s = new Station(sys, name);
		stationDAO.addStation(s);
		return s;
	}
	
	@Override
	@Transactional
	public Station createStation(fr.jodev.elite.model.Station station) {
		if (station == null) throw new NullPointerException("Cannot create a station with null parameter !");
		Station ret = createStation(station.getParentSolarSystem(), station.getName());
		station.setIdStation(ret.getIdStation());
		ret = updateStation(station);
		return ret;
	}

	@Override
	@Transactional
	public Station updateStation(long idStation, String name, int distance,
			boolean isMarket, boolean isBlackMarket,
			boolean isShipyard, boolean isOutfitting) {
		Station s = stationDAO.getById(idStation);
		if (name != null && !name.isEmpty()) s.setName(name);
		if (distance > 0) s.setDistance(distance);
		s.setIsMarket(isMarket);
		s.setIsBlackMarket(isBlackMarket);
		s.setIsShipyard(isShipyard);
		s.setIsOutfitting(isOutfitting);
		stationDAO.addStation(s);
		return s;
	}
	
	@Override
	@Transactional
	public Station updateStation(fr.jodev.elite.model.Station station) {
		Station s = stationDAO.getById(station.idStation);
		try {
			s.getIdStation();
		} catch (ObjectNotFoundException e) {
			throw new StationNotFoundException(station.idStation);
		}
		if (station.name != null && !station.name.isEmpty()) s.setName(station.name);
		if (station.distance > 0) s.setDistance(station.distance);
		if (station.isMarket != null) {
			if (Constants.TRUE.toLowerCase().equals(station.isMarket.toLowerCase())) s.setIsMarket(true);
			if (Constants.FALSE.toLowerCase().equals(station.isMarket.toLowerCase())) s.setIsMarket(false);
		}
		if (station.isBlackMarket != null) {
			if (Constants.TRUE.toLowerCase().equals(station.isBlackMarket.toLowerCase())) s.setIsBlackMarket(true);
			if (Constants.FALSE.toLowerCase().equals(station.isBlackMarket.toLowerCase())) s.setIsBlackMarket(false);
		}
		if (station.isShipyard != null) {
			if (Constants.TRUE.toLowerCase().equals(station.isShipyard.toLowerCase())) s.setIsShipyard(true);
			if (Constants.FALSE.toLowerCase().equals(station.isShipyard.toLowerCase())) s.setIsShipyard(false);
		}
		if (station.isOutfitting != null) {
			if (Constants.TRUE.toLowerCase().equals(station.isOutfitting.toLowerCase())) s.setIsOutfitting(true);
			if (Constants.FALSE.toLowerCase().equals(station.isOutfitting.toLowerCase())) s.setIsOutfitting(false);
		}
		stationDAO.addStation(s);
		return s;
	}

	@Override
	@Transactional
	public void removeStationById(long id) {
		stationDAO.removeStationById(id);
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

	@Override
	@Transactional
	public StationShipyard getShipyard(long idStation) {
		Station s = stationDAO.getById(idStation);
		List<ShipBuyable> fulllist = shipBuyableDAO.getAll();
		List<ShipBuyable> list = s.getStationShipyard();
		StationShipyard ret = new StationShipyard(idStation);
		ret.setStationName(s.getName());
		ret.setIdSolarSystem(s.getParentSolarSystem().getIdSolarSystem());
		ret.setSolarSystemName(s.getParentSolarSystem().getName());
		for (ShipBuyable sb : fulllist) {
			ShipBuyableChecked sbc = new ShipBuyableChecked(sb.getIdShipBuyable());
			for (ShipBuyable l : list) if (l.getIdShipBuyable().equals(sbc.idShipBuyable)) sbc.checked = true;
			ret.addShipBuyableChecked(sbc);
		}
		return ret;
	}

	@Override
	@Transactional
	public StationShipyard updateShipyard(StationShipyard shipyard) {
		for (ShipBuyableChecked sbc : shipyard.getShips()) {
			if (sbc.getChecked() == true) {
				addShipBuyable(shipyard.idStation, sbc.getIdShipBuyable());
			} else {
				removeShipBuyable(shipyard.idStation, sbc.getIdShipBuyable());
			}
		}
		return getShipyard(shipyard.idStation);
	}
}
