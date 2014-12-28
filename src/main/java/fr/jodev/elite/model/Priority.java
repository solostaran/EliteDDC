package fr.jodev.elite.model;

public enum Priority {
	
	NONE("none", 'N', 0),
	LOW("low", 'L', 1),
	MEDIUM("medium", 'M', 2),
	HIGH("high", 'H', 3);
	
	public static final Priority [] ALL = { NONE, LOW, MEDIUM, HIGH };
	
	private final String name;
	private final Character abrev;
	private final int value;
	
	private Priority(String name, Character abrev, int value) {
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
	
	public static Priority forName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null for SupplyOrDemand");
        }
        if (name.toLowerCase().equals(NONE.getName())) {
            return NONE;
        } else if (name.toLowerCase().equals(LOW.getName())) {
            return LOW;
        } else if (name.toLowerCase().equals(MEDIUM.getName())) {
        	return MEDIUM;
        } else if (name.toLowerCase().equals(HIGH.getName())) {
        	return HIGH;
        }
        throw new IllegalArgumentException("Name \"" + name + "\" does not correspond to any Priority");
    }
	
	public static Priority forValue(final int value) {
		switch(value) {
		case 0: return NONE;
		case 1: return LOW;
		case 2: return MEDIUM;
		case 3: return HIGH;
		default:
			throw new IllegalArgumentException("Value \""+value+"\" does not correspond to any Priority");
		}
	}
}
