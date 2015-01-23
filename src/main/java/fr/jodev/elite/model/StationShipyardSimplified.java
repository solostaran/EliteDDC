package fr.jodev.elite.model;

import java.util.ArrayList;
import java.util.List;

public class StationShipyardSimplified {
	public long idStation;
	public List<Long> ships = new ArrayList<Long>();
	
	public long getIdStation() {
		return idStation;
	}
	public void setIdStation(long idStation) {
		this.idStation = idStation;
	}
	public List<Long> getShips() {
		return ships;
	}
	public void setShips(List<Long> ships) {
		this.ships = ships;
	}
}
