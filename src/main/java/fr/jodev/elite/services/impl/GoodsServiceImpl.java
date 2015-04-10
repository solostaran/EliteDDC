package fr.jodev.elite.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.CSVHelper;
import fr.jodev.elite.dao.GoodsCategoryDAO;
import fr.jodev.elite.dao.GoodsDAO;
import fr.jodev.elite.dao.GoodsDesignationDAO;
import fr.jodev.elite.dao.StationDAO;
import fr.jodev.elite.entities.Goods;
import fr.jodev.elite.entities.GoodsCategory;
import fr.jodev.elite.entities.GoodsDesignation;
import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.exceptions.GoodsDesignationNotFoundException;
import fr.jodev.elite.exceptions.StationNotFoundException;
import fr.jodev.elite.model.Commodities;
import fr.jodev.elite.model.Commodities2;
import fr.jodev.elite.model.CsvParsingErrors;
import fr.jodev.elite.model.GoodsExtended;
import fr.jodev.elite.model.GoodsForDisplay;
import fr.jodev.elite.model.GoodsSimplified;
import fr.jodev.elite.model.GoodsSubcategory;
import fr.jodev.elite.model.GoodsSubcategory2;
import fr.jodev.elite.model.Priority;
import fr.jodev.elite.model.StationMarket;
import fr.jodev.elite.model.StationMarketSimplified;
import fr.jodev.elite.model.SupplyOrDemand;
import fr.jodev.elite.services.GoodsService;
import fr.jodev.elite.services.SystemService;

