package fr.jodev.elite.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import fr.jodev.elite.dao.GoodsDAO;
import fr.jodev.elite.dao.StationDAO;
import fr.jodev.elite.entities.ShipBuyable;
import fr.jodev.elite.entities.Station;

@Repository("stationDAO")
@Scope("singleton")
public class StationDAOImpl extends AbstractDAO implements StationDAO {
	
	@Autowired
	private GoodsDAO goodsDAO;
	
	@Autowired
	protected void setSessionFactory(SessionFactory sessionFactory) {
		super.sessionFactory = sessionFactory;
	};

	@Override
	public void addStation(Station s) {
		saveOrUpdate(s);
//		goodsDAO.initialize(s);
	}

	@Override
	public Station getById(long id) {
//		Session session = sessionFactory.getCurrentSession();
//		return (Station)session.load(Station.class, id);
		return (Station)find(Station.class, id);
	}
	
	@Override
	public Station getByIdNow(long id) {
//		Session session = sessionFactory.getCurrentSession();
		return (Station)get(Station.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Station> getByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		name = name.replaceAll("'", "''");
		String hql = "from Station s where lower(s.name) like '"+name.toLowerCase()+"%'";
		Query query = session.createQuery(hql);
		return (List<Station>)query.list();
	}
	
	/**
	 * Remove the station and its references.
	 */
	@Override
	public void removeStationById(long id) {
		Station sta =(Station)find(Station.class, id);
		sta.getParentSolarSystem().removeStation(sta);
		deleteById(Station.class, id);
	}

	@Override
	public void addShipBuyable(Station s, ShipBuyable ship) {
		s.addShipBuyable(ship);
		saveOrUpdate(s);
	}

	@Override
	public void removeShipBuyable(Station s, ShipBuyable ship) {
		Session session = sessionFactory.getCurrentSession();
		s.removeShipBuyable(ship);
		session.update(s);
	}
}
