package fr.jodev.elite.tests;

import org.jboss.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.jodev.elite.services.GoodsCategoryService;
import fr.jodev.elite.services.GoodsDesignationService;

public class GoodsPopulate {

	private static Logger logger = Logger.getLogger("ELITE");

	private static GoodsCategoryService goodsCategoryService;
	private static GoodsDesignationService goodsDesignationService;
	
	private static final String [] listCategories = {"Chemicals", "Consumer items",
		"Foods", "Industrial materials", "Legal drugs", "Machinery", "Medicines", "Metals",
		"Minerals","Technology","Textiles","Waste","Weapons"};
	
	private static void populateCategories() {
		final int nbcat = listCategories.length;
		for (int i = 0; i < nbcat; i++)
			goodsCategoryService.add(i+1,listCategories[i]);
		logger.info("Added "+listCategories.length+" Goods Categories");
	}
	
	private static void populateCategory(int id, String [] list) {
		for (String s : list)
			goodsDesignationService.add(id, s);
		logger.info("Added "+list.length+" Goods Designations to category "+listCategories[id-1]);
	}
	
	static final String [] listChemicals = {"Explosives","Hydrogen fuel","Mineral oil","Pesticides" };
	static final String [] listConsumerItems = {"Clothing","Consumer technology","Dom. appliances" };
	static final String [] listFoods = {"Algae","Animal meat","Coffee","Fish","Food cartridges","Fruit and vegetables","Grain","Synthetic meat","Tea" };
	static final String [] listIndustrialMaterials = {"Polymers","Semiconductors","Superconductors" };
	static final String [] listLegalDrugs = {"Beer","Liquor","Narcotics","Tobacco","Wine" };
	static final String [] listMachinery = {"Atmospheric processor","Crop Harvesters","Hel-static furnaces","Marine equipment","Mineral extractors","Microbial furnaces","Power generators","Water purifiers" };
	static final String [] listMedicines = {"Agri-medicines","Basic medicines","Combat stabilisers","Performance enhancers","Progenitor cells" };
	static final String [] listMetals = {"Aluminium","Beryllium","Cobalt","Copper","Gallium","Gold","Indium","Lithium","Palladium","Platinum","Silver","Tantalum","Titanium","Uranium" };
	static final String [] listMinerals = {"Bauxite","Bertrandite","Coltan","Gallite","Indite","Lepidolite","Rutile","Uraninite" };
	static final String [] listTechnology = {"Advanced catalysers","Animal monitors","Aquaponic systems","Auto-fabricators","Bioreducing lichen","Computer components","H.E. suits","Land enrichment systems","Resonating separators","Robotics" };
	static final String [] listTextiles = {"Leather","Natural fabrics","Synthetic fabrics" };
	static final String [] listWaste = {"Biowaste","Chemical waste","Scrap" };
	static final String [] listWeapons = {"Battle weapons","Non-lethal wpns","Personal weapons","Reactive armour" };
	
	private static void populateDesignations() {
		int id = 1;
		populateCategory(id++, listChemicals);		
		populateCategory(id++, listConsumerItems);	
		populateCategory(id++, listFoods);	
		populateCategory(id++, listIndustrialMaterials);		
		populateCategory(id++, listLegalDrugs);	
		populateCategory(id++, listMachinery);
		populateCategory(id++, listMedicines);
		populateCategory(id++, listMetals);
		populateCategory(id++, listMinerals);
		populateCategory(id++, listTechnology);
		populateCategory(id++, listTextiles);
		populateCategory(id++, listWaste);
		populateCategory(id, listWeapons);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("classpath:/META-INF/spring/app-context-h2.xml");
		//		ApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConfiguration.class);

		goodsCategoryService = (GoodsCategoryService)ctx.getBean("goodsCategoryServiceImpl");
		goodsDesignationService = (GoodsDesignationService)ctx.getBean("goodsDesignationServiceImpl");
		
		populateCategories();
		populateDesignations();

		((ConfigurableApplicationContext)ctx).close();
	}

}
