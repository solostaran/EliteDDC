package fr.jodev.elite.model;

public class Goods {
	public long idStation = -1;
	public long idDesignation = -1;
	public int price = -1;
	public long number = -1L;
	public int supplyOrDemand = -1;
	public int priority = -1;
	
	public long getIdStation() {
		return idStation;
	}
	public void setIdStation(long idStation) {
		this.idStation = idStation;
	}
	public long getIdDesignation() {
		return idDesignation;
	}
	public void setIdDesignation(long idDesignation) {
		this.idDesignation = idDesignation;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public int getSupplyOrDemand() {
		return supplyOrDemand;
	}
	public void setSupplyOrDemand(int supplyOrDemand) {
		this.supplyOrDemand = supplyOrDemand;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
