package fr.jodev.elite.dao;

import java.util.List;

import fr.jodev.elite.entities.Goods;
import fr.jodev.elite.entities.GoodsDesignation;
import fr.jodev.elite.entities.Station;

public interface GoodsDAO {
	Goods addOrGet(Station station, GoodsDesignation gd);
	Goods addOrGet(long idStation, long idGoodsDesignation);
	Goods getById(long id);
	void remove(Goods g);
	void update(Goods g);
	List<Goods> getByStation(Station station);
}
