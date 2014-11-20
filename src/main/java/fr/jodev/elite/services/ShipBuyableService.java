package fr.jodev.elite.services;

import java.util.List;

import fr.jodev.elite.entities.ShipBuyable;
import fr.jodev.elite.entities.ShipOutfitSlot;

public interface ShipBuyableService {
	ShipBuyable createShip(String name);
	void updateShip(long idShipBuyable, String name, long price, int mass, float defaultMinRange, float defaultMaxRange);
	List<ShipBuyable> getAll();
	void addSlotToShip(long idShipBuyable, long idShipOutfitCategory, int size);
	List<ShipOutfitSlot> getShipOutfitSlots(long idShipBuyable);
}
