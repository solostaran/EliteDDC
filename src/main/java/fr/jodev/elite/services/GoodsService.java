package fr.jodev.elite.services;

import java.util.List;

import fr.jodev.elite.entities.Goods;

public interface GoodsService {
	Goods get(long idStation, long idGoodsDesignation);
	List<Goods> getStationMarket(long idStation);
	void updateGoods(long idStation, long idGoodsDesignation, int price, long number, int supplyOrDemand, int priority);
}
