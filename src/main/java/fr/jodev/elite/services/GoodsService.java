package fr.jodev.elite.services;

import java.io.File;
import java.util.List;

import fr.jodev.elite.entities.Goods;
import fr.jodev.elite.model.Commodities;
import fr.jodev.elite.model.Commodities2;
import fr.jodev.elite.model.CsvParsingErrors;
import fr.jodev.elite.model.StationMarket;
import fr.jodev.elite.model.StationMarketSimplified;

public interface GoodsService {
	Goods get(long idStation, long idGoodsDesignation);
	List<Goods> getStationMarket(long idStation);
	StationMarket getStationMarketFull(long idStation);
	Commodities getCommodities(long idStation);
	Commodities2 getCommodities2(long idStation);
	void updateGoods(long idStation, long idGoodsDesignation, int price, long number, int supplyOrDemand, int priority);
	void updateGoods(fr.jodev.elite.model.Goods goods);
	void updateGoods(List<fr.jodev.elite.model.Goods> goods);
	void updateGoods(StationMarket market);
	void updateGoods(StationMarketSimplified market);
	void updateGoods(Commodities market);
	void updateGoods(Commodities2 market);
	CsvParsingErrors updateGoods(File localCsvFile);
	List<Commodities2> getByProximity(long idSolarSystem, float distance);
}
