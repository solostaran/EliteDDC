package fr.jodev.elite.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

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

	@NaturalId(mutable=true)
	@Column(nullable=false)
	private String name;
	
	/**
	 * Distance from point of arrival.
	 */
	private int distance;

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
	
	@OneToMany(targetEntity=Goods.class, mappedBy="station", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Goods> goods = new ArrayList<Goods>();

	protected Station() {}
	public Station(SolarSystem sys, String name) {
		this.name = name;
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

	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
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
	
	public List<Goods> getGoods() {
		return goods;
	}
	protected void setGoods(List<Goods> goods) {
		this.goods = goods;
	}
	public void addGoods(Goods g) {
		if (!goods.contains(g)) goods.add(g);
	}
	
	@Override
	public String toString() {
		return name+",market="+isMarket+",blackmarket="+isBlackMarket+",shipyard="+isShipyard+",outfitting="+isOutfitting;
	}
}
