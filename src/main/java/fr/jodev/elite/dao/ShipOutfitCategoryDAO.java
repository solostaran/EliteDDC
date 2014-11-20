package fr.jodev.elite.dao;

import java.util.List;

import fr.jodev.elite.entities.ShipOutfitCategory;

public interface ShipOutfitCategoryDAO {
	void add(ShipOutfitCategory soc);
	ShipOutfitCategory getById(long id);
	List<ShipOutfitCategory> getAll();
}
