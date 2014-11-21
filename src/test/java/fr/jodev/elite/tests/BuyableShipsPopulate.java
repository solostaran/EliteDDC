package fr.jodev.elite.tests;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.jodev.elite.entities.ShipBuyable;
import fr.jodev.elite.entities.ShipOutfitCategory;
import fr.jodev.elite.services.ShipBuyableService;
import fr.jodev.elite.services.ShipOutfitCategoryService;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan({"fr.jodev.elite.dao.impl","fr.jodev.elite.services.impl"})
public class BuyableShipsPopulate {

	private static Logger logger = Logger.getLogger("ELITE");

	private static ShipOutfitCategoryService shipOutfitCategoryService;
	private static ShipBuyableService shipBuyableService;

	private static final String [] listShipOutfitCategories = {
		"Hardpoint", "Utility Mount", "Bulkheads", "Reactor bay", "Thruster mounting",
		"Frame Shift Drive housing", "Environmental control", "Power coupling", "Sensor suite",
		"Fuel Store", "Internal compartment"
	};

	private static void populateShipOutfitCategories() {
		final int nbcat = listShipOutfitCategories.length;
		for (int i = 0; i < nbcat; i++)
			shipOutfitCategoryService.add(i+1,listShipOutfitCategories[i]);
		logger.info("Added "+listShipOutfitCategories.length+" Ship Outfit Categories");

	}

	private static long populateShip(String name, long price, int mass, float minRange, float maxRange) {
		ShipBuyable sb = shipBuyableService.createShip(name);
		final long idship = sb.getIdShipBuyable();
		shipBuyableService.updateShip(idship, name, price, mass, minRange, maxRange);
		return idship;
	}

	private static void populateSlots(long idship, int[][] listSlots) {
		for (int i = 0; i < listSlots.length; i++) {
			shipBuyableService.addSlotToShip(idship, listSlots[i][0], listSlots[i][1]);
		}
	}

	private static void populateBuyableShips() {
		// Populate Sidewinder
		long idship = populateShip("Sidewinder", 32000L, 25, 7.56f, 7.79f);
		final int[][] listSwSlots = {{1,1},{1,1},{2,0},{2,0},
				{3,8},{4,2},{5,2},{6,2},{7,1},{8,1},{9,1},{10,1},
				{11,2},{11,2},{11,1}
		};
		populateSlots(idship, listSwSlots);

		// Populate Eagle
		idship = populateShip("Eagle", 44800L, 50, 8.27f, 8.76f);
		final int [][] listEgSlots = {{1,1},{1,1},{1,1},{2,0},
				{3,8},{4,2},{5,3},{6,3},{7,1},{8,2},{9,2},{10,2},
				{11,3},{11,2},{11,1}
		};
		populateSlots(idship, listEgSlots);

		// Populate Hauler
		idship = populateShip("Hauler", 52720L, 14, 0, 0);
		final int [][] listHaSlots = {{1,1},{2,0},{2,0},
				{3,8},{4,2},{5,2},{6,2},{7,1},{8,1},{9,1},{10,2},
				{11,3},{11,3},{11,2},{11,1}
		};
		populateSlots(idship, listHaSlots);

		// Populate Viper
		idship = populateShip("Viper", 142931L, 60, 6.92f, 7.12f);
		final int [][] listViSlots = {{1,1},{1,1},{1,2},{1,2},{2,0},{2,0},
				{3,8},{4,3},{5,3},{6,3},{7,2},{8,3},{9,3},{10,2},
				{11,3},{11,3},{11,2},{11,1}
		};
		populateSlots(idship, listViSlots);

		// Populate Cobra Mk3
		idship = populateShip("Cobra Mk3", 279718L, 180, 9.78f, 10.46f);
		final int [][] listCbSlots = {{1,1},{1,1},{1,2},{1,2},{2,0},{2,0},
				{3,8},{4,4},{5,4},{6,4},{7,3},{8,3},{9,3},{10,8},
				{11,4},{11,4},{11,4},{11,2},{11,2},{11,2}
		};
		populateSlots(idship, listCbSlots);

		// Populate Type-6 Transporter
		idship = populateShip("Type-6 Transporter", 1045945L, 155, 0, 0);
		final int [][] listT6Slots = {{1,1},{1,1},{2,0},{2,0},{2,0},
				{10,8},
				{11,5},{11,5},{11,4},{11,4},{11,3},{11,2},{11,2}
		};
		populateSlots(idship, listT6Slots);

		// Populate Asp
		idship = populateShip("ASP", 6661153L, 280, 0, 0);
		final int [][] listAspSlots = {{1,1},{1,1},{1,1},{1,1},{1,2},{1,2},{2,0},{2,0},{2,0},{2,0},
				{10,16},
				{11,6},{11,6},{11,5},{11,3},{11,3},{11,3},{11,2},{11,2}
		};
		populateSlots(idship, listAspSlots);

		// Populate Federal Dropship
		idship = populateShip("Federal Dropship", 37814205L, 580, 0, 0);
		final int [][] listFdsSlots = {{1,2},{1,2},{1,2},{1,2},{1,3},{2,0},{2,0},{2,0},{2,0},
				{10,8},
				{11,6},{11,5},{11,5},{11,4},{11,3},{11,3},{11,2}
		};
		populateSlots(idship, listFdsSlots);

		// Populate Lakon Type-9
		idship = populateShip("Lakon Type-9 Heavy", 76500000L, 1000, 0, 0);
		final int [][] listL9Slots = {{1,1},{1,1},{1,2},{1,2},{1,2},{2,0},{2,0},{2,0},{2,0},
				{10,16},
				{11,8},{11,7},{11,6},{11,5},{11,4},{11,4},{11,3},{11,3},{11,2}
		};
		populateSlots(idship, listL9Slots);


		// Populate Anaconda
		idship = populateShip("Anaconda", 146969451L, 400, 0, 0);
		final int [][] listAnaSlots = {{1,1},{1,1},{1,2},{1,2},{1,3},{1,3},{1,3},{1,4},{2,0},{2,0},{2,0},{2,0},{2,0},{2,0},{2,0},{2,0},
				{10,16},
				{11,7},{11,6},{11,6},{11,6},{11,5},{11,5},{11,5},{11,4},{11,4},{11,4},{11,2}
		};
		populateSlots(idship, listAnaSlots);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("classpath:/META-INF/spring/app-context.xml");
		//		ApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConfiguration.class);

		shipOutfitCategoryService = (ShipOutfitCategoryService)ctx.getBean("shipOutfitCategoryServiceImpl");
		shipBuyableService = (ShipBuyableService)ctx.getBean("shipBuyableServiceImpl");

		// POPULATE SHIP OUTFIT CATEGORIES
		populateShipOutfitCategories();
		List<ShipOutfitCategory> listcat = shipOutfitCategoryService.getAll();
		for (ShipOutfitCategory soc : listcat)
			logger.info(soc.getIdShipOutfitCategory()+","+soc.getName());

		// POPULATE BUYABLE SHIPS
		populateBuyableShips();

		((ConfigurableApplicationContext)ctx).close();
	}

}
