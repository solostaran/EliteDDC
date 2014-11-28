package fr.jodev.elite.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="STATIONS")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idStation")
public class Station {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idStation;

	@Column(nullable=false)
	private String name;

	private boolean isShipyard;
	private boolean isOutfitting;
	private boolean isMarket;
	private boolean isBlackMarket;

	@ManyToOne
	@JoinColumn(name="idSolarSystem", nullable=false)
	//	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idSolarSystem")
	//	@JsonUnwrapped(prefix="parentSolarSystem.")
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idSolarSystem")
	@JsonIdentityReference(alwaysAsId=true)
	private SolarSystem parentSolarSystem;

	@ManyToMany
	@JoinTable (
			name="STATION_SHIPYARD",
			joinColumns = {@JoinColumn(name="idStation")},
			inverseJoinColumns={@JoinColumn(name="idShipBuyable")}
			)
//	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idShipBuyable")
//	@JsonIdentityReference(alwaysAsId=true)
	@JsonIgnore
	private List<ShipBuyable> stationShipyard = new ArrayList<ShipBuyable>();

	protected Station() {}
	protected Station(String name) {
		this();
		this.name = name;
	}
	public Station(SolarSystem sys, String name) {
		this(name);
		sys.addStation(this);
	}

	public long getIdStation() {
		return idStation;
	}
	@SuppressWarnings("unused")
	private void setIdStation(long idStation) {
		this.idStation = idStation;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsShipyard() {
		return isShipyard;
	}
	public void setIsShipyard(boolean isShipyard) {
		this.isShipyard = isShipyard;
	}

	public boolean getIsOutfitting() {
		return isOutfitting;
	}
	public void setIsOutfitting(boolean isOutfitting) {
		this.isOutfitting = isOutfitting;
	}

	public boolean getIsMarket() {
		return isMarket;
	}
	public void setIsMarket(boolean isMarket) {
		this.isMarket = isMarket;
	}

	public boolean getIsBlackMarket() {
		return isBlackMarket;
	}
	public void setIsBlackMarket(boolean isBlackMarket) {
		this.isBlackMarket = isBlackMarket;
	}

	public SolarSystem getParentSolarSystem() {
		return parentSolarSystem;
	}
	public void setParentSolarSystem(SolarSystem parentSolarSystem) {
		this.parentSolarSystem = parentSolarSystem;
	}

	public List<ShipBuyable> getStationShipyard() {
		return stationShipyard;
	}
	public void setStationShipyard(List<ShipBuyable> stationShipyard) {
		this.stationShipyard = stationShipyard;
	}
	public void addShipBuyable(ShipBuyable ship) {
		if (!stationShipyard.contains(ship)) {
			stationShipyard.add(ship);
		}
	}
	public void removeShipBuyable(ShipBuyable ship) {
		if (stationShipyard.contains(ship)) {
			stationShipyard.remove(ship);
		}
	}
}
