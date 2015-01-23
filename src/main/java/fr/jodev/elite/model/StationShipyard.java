package fr.jodev.elite.model;

import java.util.ArrayList;
import java.util.List;

public class StationShipyard {
	public long idSolarSystem;
	public String solarSystemName;
	public long idStation;
	public String stationName;
	public List<ShipBuyableChecked> ships = new ArrayList<ShipBuyableChecked>();
	
	public StationShipyard() {};
	public StationShipyard(long idStation) {
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
	
	public List<ShipBuyableChecked> getShips() {
		return ships;
	}
	public void setShips(List<ShipBuyableChecked> ships) {
		this.ships = ships;
	}
	public void addShipBuyableChecked(ShipBuyableChecked sbc) {
		if (sbc == null) return;
		for (ShipBuyableChecked temp : ships) {
			if (temp.getIdShipBuyable() == sbc.getIdShipBuyable()) return;
		}
		ships.add(sbc);
	}
}
