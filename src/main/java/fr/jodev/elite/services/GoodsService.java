package fr.jodev.elite.services;

import java.util.List;

import fr.jodev.elite.entities.Goods;
import fr.jodev.elite.model.GoodsForDisplay;

public interface GoodsService {
	Goods get(long idStation, long idGoodsDesignation);
	List<Goods> getStationMarket(long idStation);
	List<GoodsForDisplay> getStationMarketFull(long idStation);
	void updateGoods(long idStation, long idGoodsDesignation, int price, long number, int supplyOrDemand, int priority);
	void updateGoods(fr.jodev.elite.model.Goods goods);
}
