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
        if (NONE.getName().startsWith(name.toUpperCase())) {
            return NONE;
        } else if (LOW.getName().startsWith(name.toUpperCase())) {
            return LOW;
        } else if (MEDIUM.getName().startsWith(name.toUpperCase())) {
        	return MEDIUM;
        } else if (HIGH.getName().startsWith(name.toUpperCase())) {
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
	
	public static Priority forAbrev(final char abrev) {
		switch(abrev) {
		case 'L': return LOW;
		case 'M': return MEDIUM;
		case 'H': return HIGH;
		default: return NONE;
		}
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
