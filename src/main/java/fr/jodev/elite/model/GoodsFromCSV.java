package fr.jodev.elite.model;

import java.util.List;

import com.googlecode.jcsv.annotations.MapToColumn;

import fr.jodev.elite.CSVHelper;

public class GoodsFromCSV {
	@MapToColumn(column=0)
	private String solarSystemName;
	@MapToColumn(column=1)
	private String stationName;
	@MapToColumn(column=2)
	private String commodityDesignation;
	@MapToColumn(column=3)
	private int sellPrice = 0;
	@MapToColumn(column=4)
	private int buyPrice = 0;
	@MapToColumn(column=5)
	private long demandNumber = 0;
	@MapToColumn(column=6)
	private String demandPriority;
	@MapToColumn(column=7)
	private long supplyNumber = 0;
	@MapToColumn(column=8)
	private String supplyPriority;
	@MapToColumn(column=9)
	private String date;
	
	
	public String getSolarSystemName() {
		return solarSystemName;
	}
	public void setSolarSystemName(String solarSystemName) {
		this.solarSystemName = solarSystemName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getCommodityDesignation() {
		return commodityDesignation;
	}
	public void setCommodityDesignation(String commodityDesignation) {
		this.commodityDesignation = commodityDesignation;
	}
	public int getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	public int getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	public long getDemandNumber() {
		return demandNumber;
	}
	public void setDemandNumber(long demandNumber) {
		this.demandNumber = demandNumber;
	}
	public String getDemandPriority() {
		return demandPriority;
	}
	public void setDemandPriority(String demandPriority) {
		this.demandPriority = demandPriority;
	}
	public long getSupplyNumber() {
		return supplyNumber;
	}
	public void setSupplyNumber(long supplyNumber) {
		this.supplyNumber = supplyNumber;
	}
	public String getSupplyPriority() {
		return supplyPriority;
	}
	public void setSupplyPriority(String supplyPriority) {
		this.supplyPriority = supplyPriority;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString() {
		return solarSystemName+";"+stationName+";"+commodityDesignation+";"+sellPrice+";"+buyPrice;
	}
	
	public static GoodsFromCSV constructFromStrings(List<String> values) {
		int index = 0;
		GoodsFromCSV gfc = new GoodsFromCSV();
		gfc.setSolarSystemName(values.get(index++));
		gfc.setStationName(values.get(index++));
		gfc.setCommodityDesignation(values.get(index++));
		gfc.setSellPrice(CSVHelper.parseInt(values.get(index++)));
		gfc.setBuyPrice(CSVHelper.parseInt(values.get(index++)));
		gfc.setDemandNumber(CSVHelper.parseLong(values.get(index++)));
		gfc.setDemandPriority(values.get(index++));
		gfc.setSupplyNumber(CSVHelper.parseLong(values.get(index++)));
		gfc.setSupplyPriority(values.get(index++));
		gfc.setDate(values.get(index));
		return gfc;
	}
}
