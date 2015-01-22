package fr.jodev.elite.model;

public class GoodsSimplified extends GoodsCommon {
	public String status;
	
	public GoodsSimplified() {}
	public GoodsSimplified(long idDesignation) {
		setIdDesignation(idDesignation);
		lastUpdated = new String();
		status = new String();
	}
	public GoodsSimplified(fr.jodev.elite.entities.Goods g) {
		super(g);
		StringBuffer temp = new StringBuffer();
		if (g.getSupplyOrDemand() <= 0) {
			if (g.getPrice() > 0)
				this.setStatus(String.valueOf(g.getPrice()));
			else
				this.setStatus(new String());
		} else {
			temp.append(SupplyOrDemand.forValue(g.getSupplyOrDemand()).getAbrev());
			temp.append(Priority.forValue(g.getPriority()).getAbrev());
			temp.append(' ');
			temp.append(g.getPrice());
		}
		this.setStatus(temp.toString());
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append(getIdDesignation());
		ret.append(',');
		ret.append(status);
		ret.append(',');
		ret.append(number);
		return ret.toString();
	}
}
