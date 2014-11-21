package fr.jodev.elite.services;

import java.util.List;

import fr.jodev.elite.entities.ShipOutfitCategory;

public interface ShipOutfitCategoryService {
	void add(int id,String name);
	List<ShipOutfitCategory> getAll();
	List<String> getNames();
}
