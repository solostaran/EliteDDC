package fr.jodev.elite.dao;

import java.util.List;

import fr.jodev.elite.entities.ShipBuyable;

public interface ShipBuyableDAO {
	void add(ShipBuyable sb);
	ShipBuyable getById(long id);
	ShipBuyable getByIdNow(long id);
	List<ShipBuyable> getAll();
}
