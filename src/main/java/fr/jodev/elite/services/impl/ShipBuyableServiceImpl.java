package fr.jodev.elite.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.dao.ShipBuyableDAO;
import fr.jodev.elite.dao.ShipOutfitCategoryDAO;
import fr.jodev.elite.entities.ShipBuyable;
import fr.jodev.elite.entities.ShipOutfitCategory;
import fr.jodev.elite.entities.ShipOutfitSlot;
import fr.jodev.elite.services.ShipBuyableService;

@Service
@Scope("singleton")
public class ShipBuyableServiceImpl implements ShipBuyableService {
	
	@Autowired
	private ShipBuyableDAO shipBuyableDAOImpl;
	
	@Autowired
	private ShipOutfitCategoryDAO shipOutfitCategoryDAOImpl;

	@Override
	@Transactional
	public ShipBuyable createShip(String name) {
		ShipBuyable sb = new ShipBuyable(name);
		shipBuyableDAOImpl.add(sb);
		return sb;
	}

	@Override
	@Transactional
	public void updateShip(long idShipBuyable, String name, long price,
			int mass, float defaultMinRange, float defaultMaxRange) {
		ShipBuyable sb = shipBuyableDAOImpl.getById(idShipBuyable);
		sb.setName(name);
		sb.setPrice(price);
		sb.setDefaultMinRange(defaultMinRange);
		sb.setDefaultMaxRange(defaultMaxRange);
		sb.setMass(mass);
	}

	@Override
	@Transactional
	public List<ShipBuyable> getAll() {
		return shipBuyableDAOImpl.getAll();
	}

	@Override
	@Transactional
	public void addSlotToShip(long idShipBuyable, long idShipOutfitCategory, int size) {
		ShipBuyable sb = shipBuyableDAOImpl.getById(idShipBuyable);
		ShipOutfitCategory soc = shipOutfitCategoryDAOImpl.getById(idShipOutfitCategory);
		sb.addSlot(soc, size);
	}

	@Override
	@Transactional
	public List<ShipOutfitSlot> getShipOutfitSlots(long idShipBuyable) {
		ShipBuyable sb = shipBuyableDAOImpl.getById(idShipBuyable);
		return sb.getSlots();
	}

}