@Service
@Scope("singleton")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDAO goodsDAO;

	@Autowired
	private GoodsDesignationDAO goodsDesignationDAO;

	@Autowired
	private GoodsCategoryDAO goodsCategoryDAO;

	@Autowired
	private StationDAO stationDAO;

	@Autowired
	private SystemService systemService;

	@Override
	@Transactional
	public Goods get(long idStation, long idGoodsDesignation) {
		return goodsDAO.addOrGet(idStation, idGoodsDesignation);
	}

	@Override
	@Transactional
	public List<Goods> getStationMarket(long idStation) {
		Station s = stationDAO.getById(idStation);
		List<Goods> list = s.getGoods();
		List<Goods> ret = new ArrayList<Goods>();
		for (Goods g : list) ret.add(g);
		return ret;
	}

	@Override
	@Transactional
	public StationMarket getStationMarketFull(long idStation) {
		Station s = stationDAO.getById(idStation);
		List<Goods> list = goodsDAO.getByStation(s);
		List<GoodsDesignation> designations = goodsDesignationDAO.getAll();
		List<GoodsForDisplay> ret = new ArrayList<GoodsForDisplay>();
		for (GoodsDesignation gd : designations) {
			GoodsForDisplay temp = new GoodsForDisplay();
			temp.category = gd.getCategory().getIdGoodsCategory();
			temp.designation = gd.getIdGoodsDesignation();
			for (Goods g : list) {
				if (g.getGoodsDesignation().getIdGoodsDesignation() == gd.getIdGoodsDesignation()) {
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					temp.lastUpdated = df.format(new Date(g.getLastUpdated()));
					temp.number = g.getNumber();
					temp.price = g.getPrice();
					temp.priority = Priority.forValue(g.getPriority());
					temp.supplyOrDemand = SupplyOrDemand.forValue(g.getSupplyOrDemand());
				}
			}
			ret.add(temp);
		}
		StationMarket market = new StationMarket(idStation);
		market.goods = ret;
		return market;
	}

	@Override
	@Transactional
	public Commodities getCommodities(long idStation) {
		Station s = stationDAO.getById(idStation);
		List<Goods> list = goodsDAO.getByStation(s);
		List<GoodsCategory> listCat = goodsCategoryDAO.getAll();
		//		List<GoodsDesignation> listDes = goodsDesignationDAO.getAll();
		Commodities ret = new Commodities(idStation);
		for (GoodsCategory gc : listCat) {
			GoodsSubcategory gsc = new GoodsSubcategory();
			gsc.setIdCategory(gc.getIdGoodsCategory());
			//			gsc.setName(gc.getName());
			ret.addSubcategory(gsc);
		}
		for (Goods g : list) {
			GoodsExtended gws = new GoodsExtended(g);
			int index = g.getGoodsDesignation().getCategory().getIdGoodsCategory().intValue();
			ret.addGoods(index, gws);
		}
		return ret;
	}

	/**
	 * Return station goods (create empty ones if not in database).
	 */
	@Override
	@Transactional
	public Commodities2 getCommodities2(long idStation) {
		Station s = stationDAO.getById(idStation);
		SolarSystem sys = s.getParentSolarSystem();
		List<Goods> list = goodsDAO.getByStation(s);
		List<GoodsCategory> listCat = goodsCategoryDAO.getAll();
		List<GoodsDesignation> listDes = goodsDesignationDAO.getAll();
		Commodities2 ret = new Commodities2(idStation);
		ret.setIdSolarSystem(sys.getIdSolarSystem());
		ret.setSolarSystemName(sys.getName());
		ret.setStationName(s.getName());
		// Create a list of categories
		for (GoodsCategory gc : listCat) {
			GoodsSubcategory2 gsc = new GoodsSubcategory2();
			gsc.setIdCategory(gc.getIdGoodsCategory());
			ret.addSubcategory(gsc);
		}
		// Add a list of designations to each category (and fill in with goods' data)
		for (int indexDes = 0, index = 0; indexDes < listDes.size(); indexDes++) {
			GoodsSimplified gs = null;
			int indexCat = listDes.get(indexDes).getCategory().getIdGoodsCategory().intValue();
			if (index < list.size()) {
				Goods g = list.get(index);
				if (g.getGoodsDesignation().getIdGoodsDesignation().intValue() == indexDes+1) {
					// goods present in the database
					gs = new GoodsSimplified(g);
					index++;
				} else {
					// create empty goods
					gs = new GoodsSimplified(indexDes+1);
				}
			} else {
				// end of list filled with empty goods
				gs = new GoodsSimplified(indexDes+1);
			}
			ret.addGoods(indexCat, gs);
		}
		// A simple one, only with goods in the database
		//		for (Goods g : list) {
		//			GoodsSimplified gs = new GoodsSimplified(g);
		//			int index = g.getGoodsDesignation().getCategory().getIdGoodsCategory().intValue();
		//			ret.addGoods(index, gs);
		//		}
		return ret;
	}

	@Override
	@Transactional
	public void updateGoods(long idStation, long idGoodsDesignation,
			int price, long number, int supplyOrDemand, int priority) {
		Station s = null;
		try {
			s = stationDAO.getById(idStation);
			s.getIdStation();
		} catch (ObjectNotFoundException e) {
			throw new StationNotFoundException(idStation);
		}
		GoodsDesignation gd = null;
		try {
			gd = goodsDesignationDAO.getById(idGoodsDesignation);
			gd.getIdGoodsDesignation();
		} catch (ObjectNotFoundException e) {
			throw new GoodsDesignationNotFoundException(idGoodsDesignation);
		}
		Goods g = goodsDAO.addOrGet(s, gd);
		if (supplyOrDemand < 0 || supplyOrDemand > 2) {
			supplyOrDemand = g.getSupplyOrDemand();
		}
		if (supplyOrDemand == 0) {
			if (g.getLastUpdated() == 0) return; // no new information = return
			g.setPriority(0);
			g.setPrice(0);
			g.setNumber(0L);
		} else {
			g.setSupplyOrDemand(supplyOrDemand);
			if (price < 0) price = g.getPrice();
			g.setPrice(price);
			if (number < 0) number = g.getNumber();
			g.setNumber(number);
			if (priority < 0) priority = g.getPriority();
			g.setPriority(priority);
		}
		goodsDAO.update(g);
	}

	@Override
	@Transactional
	public void updateGoods(fr.jodev.elite.model.Goods goods) {
		updateGoods(goods.idStation, goods.idDesignation, goods.price, goods.number, goods.supplyOrDemand, goods.priority);
	}

	@Override
	@Transactional
	public void updateGoods(List<fr.jodev.elite.model.Goods> goods) {
		for (fr.jodev.elite.model.Goods g : goods) {
			updateGoods(g.idStation, g.idDesignation, g.price, g.number, g.supplyOrDemand, g.priority);
		}
	}

	@Override
	@Transactional
	public void updateGoods(StationMarket market) {
		for (GoodsForDisplay gfd : market.goods) {
			updateGoods(market.idStation, gfd.designation, gfd.price,
					gfd.number, gfd.supplyOrDemand.getValue(), gfd.priority.getValue());
		}
	}

	@Override
	@Transactional
	public void updateGoods(StationMarketSimplified market) {
		for (GoodsSimplified gs : market.goods) {
			GoodsExtended g = new GoodsExtended(gs);
			updateGoods(market.idStation, g.getIdDesignation(), g.getPrice(),
					g.getNumber(), g.getSupplyOrDemand().getValue(), g.getPriority().getValue());
		}
	}

	@Override
	@Transactional
	public void updateGoods(Commodities market) {
		for (GoodsSubcategory sub : market.commodities) {
			for (GoodsExtended g : sub.goods) {
				updateGoods(market.idStation, g.getIdDesignation(), g.getPrice(),
						g.getNumber(), g.getSupplyOrDemand().getValue(), g.getPriority().getValue());
			}
		}
	}

	@Override
	@Transactional
	public void updateGoods(Commodities2 market) {
		for (GoodsSubcategory2 sub : market.commodities) {
			for (GoodsSimplified gs : sub.goods) {
				GoodsExtended g = new GoodsExtended(gs);
				updateGoods(market.idStation, g.getIdDesignation(), g.getPrice(),
						g.getNumber(), g.getSupplyOrDemand().getValue(), g.getPriority().getValue());
			}
		}
	}

	@Override
	@Transactional
	public List<Commodities2> getByProximity(long idSolarSystem, float distance) {
		List<SolarSystem> listSys = systemService.getByProximity(idSolarSystem, distance);
		List<Commodities2> ret = new ArrayList<Commodities2>();
		for (SolarSystem sys : listSys) {
			for (Station s : sys.getStations()) {
				ret.add(getCommodities2(s.getIdStation()));
			}
		}
		return ret;
	}

	@Override
	@Transactional
	public CsvParsingErrors updateGoods(File localCsvFile) {
		// Parsing with the library jCsv
//		Reader reader;
//		try {
//			reader = new FileReader(localCsvFile);
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e.getMessage());
//		}
//
//		ValueProcessorProvider provider = new ValueProcessorProvider();
//		CSVEntryParser<GoodsFromCSV> entryParser = new AnnotationEntryParser<GoodsFromCSV>(GoodsFromCSV.class, provider);
//		CSVReader<GoodsFromCSV> csvGoodsReader = new CSVReaderBuilder<GoodsFromCSV>(reader)
//				.strategy(new CSVStrategy(';', '\"', '#', true, true))
//				.entryParser(entryParser)
//				.build();
//
//		List<GoodsFromCSV> list = null;
//		try {
//			list = csvGoodsReader.readAll();
//			list.remove(0);
//		} catch (IOException e) {
//			throw new RuntimeException(e.getMessage());
//		}

		CsvParsingErrors ret = new CsvParsingErrors();
		
		// Parse the CSV file line by line
		FileInputStream fis;
		Reader fr;
		List<String> values;
		try {
			fis = new FileInputStream(localCsvFile);
			fr = new InputStreamReader(fis, "UTF-8");

			String previousSystemName = null;
			SolarSystem previousSystem = null;
			String previousStationName = null;
			Station previousStation = null;
			int price;
			int supplyOrDemand = 0;
			long number = 0;
			int priority = 0;
			// -------------------------
			// CSV parsing and treatment
			// -------------------------
			CSVHelper.parseLine(fr); // ignore first line (header)
			values = CSVHelper.parseLine(fr);
			while (values != null) {
				
				// Optimization to limit the number of SolarSystem's searches
				if (!values.get(0).equalsIgnoreCase(previousSystemName)) {
					List<SolarSystem> listsys = systemService.getByName(values.get(0));
					if (listsys.size() == 1) {
						previousSystem = listsys.get(0);
						previousSystemName = values.get(0);
					} else {
						if (listsys.size() == 0) {
							ret.addUnknownSolarSystem(values.get(0));
						} else {
							ret.addNotUniqueSolarSystem(values.get(0));
						}
						previousSystem = null;
						previousSystemName = null;
					}
				}
				if (previousSystem != null) {
					// Optimization to limit the number of Station's searches
					if (!values.get(1).equalsIgnoreCase(previousStationName)) {
						List<Station> liststa = stationDAO.getByName(values.get(1));
						if (liststa.size() == 1) {
							previousStation = liststa.get(0);
							previousStationName = values.get(1);
						} else {
							if (liststa.size() == 0) {
								ret.addUnknownStation(values.get(1));
							} else {
								ret.addNotUniqueStation(values.get(1));
							}
							previousStation = null;
							previousStationName = null;
						}
					}
					if (previousStation != null) {
						// now it has 1 SolarSystem and 1 Station, we can proceed
						GoodsDesignation gd = goodsDesignationDAO.getByName(values.get(2));
						if (gd != null) {
							price = CSVHelper.parseInt(values.get(4)); // Buy price
							if (price == 0) {
								price = CSVHelper.parseInt(values.get(3)); // Sell price
								supplyOrDemand = 2; // demand
								number = CSVHelper.parseLong(values.get(5));
								priority = Priority.forName(values.get(6)).getValue();
							} else {
								supplyOrDemand = 1; // supply
								number = CSVHelper.parseLong(values.get(7));
								priority = Priority.forName(values.get(8)).getValue();
							}
							// Get goods and compare dates
							Goods g = goodsDAO.addOrGet(previousStation, gd);
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss+00:00");
							Date d = df.parse(values.get(9).replace('T', ' '));
							if (d.getTime() > g.getLastUpdated()) {
								// Update in DB
//								System.out.println("Update goods "+previousSystemName+","+previousStationName+","+gd.getName());
								g.setNumber(number);
								g.setPrice(price);
								g.setSupplyOrDemand(supplyOrDemand);
								g.setPriority(priority);
								goodsDAO.update(g);
							}
						} else {
							ret.addUnknownGoodsDesignation(values.get(2));
						}
					}
				}
				// Next Line
				values = CSVHelper.parseLine(fr);
			};
			// -------------------------
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return ret;
	}
}
