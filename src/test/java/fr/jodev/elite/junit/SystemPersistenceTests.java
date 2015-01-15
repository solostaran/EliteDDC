package fr.jodev.elite.junit;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.dao.SystemDAO;
import fr.jodev.elite.entities.SolarSystem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SystemPersistenceTests extends AbstractTransactionalJUnit4SpringContextTests {
	// a transaction is provided for each test (no need to beginTransaction or commit)
	
//	private Logger logger = Logger.getLogger("ELITE");
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SystemDAO systemDAO;
	
	private SolarSystem system1;
	private SolarSystem system2;
	
	@Before
	public void populateTests() {
		Session session = sessionFactory.getCurrentSession();
		
		system1 = new SolarSystem("Plip",0.0,0.0,0.0);
		session.save(system1);
		
		system2 = new SolarSystem("Plop",4.99,4.99,4.99);
		session.save(system2);
	}
	
	@Test
	@Transactional
	public void testDistanceIn() throws Exception {
		List<SolarSystem> list = systemDAO.getByProximity(system1, 5);
		assertTrue(list.contains(system2));
	}
	
	@Test
	@Transactional
	public void testDistanceOut() throws Exception {
		List<SolarSystem> list = systemDAO.getByProximity(system1, 4);
		assertTrue(!list.contains(system2));
	}
}
