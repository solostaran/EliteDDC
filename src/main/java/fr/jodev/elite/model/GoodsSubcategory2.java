package fr.jodev.elite.model;

import java.util.ArrayList;
import java.util.List;

public class GoodsSubcategory2 {
	public long idCategory;
//	public String name;
	public List<GoodsSimplified> goods = new ArrayList<GoodsSimplified>();
	
	public long getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(long idCategory) {
		this.idCategory = idCategory;
	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
	public List<GoodsSimplified> getGoods() {
		return goods;
	}
	public void setGoods(List<GoodsSimplified> goods) {
		if (goods != null) this.goods = goods;
	}
	public void addGoods(GoodsSimplified g) {
		goods.add(g);
	}
}
