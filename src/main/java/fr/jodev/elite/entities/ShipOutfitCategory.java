package fr.jodev.elite.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="SHIP_OUTFIT_CATEGORIES")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="name")
public class ShipOutfitCategory {
	@Id
	@Column(nullable=false)
	private Long idShipOutfitCategory;
	
	@NaturalId
	@Column(nullable=false)
	private String name;
	
	protected ShipOutfitCategory() {}
	public ShipOutfitCategory(long id, String name) {
		this.setIdShipOutfitCategory(id);
		this.setName(name);
	}
	
	public Long getIdShipOutfitCategory() {
		return idShipOutfitCategory;
	}
	protected void setIdShipOutfitCategory(Long idShipOutfitCategory) {
		this.idShipOutfitCategory = idShipOutfitCategory;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
