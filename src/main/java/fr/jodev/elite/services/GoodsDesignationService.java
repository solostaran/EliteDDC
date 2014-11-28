package fr.jodev.elite.services;

import java.util.List;

import fr.jodev.elite.entities.GoodsDesignation;

public interface GoodsDesignationService {
	void add(long idCategory, String name);
	List<GoodsDesignation> getAll();
}
