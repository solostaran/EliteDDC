package fr.jodev.elite.dao.impl;

import static fr.jodev.elite.entities.GoodsCategoryComparator.ID_CATEGORY_SORT;
import static fr.jodev.elite.entities.GoodsCategoryComparator.getComparator;

import java.util.Collections;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import fr.jodev.elite.dao.GoodsCategoryDAO;
import fr.jodev.elite.entities.GoodsCategory;

@Repository("goodsCategoryDAO")
@Scope("singleton")
public class GoodsCategoryDAOImpl extends AbstractDAO implements
		GoodsCategoryDAO {

	@Autowired
	protected void setSessionFactory(SessionFactory sessionFactory) {
		super.sessionFactory = sessionFactory;
	};
	
	private List<GoodsCategory> listGc; // Cache with sorted entities
	
	@Override
	public void add(GoodsCategory gc) {
		saveOrUpdate(gc);
	}

	@Override
	public GoodsCategory getById(long id) {
		return (GoodsCategory)find(GoodsCategory.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsCategory> getAll() {
		if (listGc == null)
			listGc = (List<GoodsCategory>)findAll(GoodsCategory.class);
			Collections.sort(listGc, getComparator(ID_CATEGORY_SORT));
		return listGc;
	}
}
