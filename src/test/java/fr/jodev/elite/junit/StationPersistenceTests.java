package fr.jodev.elite.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.entities.Station;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class StationPersistenceTests extends AbstractTransactionalJUnit4SpringContextTests {
	// a transaction is provided for each test (no need to beginTransaction or commit)
	
//	private Logger logger = Logger.getLogger("ELITE");
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	@Transactional
	public void testSaveStationWithSystem() throws Exception {
		Session session = sessionFactory.getCurrentSession();
//		session.beginTransaction();
		
		SolarSystem system1 = new SolarSystem("Sol");
		session.save(system1);
		
		Station station1 = new Station(system1, "Luna 2");
		session.save(station1);
		
		Station station2 = new Station(system1, "Titan Inc.");
		session.save(station2);
		
//		session.getTransaction().commit();
		assertNotNull(station1.getIdStation());
		assertNotNull(system1.getIdSolarSystem());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	public void testFindSystemByName() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		
		SolarSystem system1 = new SolarSystem("Soltruc1");
		session.save(system1);
		
		SolarSystem system2 = new SolarSystem("Soltruc2");
		session.save(system2);
		
		String hql = "from SolarSystem sls where lower(sls.name) like 'soltruc%'";
		List<SolarSystem> list = (List<SolarSystem>)session.createQuery(hql).list();
		
		assertTrue(list.size() == 2);
	}
	
	@Test
	@Transactional
	public void testSaveAndGetStations() throws Exception {
		Session session = sessionFactory.getCurrentSession();

		SolarSystem system1 = new SolarSystem("Sol");
		session.save(system1);
		
		Station station1 = new Station(system1, "Luna 2");
		session.save(station1);
		
		Station station2 = new Station(system1, "Titan Inc.");
		session.save(station2);
		
		SolarSystem system2 = (SolarSystem) session.load(SolarSystem.class, Long.valueOf(system1.getIdSolarSystem()));

//		logger.info(system2.getName());
//		for (Station s : system2.getStations()) {
//			logger.info(s.getName());
//		}
		assertTrue(system2.getStations().size() == 2);
	}
}
