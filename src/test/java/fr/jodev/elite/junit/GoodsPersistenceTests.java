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

import fr.jodev.elite.dao.GoodsDAO;
import fr.jodev.elite.entities.Goods;
import fr.jodev.elite.entities.GoodsDesignation;
import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.services.GoodsDesignationService;
import fr.jodev.elite.services.StationService;
import fr.jodev.elite.services.SystemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class GoodsPersistenceTests extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private GoodsDAO goodsDAO;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private GoodsDesignationService goodsDesignationService;
	
	private Station station;
	private List<GoodsDesignation> listGd;
	
	@Before
	public void populateTests() {
		SolarSystem sys = systemService.createSolarSystem("SolarTest123");
		station = stationService.createStation(sys.getIdSolarSystem(), "Galaga123");
		listGd = goodsDesignationService.getAll();
		goodsDAO.addOrGet(station, listGd.get(1));
	}
	
	@After
	public void endTests() {
		
	}
	
	@Test
	@Transactional
	public void testCreateGoods() {
		Goods g = new Goods(station, listGd.get(0));
		goodsDAO.update(g);
		assertNotNull(g.getIdGoods());
	}
	
	@Test
	@Transactional
	public void testAddOrGetGoods() {
//		Station toto = stationService.getById(64);
		Goods g = goodsDAO.addOrGet(station, listGd.get(0));
		assertNotNull(g.getIdGoods());
		g = goodsDAO.addOrGet(station, listGd.get(1));
		assertNotNull(g.getIdGoods());
	}
	
	@Test
	@Transactional
	public void testListGoods() {
		List<Goods> list = goodsDAO.getByStation(station);
		assertTrue(list.size() == 1);
	}
}
