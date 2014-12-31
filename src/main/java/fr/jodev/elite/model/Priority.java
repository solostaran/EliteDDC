package fr.jodev.elite.model;

public enum Priority {
	
	NONE("NONE", 'N', 0),
	LOW("LOW", 'L', 1),
	MEDIUM("MEDIUM", 'M', 2),
	HIGH("HIGH", 'H', 3);
	
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
        if (name.toUpperCase().equals(NONE.getName())) {
            return NONE;
        } else if (name.toUpperCase().equals(LOW.getName())) {
            return LOW;
        } else if (name.toUpperCase().equals(MEDIUM.getName())) {
        	return MEDIUM;
        } else if (name.toUpperCase().equals(HIGH.getName())) {
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
	
	@Override
	public String toString() {
		return getName();
	}
}
