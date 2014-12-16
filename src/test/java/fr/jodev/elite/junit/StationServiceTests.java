package fr.jodev.elite.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.entities.ShipBuyable;
import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.services.ShipBuyableService;
import fr.jodev.elite.services.StationService;
import fr.jodev.elite.services.SystemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class StationServiceTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private StationService stationService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private ShipBuyableService shipBuyableService;
	
	private SolarSystem sys;
	private ShipBuyable ship;
	private Station sta;
	
	@Before
	public void populateTests() {
		sys = systemService.createSolarSystem("SolarTest123");
		sta = stationService.createStation(sys.getIdSolarSystem(), "Galaga123");
		ship = shipBuyableService.createShip("RType123");
	}
	
	@After
	public void endTests() {
		
	}
	
	@Test
	@Transactional
	public void testStationCreation() throws Exception {
		Station station = stationService.createStation(sys.getIdSolarSystem(), "OdysseusTest");
		assertNotNull(station.getIdStation());
	}
	
	@Test
	@Transactional
	public void testStationGetByName() throws Exception {
		stationService.createStation(sys.getIdSolarSystem(), "OdysseusTest1");
		stationService.createStation(sys.getIdSolarSystem(), "OdysseusTest2");
		List<Station> list = stationService.getByName("OdysseusTest");
		assertTrue(list.size() == 2);
	}
	
	@Test
	@Transactional
	public void testStationsAndSolarSystem() throws Exception {
		List<Station> list = sys.getStations();
		final int size = list.size();
		Station s = stationService.createStation(sys.getIdSolarSystem(), "Sol3");
		stationService.createStation(sys.getIdSolarSystem(), "Luna2");
		list = sys.getStations();
		assertTrue(list.size() == size + 2);
		assertTrue(s.getParentSolarSystem() == sys);
	}
	
	@Test
	@Transactional
	public void testAddShipBuyable() throws Exception {
		final long id = sta.getIdStation();
		final long idship = ship.getIdShipBuyable();
		stationService.addShipBuyable(id, idship);
		List<Long> list = stationService.getShipBuyables(id);
		assertTrue(list.size() == 1);
		
		stationService.removeShipBuyable(id, idship);
		list = stationService.getShipBuyables(id);
		assertTrue(list.isEmpty());
	}
}
