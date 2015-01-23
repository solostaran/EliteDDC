package fr.jodev.elite.dao.impl;

import static fr.jodev.elite.entities.ShipBuyableComparator.ID_SORT;
import static fr.jodev.elite.entities.ShipBuyableComparator.getComparator;

import java.util.Collections;
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
		List<ShipBuyable> ret = (List<ShipBuyable>)findAll(ShipBuyable.class);
		Collections.sort(ret, getComparator(ID_SORT));
		return ret;
	}
}
