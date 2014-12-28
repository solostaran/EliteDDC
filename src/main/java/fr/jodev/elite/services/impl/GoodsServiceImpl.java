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

import fr.jodev.elite.dao.GoodsDAO;
import fr.jodev.elite.dao.GoodsDesignationDAO;
import fr.jodev.elite.dao.StationDAO;
import fr.jodev.elite.entities.Goods;
import fr.jodev.elite.entities.GoodsDesignation;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.exceptions.GoodsDesignationNotFoundException;
import fr.jodev.elite.exceptions.StationNotFoundException;
import fr.jodev.elite.model.GoodsForDisplay;
import fr.jodev.elite.model.Priority;
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
		return goodsDAO.getByStation(s);
	}
	
	@Override
	@Transactional
	public List<GoodsForDisplay> getStationMarketFull(long idStation) {
		Station s = stationDAO.getById(idStation);
		List<Goods> list = goodsDAO.getByStation(s);
		List<GoodsDesignation> fullList = goodsDesignationDAO.getAll();
		List<GoodsForDisplay> ret = new ArrayList<GoodsForDisplay>();
		for (GoodsDesignation gd : fullList) {
			GoodsForDisplay temp = new GoodsForDisplay();
			temp.category = gd.getCategory().getName();
			temp.designation = gd.getName();
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
		if (price >= 0) g.setPrice(price);
		if (number >= 0) g.setNumber(number);
		if (supplyOrDemand >= 0 && supplyOrDemand <= 2) g.setSupplyOrDemand(supplyOrDemand);
		if (priority >= 0 && priority <= 3) g.setPriority(priority);
		goodsDAO.update(g);
	}

	@Override
	@Transactional
	public void updateGoods(fr.jodev.elite.model.Goods goods) {
		updateGoods(goods.idStation, goods.idDesignation, goods.price, goods.number, goods.supplyOrDemand, goods.priority);
	}
}
