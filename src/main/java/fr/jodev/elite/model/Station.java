package fr.jodev.elite.model;

public class Station {
	public long idStation = -1;
	public String name = null;
	public long parentSolarSystem = -1;
	public String isShipyard = null;
	public String isOutfitting = null;
	public String isMarket = null;
	public String isBlackMarket = null;
	
	public long getIdStation() {
		return idStation;
	}
	public void setIdStation(long idStation) {
		this.idStation = idStation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getParentSolarSystem() {
		return parentSolarSystem;
	}
	public void setParentSolarSystem(long parentSolarSystem) {
		this.parentSolarSystem = parentSolarSystem;
	}
	public String getIsShipyard() {
		return isShipyard;
	}
	public void setIsShipyard(String isShipyard) {
		this.isShipyard = isShipyard;
	}
	public String getIsOutfitting() {
		return isOutfitting;
	}
	public void setIsOutfitting(String isOutfitting) {
		this.isOutfitting = isOutfitting;
	}
	public String getIsMarket() {
		return isMarket;
	}
	public void setIsMarket(String isMarket) {
		this.isMarket = isMarket;
	}
	public String getIsBlackMarket() {
		return isBlackMarket;
	}
	public void setIsBlackMarket(String isBlackMarket) {
		this.isBlackMarket = isBlackMarket;
	}
}
