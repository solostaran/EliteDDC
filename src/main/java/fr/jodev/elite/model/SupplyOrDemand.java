package fr.jodev.elite.model;

public enum SupplyOrDemand {
	
	NONE("none",'N', 0),
	SUPPLY("supply", 'S', 1),
	DEMAND("demand", 'D', 2);
	
	public static final SupplyOrDemand[] ALL = { NONE, SUPPLY, DEMAND };
	
	private final String name;
	private final Character abrev;
	private final int value;
	
	private SupplyOrDemand(final String name, final Character abrev, final int value) {
        this.name = name;
        this.abrev = abrev;
        this.value = value;
    }
	
	public String getName() {
		return name;
	}
	
	public Character getAbrev() {
		return abrev;
	}
	
	public int getValue() {
		return value;
	}

	@Override
    public String toString() {
        return getName();
    }
	
	public static SupplyOrDemand forName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null for SupplyOrDemand");
        }
        if (name.toLowerCase().equals(NONE.getName())) {
            return NONE;
        } else if (name.toLowerCase().equals(SUPPLY.getName())) {
            return SUPPLY;
        } else if (name.toLowerCase().equals(DEMAND.getName())) {
        	return DEMAND;
        }
        throw new IllegalArgumentException("Name \"" + name + "\" does not correspond to any SupplyOrDemand");
    }
	
	public static SupplyOrDemand forValue(final int value) {
		switch(value) {
		case 0: return NONE;
		case 1: return SUPPLY;
		case 2: return DEMAND;
		default:
			throw new IllegalArgumentException("Value \""+value+"\" does not correspond to any SupplyOrDemand");
		}
	}
}
