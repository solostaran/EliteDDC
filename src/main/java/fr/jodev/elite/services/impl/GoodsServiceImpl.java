package fr.jodev.elite.services.impl;

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

import fr.jodev.elite.dao.GoodsCategoryDAO;
import fr.jodev.elite.dao.GoodsDAO;
import fr.jodev.elite.dao.GoodsDesignationDAO;
import fr.jodev.elite.dao.StationDAO;
import fr.jodev.elite.entities.Goods;
import fr.jodev.elite.entities.GoodsCategory;
import fr.jodev.elite.entities.GoodsDesignation;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.exceptions.GoodsDesignationNotFoundException;
import fr.jodev.elite.exceptions.StationNotFoundException;
import fr.jodev.elite.model.Commodities;
import fr.jodev.elite.model.Commodities2;
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
	
	@Override
	@Transactional
	public Commodities2 getCommodities2(long idStation) {
		Station s = stationDAO.getById(idStation);
		List<Goods> list = goodsDAO.getByStation(s);
		List<GoodsCategory> listCat = goodsCategoryDAO.getAll();
		Commodities2 ret = new Commodities2(idStation);
		for (GoodsCategory gc : listCat) {
			GoodsSubcategory2 gsc = new GoodsSubcategory2();
			gsc.setIdCategory(gc.getIdGoodsCategory());
			ret.addSubcategory(gsc);
		}
		for (Goods g : list) {
			GoodsSimplified gs = new GoodsSimplified(g);
			int index = g.getGoodsDesignation().getCategory().getIdGoodsCategory().intValue();
			ret.addGoods(index, gs);
		}
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
}
