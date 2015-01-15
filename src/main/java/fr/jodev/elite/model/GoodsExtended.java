package fr.jodev.elite.model;

public class GoodsExtended extends GoodsCommon {
	public int price = 0;
	public SupplyOrDemand supplyOrDemand = SupplyOrDemand.NONE;
	public Priority priority = Priority.NONE;
	
	public GoodsExtended() {}
	public GoodsExtended(fr.jodev.elite.entities.Goods g) {
		super(g);
		this.setPrice(g.getPrice());
		this.setSupplyOrDemand(SupplyOrDemand.forValue(g.getSupplyOrDemand()));
		this.setPriority(Priority.forValue(g.getPriority()));
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public SupplyOrDemand getSupplyOrDemand() {
		return supplyOrDemand;
	}
	public void setSupplyOrDemand(SupplyOrDemand supplyOrDemand) {
		this.supplyOrDemand = supplyOrDemand;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
}
