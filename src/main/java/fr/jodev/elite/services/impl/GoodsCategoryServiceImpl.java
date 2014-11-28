package fr.jodev.elite.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.dao.GoodsCategoryDAO;
import fr.jodev.elite.entities.GoodsCategory;
import fr.jodev.elite.services.GoodsCategoryService;

@Service
@Scope("singleton")
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
	
	@Autowired
	private GoodsCategoryDAO goodsCategoryDAO;

	@Override
	@Transactional
	public void add(long id, String name) {
		goodsCategoryDAO.add(new GoodsCategory(id,name));
	}

	@Override
	@Transactional
	public List<GoodsCategory> getAll() {
		return goodsCategoryDAO.getAll();
	}

}
