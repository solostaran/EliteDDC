package fr.jodev.elite.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.jodev.elite.entities.ShipOutfitCategory;
import fr.jodev.elite.services.ShipOutfitCategoryService;

@RestController
@RequestMapping("/ship/outfitcategories")
public class ShipOutfitCategoryController {

	@Autowired
	private ShipOutfitCategoryService shipOutfitCategoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ShipOutfitCategory> getAll() {
		return shipOutfitCategoryService.getAll();
	}
	
	@RequestMapping("/names")
	public List<String> listNames() {
		return shipOutfitCategoryService.getNames();
	}
}
