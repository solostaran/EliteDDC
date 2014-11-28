package fr.jodev.elite.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.jodev.elite.dao.GoodsCategoryDAO;
import fr.jodev.elite.dao.GoodsDesignationDAO;
import fr.jodev.elite.entities.GoodsCategory;
import fr.jodev.elite.entities.GoodsDesignation;
import fr.jodev.elite.services.GoodsDesignationService;

@Service
@Scope("singleton")
public class GoodsDesignationServiceImpl implements GoodsDesignationService {
	
	@Autowired
	private GoodsDesignationDAO goodsDesignationDAO;
	
	@Autowired
	private GoodsCategoryDAO goodsCategoryDAO;

	@Override
	@Transactional
	public void add(long idCategory, String name) {
		GoodsCategory gc = goodsCategoryDAO.getById(idCategory);
		GoodsDesignation gd = new GoodsDesignation(gc, name);
		goodsDesignationDAO.add(gd);
	}

	@Override
	@Transactional
	public List<GoodsDesignation> getAll() {
		return goodsDesignationDAO.getAll();
	}

}
