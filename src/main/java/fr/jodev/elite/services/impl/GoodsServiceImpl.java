package fr.jodev.elite.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.dao.GoodsDAO;
import fr.jodev.elite.dao.StationDAO;
import fr.jodev.elite.entities.Goods;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.services.GoodsService;

@Service
@Scope("singleton")
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsDAO goodsDAO;
	
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
	public void updateGoods(long idStation, long idGoodsDesignation,
			int price, long number, int supplyOrDemand, int priority) {
		Goods g = goodsDAO.addOrGet(idStation, idGoodsDesignation);
		if (price >= 0) g.setPrice(price);
		if (number >= 0) g.setNumber(number);
		if (supplyOrDemand >= 0 && supplyOrDemand <= 2) g.setSupplyOrDemand(supplyOrDemand);
		if (priority >= 0 && priority <= 3) g.setPriority(priority);
		goodsDAO.update(g);
	}

}
