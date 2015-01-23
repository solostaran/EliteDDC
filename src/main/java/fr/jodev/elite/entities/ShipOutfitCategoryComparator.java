package fr.jodev.elite.entities;

import java.util.Comparator;

/**
 * Multi-criteria sort.
 * @author JRD
 * @see <a href="http://stackoverflow.com/questions/1421322/how-do-i-sort-a-list-with-multiple-sort-parameters#1421537">How do I sort a list with multiple sort parameters ?</a>
 */
public enum ShipOutfitCategoryComparator implements Comparator<ShipOutfitCategory> {
	ID_SORT {
		public int compare(ShipOutfitCategory soc1, ShipOutfitCategory soc2) {
			return Long.valueOf(soc1.getIdShipOutfitCategory()).compareTo(soc2.getIdShipOutfitCategory());
		}};

	public static Comparator<ShipOutfitCategory> descending(final Comparator<ShipOutfitCategory> other) {
		return new Comparator<ShipOutfitCategory>() {
			public int compare(ShipOutfitCategory soc1, ShipOutfitCategory soc2) {
				return -1 * other.compare(soc1, soc2);
			}
		};
	}

	public static Comparator<ShipOutfitCategory> getComparator(final ShipOutfitCategoryComparator... multipleOptions) {
		return new Comparator<ShipOutfitCategory>() {
			public int compare(ShipOutfitCategory soc1, ShipOutfitCategory soc2) {
				for (ShipOutfitCategoryComparator option : multipleOptions) {
					int result = option.compare(soc1, soc2);
					if (result != 0) {
						return result;
					}
				}
				return 0;
			}
		};
	}
}
