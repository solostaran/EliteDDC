package fr.jodev.elite.model;

import java.util.List;

public class StationMarket {
	public long idStation;
	public List<GoodsForDisplay> goods;
	
	public StationMarket() {};
	public StationMarket(long idStation) {
		this.idStation = idStation;
	}
	
	public long getIdStation() {
		return idStation;
	}
	public void setIdStation(long idStation) {
		this.idStation = idStation;
	}
	public List<GoodsForDisplay> getGoods() {
		return goods;
	}
	public void setGoods(List<GoodsForDisplay> goods) {
		this.goods = goods;
	}
}
