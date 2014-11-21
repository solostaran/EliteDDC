package fr.jodev.elite.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.jodev.elite.entities.ShipBuyable;
import fr.jodev.elite.entities.ShipOutfitSlot;
import fr.jodev.elite.services.ShipBuyableService;

@RestController
@RequestMapping("/ship/buyables")
public class ShipBuyableController {
	
	@Autowired
	private ShipBuyableService shipBuyableService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ShipBuyable> getAll() {
		return shipBuyableService.getAll();
	}
	
	@RequestMapping("/{id}")
	public ShipBuyable getById(@PathVariable Long id) {
		return shipBuyableService.getById(id);
	}
	
	@RequestMapping("/{id}/slots")
	public List<ShipOutfitSlot> getSlots(@PathVariable Long id) {
		return shipBuyableService.getShipOutfitSlots(id);
	}

}
