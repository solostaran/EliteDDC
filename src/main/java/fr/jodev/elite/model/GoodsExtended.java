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
	public GoodsExtended(fr.jodev.elite.model.GoodsSimplified g) {
		super(g);
		String str = g.getStatus().trim();
		if (!str.isEmpty()) {
			if (Character.isDigit(str.charAt(0))) {
				setPrice(Integer.parseInt(str));
				return;
			}
			SupplyOrDemand sod = SupplyOrDemand.forAbrev(str.charAt(0));
			this.setSupplyOrDemand(sod);
			str = str.substring(1);
			try {
				if (sod != SupplyOrDemand.NONE) {
					if (Character.isDigit(str.charAt(0))) {
						setPrice(Integer.parseInt(str));
						return;
					}
					Priority p = Priority.forAbrev(str.charAt(0));
					this.setPriority(p);
					int i=1;
					while (!Character.isDigit(str.charAt(i++))) {}
					str = str.substring(i-1);
					this.setPrice(Integer.parseInt(str));
				}
			}
			catch(IndexOutOfBoundsException e) {}
			catch(NumberFormatException e) {}
		}
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
