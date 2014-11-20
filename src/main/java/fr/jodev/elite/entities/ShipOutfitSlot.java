package fr.jodev.elite.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SHIP_OUTFIT_SLOTS")
public class ShipOutfitSlot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idShipOutfitSlot;
	
	@ManyToOne
	@JoinColumn(name = "idShipBuyable", nullable=false)
	private ShipBuyable ship;
	
	@ManyToOne
	@JoinColumn(name = "idShipOutfitCategory")
	private ShipOutfitCategory shipOutfitCategory;
	
	private int size;
	
	protected ShipOutfitSlot() {}
	public ShipOutfitSlot(ShipBuyable sb, ShipOutfitCategory soc, int size) {
		this.ship = sb;
		this.shipOutfitCategory = soc;
		this.size = size;
		sb.addSlot(this);
	}

	public ShipBuyable getShip() {
		return ship;
	}
	public void setShip(ShipBuyable ship) {
		this.ship = ship;
	}
	
	public ShipOutfitCategory getShipOutfitCategory() {
		return shipOutfitCategory;
	}
	public void setShipOutfitCategory(ShipOutfitCategory shipOutfitCategory) {
		this.shipOutfitCategory = shipOutfitCategory;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

}
