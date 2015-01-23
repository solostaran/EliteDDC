package fr.jodev.elite.model;

public class ShipBuyableChecked {
	public long idShipBuyable;
	public boolean checked = false;
	
	public ShipBuyableChecked() {}
	public ShipBuyableChecked(long idShipBuyable) {
		this.idShipBuyable = idShipBuyable;
	}
	
	public long getIdShipBuyable() {
		return idShipBuyable;
	}
	public void setIdShipBuyable(long idShipBuyable) {
		this.idShipBuyable = idShipBuyable;
	}
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
