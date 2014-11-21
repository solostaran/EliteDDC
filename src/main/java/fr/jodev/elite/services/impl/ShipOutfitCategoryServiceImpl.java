package fr.jodev.elite.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.dao.ShipOutfitCategoryDAO;
import fr.jodev.elite.entities.ShipOutfitCategory;
import fr.jodev.elite.services.ShipOutfitCategoryService;

@Service
@Scope("singleton")
public class ShipOutfitCategoryServiceImpl implements ShipOutfitCategoryService {
	
	// Memory Cache
	private List<String> names;
	
	@Autowired
	private ShipOutfitCategoryDAO shipOutfitCategoryDAO;

	@Override
	@Transactional
	public List<ShipOutfitCategory> getAll() {
		return shipOutfitCategoryDAO.getAll();
	}

	@Override
	@Transactional
	public void add(int id, String name) {
		shipOutfitCategoryDAO.add(new ShipOutfitCategory(id,name));
	}

	@Override
	@Transactional
	public List<String> getNames() {
		if (names == null) {
			List<ShipOutfitCategory> list = shipOutfitCategoryDAO.getAll();
			names = new ArrayList<String>();
			for (ShipOutfitCategory soc : list)
				names.add(soc.getName());
		}
		return names;
	}

}
