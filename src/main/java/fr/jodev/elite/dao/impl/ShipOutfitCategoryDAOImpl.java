package fr.jodev.elite.dao.impl;

import static fr.jodev.elite.entities.OutfitCategoryComparator.ID_SORT;
import static fr.jodev.elite.entities.OutfitCategoryComparator.getComparator;

import java.util.Collections;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import fr.jodev.elite.dao.ShipOutfitCategoryDAO;
import fr.jodev.elite.entities.ShipOutfitCategory;

@Repository("shipOutfitCategoryDAO")
@Scope("singleton")
public class ShipOutfitCategoryDAOImpl extends AbstractDAO implements ShipOutfitCategoryDAO {

//	private static Logger logger = Logger.getLogger("ELITE");
	
	private List<ShipOutfitCategory> listSoc; // Cache with sorted entities
	
	@Autowired
	protected void setSessionFactory(SessionFactory sessionFactory) {
		super.sessionFactory = sessionFactory;
	}; 
	
	@Override
	public void add(ShipOutfitCategory soc) {
		saveOrUpdate(soc);
	}

	@Override
	public ShipOutfitCategory getById(long id) {
		return (ShipOutfitCategory)find(ShipOutfitCategory.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShipOutfitCategory> getAll() {
		if (listSoc == null) {
			listSoc = (List<ShipOutfitCategory>)findAll(ShipOutfitCategory.class);
			Collections.sort(listSoc, getComparator(ID_SORT));
		}
		return listSoc;
	}

}
