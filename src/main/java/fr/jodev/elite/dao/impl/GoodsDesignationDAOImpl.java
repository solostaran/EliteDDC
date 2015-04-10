package fr.jodev.elite.dao.impl;

import static fr.jodev.elite.entities.GoodsDesignationComparator.ID_CATEGORY_SORT;
import static fr.jodev.elite.entities.GoodsDesignationComparator.NAME_SORT;
import static fr.jodev.elite.entities.GoodsDesignationComparator.getComparator;

import java.util.Collections;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import fr.jodev.elite.dao.GoodsDesignationDAO;
import fr.jodev.elite.entities.GoodsDesignation;

@Repository("goodsDesignationDAO")
@Scope("singleton")
public class GoodsDesignationDAOImpl extends AbstractDAO implements
		GoodsDesignationDAO {
	
	private List<GoodsDesignation> listGd; // Cache with sorted entities
	
	@Autowired
	protected void setSessionFactory(SessionFactory sessionFactory) {
		super.sessionFactory = sessionFactory;
	};

	@Override
	public void add(GoodsDesignation gd) {
		saveOrUpdate(gd);
	}

	@Override
	public GoodsDesignation getById(long id) {
		return (GoodsDesignation)find(GoodsDesignation.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsDesignation> getAll() {
		if (listGd == null) {
			listGd = (List<GoodsDesignation>)findAll(GoodsDesignation.class);
			Collections.sort(listGd, getComparator(ID_CATEGORY_SORT, NAME_SORT));
		}
		return listGd;
	}

	@Override
	public GoodsDesignation getByName(String designation) {
		getAll();
		for (GoodsDesignation gd : listGd) {
			if (gd.getName().equalsIgnoreCase(designation))
				return gd;
		}
		return null;
	}

}
