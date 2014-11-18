package fr.jodev.elite.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import fr.jodev.elite.dao.SystemDAO;
import fr.jodev.elite.entities.SolarSystem;

@Repository("systemDAO")
@Scope("singleton")
public class SystemDAOImpl implements SystemDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addSolarSystem(SolarSystem sys) {
		Session session = sessionFactory.getCurrentSession();
		session.save(sys);
	}
	
	@Override
	public void removeSolarSystem(SolarSystem sys) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(sys);
	}

	@Override
	public SolarSystem getById(long id) {
		Session session = sessionFactory.getCurrentSession();
		SolarSystem sys = null;
		sys = (SolarSystem)session.load(SolarSystem.class, id);
		session.update(sys);
		return sys;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolarSystem> getByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from SolarSystem sls where lower(sls.name) like '"+name.toLowerCase()+"%'";
//		String hql = "from SolarSystem where name = :name";
		Query query = session.createQuery(hql);
//		query.setParameter("name", name);
		return (List<SolarSystem>)query.list();
	}
}
