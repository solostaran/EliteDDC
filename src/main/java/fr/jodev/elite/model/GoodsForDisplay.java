package fr.jodev.elite.model;

public class GoodsForDisplay {
	public String category;
	public String designation;
	public int price = -1;
	public long number = -1L;
	public SupplyOrDemand supplyOrDemand = SupplyOrDemand.NONE;
	public Priority priority = Priority.NONE;
	public String lastUpdated;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
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
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public String toString() {
		StringBuffer sod = new StringBuffer();
		if (supplyOrDemand != SupplyOrDemand.NONE) {
			sod.append(supplyOrDemand.getAbrev());
			sod.append(priority.getAbrev());
			sod.append(' ');
			sod.append(price);
			sod.append(',');
			sod.append(number);
		}
		return category+","+designation+","+sod;
	}
}
