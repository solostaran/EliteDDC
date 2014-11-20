package fr.jodev.elite.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="SOLAR_SYSTEMS")
public class SolarSystem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idSolarSystem;
	
	@Column(nullable=false)
	private String name;
	
	@JsonIgnore
	@OneToMany(targetEntity=Station.class, mappedBy="parentSolarSystem", cascade=CascadeType.ALL)
	private Set<Station> stations = new HashSet<Station>();
	
	protected SolarSystem() {}
	public SolarSystem(String name) {
		this.name = name;
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
	public Set<Station> getStations() {
		return stations;
	}
	protected void setStations(Set<Station> stations) {
		this.stations = stations;
	}
}
