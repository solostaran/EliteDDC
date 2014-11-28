package fr.jodev.elite.dao;

import java.util.List;

import fr.jodev.elite.entities.GoodsCategory;

public interface GoodsCategoryDAO {
	void add(GoodsCategory gc);
	GoodsCategory getById(long id);
	List<GoodsCategory> getAll();
}
