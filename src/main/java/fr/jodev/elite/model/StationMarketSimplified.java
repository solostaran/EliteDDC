package fr.jodev.elite.model;

import java.util.List;

public class StationMarketSimplified {
	public long idStation;
	public List<GoodsSimplified> goods;
	
	public StationMarketSimplified() {}
	public StationMarketSimplified(long idStation) {
		this.idStation = idStation;
	}
	
	public long getIdStation() {
		return idStation;
	}
	public void setIdStation(long idStation) {
		this.idStation = idStation;
	}
	public List<GoodsSimplified> getGoods() {
		return goods;
	}
	public void setGoods(List<GoodsSimplified> goods) {
		this.goods = goods;
	}
}
