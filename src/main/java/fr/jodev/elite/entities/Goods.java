package fr.jodev.elite.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fr.jodev.elite.DateNumberSerializer;

@Entity
@Table(name="GOODS")
public class Goods {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private Long idGoods;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idStation", nullable=false)
	private Station station;
	
	@ManyToOne
	@JoinColumn(name = "idGoodsDesignation", nullable=false)
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idGoodsDesignation")
	@JsonIdentityReference(alwaysAsId=true)
	private GoodsDesignation goodsDesignation;
	
	private int price = 0;
	private long number = 0;
	private int supplyOrDemand = 0;
	private int priority = 0;
	
	@Column(nullable=false)
	private long lastUpdated = 0;
	
	protected Goods() {}
	public Goods(Station station, GoodsDesignation gd) {
		this.setStation(station);
		this.setGoodsDesignation(gd);
	}
	
	public Long getIdGoods() {
		return idGoods;
	}
	@SuppressWarnings("unused")
	private void setIdGoods(Long idGoods) {
		this.idGoods = idGoods;
	}
	
	public Station getStation() {
		return station;
	}
	private void setStation(Station station) {
		this.station = station;
	}
	
	public GoodsDesignation getGoodsDesignation() {
		return goodsDesignation;
	}
	private void setGoodsDesignation(GoodsDesignation goodsDesignation) {
		this.goodsDesignation = goodsDesignation;
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
	
	@JsonSerialize(using=DateNumberSerializer.class)
	public long getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
