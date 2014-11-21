package fr.jodev.elite.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import fr.jodev.elite.dao.ShipBuyableDAO;
import fr.jodev.elite.entities.ShipBuyable;

@Repository("shipBuyableDAO")
@Scope("singleton")
public class ShipBuyableDAOImpl extends AbstractDAO implements ShipBuyableDAO {

	@Autowired
	protected void setSessionFactory(SessionFactory sessionFactory) {
		super.sessionFactory = sessionFactory;
	}; 
	
	@Override
	public void add(ShipBuyable sb) {
		saveOrUpdate(sb);
	}

	@Override
	public ShipBuyable getById(long id) {
		ShipBuyable sb = null;
		sb = (ShipBuyable)find(ShipBuyable.class, id);
		return sb;
	}
	
	@Override
	public ShipBuyable getByIdNow(long id) {
		return (ShipBuyable)get(ShipBuyable.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShipBuyable> getAll() {
//		Session session = sessionFactory.getCurrentSession();
//		String hql = "from ShipBuyable";
//		Query query = session.createQuery(hql);
		return (List<ShipBuyable>)findAll(ShipBuyable.class);
	}
}
