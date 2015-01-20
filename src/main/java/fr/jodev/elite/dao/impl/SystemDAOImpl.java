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
public class SystemDAOImpl extends AbstractDAO implements SystemDAO {

	@Autowired
	protected void setSessionFactory(SessionFactory sessionFactory) {
		super.sessionFactory = sessionFactory;
	};

	@Override
	public void addSolarSystem(SolarSystem sys) {
		saveOrUpdate(sys);
	}
	
	@Override
	public void removeSolarSystem(SolarSystem sys) {
		delete(sys);
	}
	
	/**
	 * Remove a solar system and its references.
	 */
	@Override
	public void removeSolarSystemById(long id) {
		deleteById(SolarSystem.class, id);
	}

	@Override
	public SolarSystem getById(long id) {
		return (SolarSystem)find(SolarSystem.class, id);
	}
	
	@Override
	public SolarSystem getByIdNow(long id) {
		return (SolarSystem)get(SolarSystem.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolarSystem> getByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		name = name.replaceAll("'", "''");
		final String hql = "from SolarSystem sls where lower(sls.name) like '"+name.toLowerCase()+"%'";
//		String hql = "from SolarSystem where name = :name";
		Query query = session.createQuery(hql);
//		query.setParameter("name", name);
		return (List<SolarSystem>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolarSystem> getByProximity(SolarSystem sys, float distance) {
		Session session = sessionFactory.getCurrentSession();
		if (sys.getX() == null || sys.getY() == null || sys.getZ() == null) return null;
		final String hql = "from SolarSystem sls where (sls.X between "+
				(sys.getX()-distance)+" and "+(sys.getX()+distance)+") and (sls.Y between "+
				(sys.getY()-distance)+" and "+(sys.getY()+distance)+") and (sls.Z between "+
				(sys.getZ()-distance)+" and "+(sys.getZ()+distance)+")";
		Query query = session.createQuery(hql);
		return (List<SolarSystem>)query.list();
	}
}
