package fr.jodev.elite.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import fr.jodev.elite.dao.StationDAO;
import fr.jodev.elite.entities.Station;

@Repository("stationDAO")
@Scope("singleton")
public class StationDAOImpl implements StationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addStation(Station s) {
		Session session = sessionFactory.getCurrentSession();
		session.save(s);
	}

	@Override
	public Station getById(long id) {
		Session session = sessionFactory.getCurrentSession();
		Station s = (Station)session.load(Station.class, id);
		session.update(s);
		return s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Station> getByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Station s where lower(s.name) like '"+name.toLowerCase()+"%'";
		Query query = session.createQuery(hql);
		return (List<Station>)query.list();
	}

}
