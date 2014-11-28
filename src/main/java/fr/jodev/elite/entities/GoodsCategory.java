package fr.jodev.elite.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="GOODS_CATEGORIES")
public class GoodsCategory {
	@Id
	private Long idGoodsCategory;
	
	@NaturalId
	@Column(nullable=false)
	private String name;
	
	protected GoodsCategory() {}
	public GoodsCategory(long id, String name) {
		this.setIdGoodsCategory(id);
		this.setName(name);
	}
	
	public Long getIdGoodsCategory() {
		return idGoodsCategory;
	}
	public void setIdGoodsCategory(Long idShipOutfitCategory) {
		this.idGoodsCategory = idShipOutfitCategory;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
