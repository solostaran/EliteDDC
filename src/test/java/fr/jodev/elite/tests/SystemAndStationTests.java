package fr.jodev.elite.tests;

import java.util.List;

import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.jodev.elite.entities.ShipBuyable;
import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.services.ShipBuyableService;
import fr.jodev.elite.services.StationService;
import fr.jodev.elite.services.SystemService;

public class SystemAndStationTests {
	
	private static Logger logger = Logger.getLogger("ELITE");

	public static void main(String[] args) {
		ApplicationContext ctx = 
		        new ClassPathXmlApplicationContext("classpath:/META-INF/spring/app-context.xml");
		
		SessionFactory sessionFactory = (SessionFactory)ctx.getBean("sessionFactory");
		sessionFactory.openSession();
		
		SystemService sysserv = (SystemService)ctx.getBean("systemServiceImpl");
		
		SolarSystem sys = sysserv.createSolarSystem("Carne666");
		logger.info("System added : "+sys.getIdSolarSystem());
		
		List<SolarSystem> list = sysserv.getByName("Carne");
		logger.info("Get SystemByName(Carne) : size of list = "+list.size());
		
		StationService staserv = (StationService)ctx.getBean("stationServiceImpl");
		
		Station sta = staserv.createStation(sys.getIdSolarSystem(), "TestTotal1");
		logger.info("Station added : "+sta.getIdStation());
		sta = staserv.createStation(sys.getIdSolarSystem(), "TestEsso2");
		logger.info("Station added : "+sta.getIdStation());
		
		List<Station> lista = staserv.getByName("Test");
		logger.info("Get StationByName(Test) : size of list = "+lista.size());
		
		ShipBuyableService sbserv = (ShipBuyableService)ctx.getBean("shipBuyableServiceImpl");
		
		List<ShipBuyable> listship = sbserv.getAll();
		final long idship1 = listship.get(0).getIdShipBuyable();
		final long idship2 = listship.get(listship.size() - 1).getIdShipBuyable();
		staserv.addShipBuyable(sta.getIdStation(), idship1);
		staserv.addShipBuyable(sta.getIdStation(), idship2);
		logger.info("Added 2 ships in "+sta.getName()+" shipyard.");
		
		((ConfigurableApplicationContext)ctx).close();
	}

}
