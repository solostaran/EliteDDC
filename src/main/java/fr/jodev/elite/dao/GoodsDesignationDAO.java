package fr.jodev.elite.dao;

import java.util.List;

import fr.jodev.elite.entities.GoodsDesignation;

public interface GoodsDesignationDAO {
	public void add(GoodsDesignation gd);
	public GoodsDesignation getById(long id);
	public List<GoodsDesignation> getAll();
	public GoodsDesignation getByName(String designation);
}
