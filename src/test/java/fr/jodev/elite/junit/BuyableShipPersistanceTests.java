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

import fr.jodev.elite.dao.ShipBuyableDAO;
import fr.jodev.elite.dao.ShipOutfitCategoryDAO;
import fr.jodev.elite.entities.ShipBuyable;
import fr.jodev.elite.entities.ShipOutfitCategory;
import fr.jodev.elite.entities.ShipOutfitSlot;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BuyableShipPersistanceTests extends
		AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private ShipOutfitCategoryDAO shipOutfitCategoryDAOImpl;
	
	@Autowired
	private ShipBuyableDAO shipBuyableDAOImpl;
	
	private ShipOutfitCategory soctest1;
	private ShipOutfitCategory soctest2;
	private ShipBuyable sbtest;
	
	@Before
	public void populateTests() {
		soctest1 = new ShipOutfitCategory(51,"SOC1");
		shipOutfitCategoryDAOImpl.add(soctest1);
		soctest2 = new ShipOutfitCategory(52,"SOC2");
		shipOutfitCategoryDAOImpl.add(soctest2);
		sbtest = new ShipBuyable("SBSW");
		shipBuyableDAOImpl.add(sbtest);
	}
	
	@After
	public void endTests() {
		
	}
	
	@Test
	@Transactional
	public void testAddShipOutfitCategory() throws Exception {
		ShipOutfitCategory soc = new ShipOutfitCategory(53,"SOCTEST");
		shipOutfitCategoryDAOImpl.add(soc);
		assertNotNull(soc.getIdShipOutfitCategory());
	}
	
	@Test
	@Transactional
	public void testGetAllShipOutfitCategory() throws Exception {
		List<ShipOutfitCategory> list = shipOutfitCategoryDAOImpl.getAll();
		assertTrue(list.size() >= 2);
		assertTrue(list.contains(soctest2));
	}
	
	@Test
	@Transactional
	public void testAddShipBuyable() throws Exception {
		ShipBuyable sb = new ShipBuyable("SBTEST");
		shipBuyableDAOImpl.add(sb);
		assertNotNull(sb.getIdShipBuyable());
	}

	@Test
	@Transactional
	public void testGetAllShipBuyable() throws Exception {
		List<ShipBuyable> list = shipBuyableDAOImpl.getAll();
		assertTrue(list.size() >= 1);
		assertTrue(list.contains(sbtest));
	}
	
	@Test
	@Transactional
	public void testAddSlotToShip() throws Exception {
		sbtest.addSlot(soctest1, 2);
		List<ShipOutfitSlot> list = sbtest.getSlots();
		assertTrue(list.size() == 1);
	}
}
