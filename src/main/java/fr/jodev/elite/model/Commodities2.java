package fr.jodev.elite.model;

import java.util.ArrayList;
import java.util.List;

public class Commodities2 {
	public long idSolarSystem;
	public String solarSystemName;
	public long idStation;
	public String stationName;
	public List<GoodsSubcategory2> commodities = new ArrayList<GoodsSubcategory2>();
	
	public Commodities2() {}
	public Commodities2(long idStation) {
		this.idStation = idStation;
	}
	
	public long getIdSolarSystem() {
		return idSolarSystem;
	}
	public void setIdSolarSystem(long idSolarSystem) {
		this.idSolarSystem = idSolarSystem;
	}
	public String getSolarSystemName() {
		return solarSystemName;
	}
	public void setSolarSystemName(String solarSystemName) {
		this.solarSystemName = solarSystemName;
	}
	public long getIdStation() {
		return idStation;
	}
	public void setIdStation(long idStation) {
		this.idStation = idStation;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public List<GoodsSubcategory2> getCommodities() {
		return commodities;
	}
	public void setCommodities(List<GoodsSubcategory2> commodities) {
		if (commodities != null) this.commodities = commodities;
	}
	public void addSubcategory(GoodsSubcategory2 gsc) {
		commodities.add(gsc);
	}
	public void addGoods(int subcat, GoodsSimplified g) {
		commodities.get(subcat - 1).addGoods(g);
	}
}
