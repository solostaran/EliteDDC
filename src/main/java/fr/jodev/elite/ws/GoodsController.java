package fr.jodev.elite.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.jodev.elite.entities.Goods;
import fr.jodev.elite.entities.GoodsCategory;
import fr.jodev.elite.entities.GoodsDesignation;
import fr.jodev.elite.services.GoodsCategoryService;
import fr.jodev.elite.services.GoodsDesignationService;
import fr.jodev.elite.services.GoodsService;

@RestController
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodsCategoryService goodsCategoryService;
	
	@Autowired
	private GoodsDesignationService goodsDesignationService;
	
	@RequestMapping("/categories")
	public List<GoodsCategory> getCategories() {
		return goodsCategoryService.getAll();
	}
	
	@RequestMapping("/designations")
	public List<GoodsDesignation> getDesignations() {
		return goodsDesignationService.getAll();
	}
	
	@RequestMapping("/get")
	public Goods getOne(@RequestParam(value="station", required=true) long idStation,
			@RequestParam(value="designation", required=true) long idGoodsDesignation) {
		return goodsService.get(idStation, idGoodsDesignation);
	}
	
	@RequestMapping("/station")
	public List<Goods> getByStation(@RequestParam(value="id", required=true) long idStation) {
		return goodsService.getStationMarket(idStation);
	}
}