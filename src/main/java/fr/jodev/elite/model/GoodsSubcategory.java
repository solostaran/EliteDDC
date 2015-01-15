package fr.jodev.elite.model;

import java.util.ArrayList;
import java.util.List;

public class GoodsSubcategory {
	public long idCategory;
//	public String name;
	public List<GoodsExtended> goods = new ArrayList<GoodsExtended>();
	
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
	public List<GoodsExtended> getGoods() {
		return goods;
	}
	public void setGoods(List<GoodsExtended> goods) {
		if (goods != null) this.goods = goods;
	}
	public void addGoods(GoodsExtended g) {
		goods.add(g);
	}
}
