package fr.jodev.elite.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="SHIP_BUYABLES")
public class ShipBuyable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idShipBuyable;
	
	@NaturalId
	@Column(nullable=false)
	private String name;
	
	private long price;
	private float defaultMinRange;
	private float defaultMaxRange;
	private int mass;
	
	@JsonIgnore
	@OneToMany(targetEntity=ShipOutfitSlot.class, mappedBy = "ship", cascade=CascadeType.ALL)
	private List<ShipOutfitSlot> slots = new ArrayList<ShipOutfitSlot>();
	
	protected ShipBuyable() {}
	public ShipBuyable(String name) {
		this.name = name;
	}	

	public Long getIdShipBuyable() {
		return idShipBuyable;
	}
	@SuppressWarnings("unused")
	private void setIdShipBuyable(Long idShipBuyable) {
		this.idShipBuyable = idShipBuyable;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	public float getDefaultMinRange() {
		return defaultMinRange;
	}
	public void setDefaultMinRange(float defaultMinRange) {
		this.defaultMinRange = defaultMinRange;
	}
	
	public float getDefaultMaxRange() {
		return defaultMaxRange;
	}
	public void setDefaultMaxRange(float defaultMaxRange) {
		this.defaultMaxRange = defaultMaxRange;
	}
	
	public int getMass() {
		return mass;
	}
	public void setMass(int mass) {
		this.mass = mass;
	}
	
	public void addSlot(ShipOutfitSlot slot) {
		if (!slots.contains(slot) && slot.getShip() == this) {
			slots.add(slot);
		}
	}
	public void addSlot(ShipOutfitCategory soc, int size) {
		new ShipOutfitSlot(this, soc, size); // call addSlot(ShipOutfitSlot)
	}
	public List<ShipOutfitSlot> getSlots() {
		return slots;
	}
	protected void setSlots(List<ShipOutfitSlot> slots) {
		this.slots = slots;
	}
}
