package fr.jodev.elite.model;

import java.util.ArrayList;
import java.util.List;

public class Commodities {
	public long idStation;
	public List<GoodsSubcategory> commodities = new ArrayList<GoodsSubcategory>();
	
	public Commodities(long idStation) {
		this.idStation = idStation;
	}
	
	public long getIdStation() {
		return idStation;
	}
	public void setIdStation(long idStation) {
		this.idStation = idStation;
	}
	public List<GoodsSubcategory> getCommodities() {
		return commodities;
	}
	public void setCommodities(List<GoodsSubcategory> commodities) {
		if (commodities != null) this.commodities = commodities;
	}
	public void addSubcategory(GoodsSubcategory gsc) {
		commodities.add(gsc);
	}
	public void addGoods(int subcat, GoodsExtended g) {
		commodities.get(subcat - 1).addGoods(g);
	}
}
