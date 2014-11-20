package fr.jodev.elite.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.services.SystemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SystemServiceTests extends AbstractTransactionalJUnit4SpringContextTests {
	// a transaction is provided for each test (no need to beginTransaction or commit)
	// by default there is a rollback
	
//	private Logger logger = Logger.getLogger("ELITE");
	
	@Autowired
	private SystemService systemService;
	
	@Test
	@Transactional
	public void testSystemCreation() throws Exception {
		SolarSystem sys = systemService.createSolarSystem("SystemTest123");
		assertNotNull(sys.getIdSolarSystem());
	}
	
	@Test
	@Transactional
	public void testSystemGetByName() throws Exception {
		systemService.createSolarSystem("SolarTest1");
		systemService.createSolarSystem("SolarTest2");
		
		List<SolarSystem> list = systemService.getByName("SolarTest");
		assertTrue(list.size() == 2);
	}
}
