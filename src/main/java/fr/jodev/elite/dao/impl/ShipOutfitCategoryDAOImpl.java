package fr.jodev.elite.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import fr.jodev.elite.dao.ShipOutfitCategoryDAO;
import fr.jodev.elite.entities.ShipOutfitCategory;

@Repository("shipOutfitCategoryDAO")
@Scope("singleton")
public class ShipOutfitCategoryDAOImpl extends AbstractDAO implements ShipOutfitCategoryDAO {

//	private static Logger logger = Logger.getLogger("ELITE");
	
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
		ShipOutfitCategory soc = null;
		soc = (ShipOutfitCategory)find(ShipOutfitCategory.class, id);
		return soc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShipOutfitCategory> getAll() {
		List<ShipOutfitCategory> list = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(ShipOutfitCategory.class);
		crit.addOrder(Order.asc("idShipOutfitCategory"));
//		list = (List<ShipOutfitCategory>)findAll(ShipOutfitCategory.class);
		Query query = session.createQuery("from ShipOutfitCategory");
		list = (List<ShipOutfitCategory>)query.list();
		return list;
	}

}
