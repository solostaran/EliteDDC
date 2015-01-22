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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="SOLAR_SYSTEMS")
//@JsonSerialize
@JsonInclude(Include.NON_NULL)
public class SolarSystem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idSolarSystem;
	
	@NaturalId(mutable=true)
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=true)
	private Double X = null;
	@Column(nullable=true)
	private Double Y = null;
	@Column(nullable=true)
	private Double Z = null;
	
//	@JsonIgnore
	@OneToMany(targetEntity=Station.class, mappedBy="parentSolarSystem", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idStation")
	@JsonIdentityReference(alwaysAsId=true)
	private List<Station> stations = new ArrayList<Station>();
	
	protected SolarSystem() {}
	public SolarSystem(String name) {
		this.name = name;
	}
	public SolarSystem(String name, Double x, Double y, Double z) {
		this(name);
		X = x;
		Y = y;
		Z = z;
	}
	
	public long getIdSolarSystem() {
		return idSolarSystem;
	}
	@SuppressWarnings("unused")
	private void setIdSolarSystem(long idSolarSystem) {
		this.idSolarSystem = idSolarSystem;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addStation(Station station) {
		if (!stations.contains(station)) {
			getStations().add(station);
			station.setParentSolarSystem(this);
		}
	}
	
	public void removeStation(Station station) {
		if (stations.contains(station)) {
			stations.remove(station);
		}
	}
	
	public List<Station> getStations() {
		return stations;
	}
	protected void setStations(List<Station> stations) {
		this.stations = stations;
	}
	public int getNbStations() {
		return stations.size();
	}
	
//	@JsonSerialize(using=LongNullableSerializer.class)
	public Double getX() {
		return X;
	}
	public void setX(Double x) {
		X = x;
	}
	
//	@JsonSerialize(using=LongNullableSerializer.class)
	public Double getY() {
		return Y;
	}
	public void setY(Double y) {
		Y = y;
	}
	
//	@JsonSerialize(using=LongNullableSerializer.class)
	public Double getZ() {
		return Z;
	}
	public void setZ(Double z) {
		Z = z;
	}
	
	@Override
	public String toString() {
		return name+",x="+X+",y="+Y+",z="+Z;
	}
}
