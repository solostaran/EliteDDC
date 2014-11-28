package fr.jodev.elite.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="GOODS_DESIGNATIONS")
public class GoodsDesignation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idGoodsDesignation;
	
	@NaturalId
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "idGoodsCategory")
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idGoodsCategory")
	@JsonIdentityReference(alwaysAsId=true)
	private GoodsCategory category;
	
	protected GoodsDesignation() {}
	public GoodsDesignation(GoodsCategory gc, String name) {
		this.setCategory(gc);
		this.setName(name);
	}

	public Long getIdGoodsDesignation() {
		return idGoodsDesignation;
	}
	public void setIdGoodsDesignation(Long idGoodsDesignation) {
		this.idGoodsDesignation = idGoodsDesignation;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public GoodsCategory getCategory() {
		return category;
	}
	public void setCategory(GoodsCategory category) {
		this.category = category;
	}
}
