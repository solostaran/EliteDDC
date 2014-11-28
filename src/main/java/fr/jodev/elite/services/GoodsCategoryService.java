package fr.jodev.elite.services;

import java.util.List;

import fr.jodev.elite.entities.GoodsCategory;

public interface GoodsCategoryService {
	void add(long id, String name);
	List<GoodsCategory> getAll();
}
