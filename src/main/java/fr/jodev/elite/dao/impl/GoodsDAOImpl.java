package fr.jodev.elite.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import fr.jodev.elite.dao.GoodsDAO;
import fr.jodev.elite.dao.GoodsDesignationDAO;
import fr.jodev.elite.dao.StationDAO;
import fr.jodev.elite.entities.Goods;
import fr.jodev.elite.entities.GoodsDesignation;
import fr.jodev.elite.entities.Station;

@Repository("goodsDAO")
@Scope("singleton")
public class GoodsDAOImpl extends AbstractDAO implements GoodsDAO {

	@Autowired
	protected void setSessionFactory(SessionFactory sessionFactory) {
		super.sessionFactory = sessionFactory;
	};
	
	@Autowired
	private StationDAO stationDAO;
	
	@Autowired
	private GoodsDesignationDAO goodsDesignationDAO;
	
	@Override
	public Goods addOrGet(Station station, GoodsDesignation gd) {
		Session session = sessionFactory.getCurrentSession();
		Goods ret = (Goods) session.createQuery(
				"from Goods g where g.station=:station and g.goodsDesignation=:gd")
			.setEntity("station", station)
			.setEntity("gd", gd)
			.uniqueResult();
		if (ret == null) {
			ret = new Goods(station, gd);
			update(ret);
		}
		return ret;
	}
	
	@Override
	public Goods addOrGet(long idStation, long idGoodsDesignation) {
		Station s = stationDAO.getById(idStation);
		GoodsDesignation gd = goodsDesignationDAO.getById(idGoodsDesignation);
		return addOrGet(s, gd);
	}

	@Override
	public Goods getById(long id) {
		return (Goods)find(Goods.class, id);
	}

	@Override
	public void remove(Goods g) {
		remove(g);
	}

	@Override
	public void update(Goods g) {
		g.setLastUpdated(new Date().getTime());
		saveOrUpdate(g);
	}

//	@SuppressWarnings("unchecked")
	@Override
	public List<Goods> getByStation(Station station) {
//		Session session = sessionFactory.getCurrentSession();
//		List<Goods> list = session.createQuery("from Goods g where g.station = :station")
//			.setParameter("station", station).list();
		List<Goods> list = station.getGoods();
		return list;
	}

}
