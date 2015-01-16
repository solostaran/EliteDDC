package fr.jodev.elite.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class GoodsCommon {
	public long idDesignation;
	public long number = 0L;
	public String lastUpdated;
	
	public GoodsCommon() {}
	public GoodsCommon(fr.jodev.elite.entities.Goods g) {
		this.setIdDesignation(g.getGoodsDesignation().getIdGoodsDesignation());
		this.setNumber(g.getNumber());
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.setLastUpdated(df.format(new Date(g.getLastUpdated())));
	}
	public GoodsCommon(GoodsCommon g) {
		this.setIdDesignation(g.getIdDesignation());
		this.setLastUpdated(g.getLastUpdated());
		this.setNumber(g.getNumber());
	}
	
	public long getIdDesignation() {
		return idDesignation;
	}
	public void setIdDesignation(long idDesignation) {
		this.idDesignation = idDesignation;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
