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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
	private Long X = null;
	@Column(nullable=true)
	private Long Y = null;
	@Column(nullable=true)
	private Long Z = null;
	
	@JsonIgnore
	@OneToMany(targetEntity=Station.class, mappedBy="parentSolarSystem", cascade=CascadeType.ALL)
	private List<Station> stations = new ArrayList<Station>();
	
	protected SolarSystem() {}
	public SolarSystem(String name) {
		this.name = name;
	}
	public SolarSystem(String name, Long x, Long y, Long z) {
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
	public List<Station> getStations() {
		return stations;
	}
	protected void setStations(List<Station> stations) {
		this.stations = stations;
	}
	
//	@JsonSerialize(using=LongNullableSerializer.class)
	public Long getX() {
		return X;
	}
	public void setX(Long x) {
		X = x;
	}
	
//	@JsonSerialize(using=LongNullableSerializer.class)
	public Long getY() {
		return Y;
	}
	public void setY(Long y) {
		Y = y;
	}
	
//	@JsonSerialize(using=LongNullableSerializer.class)
	public Long getZ() {
		return Z;
	}
	public void setZ(Long z) {
		Z = z;
	}
}
