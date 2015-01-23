package fr.jodev.elite.entities;

import java.util.Comparator;

/**
 * Multi-criteria sort.
 * @author JRD
 * @see <a href="http://stackoverflow.com/questions/1421322/how-do-i-sort-a-list-with-multiple-sort-parameters#1421537">How do I sort a list with multiple sort parameters ?</a>
 */
public enum ShipBuyableComparator implements Comparator<ShipBuyable> {
	ID_SORT {
		public int compare(ShipBuyable sb1, ShipBuyable sb2) {
			return Long.valueOf(sb1.getIdShipBuyable()).compareTo(sb2.getIdShipBuyable());
		}};

	public static Comparator<ShipBuyable> descending(final Comparator<ShipBuyable> other) {
		return new Comparator<ShipBuyable>() {
			public int compare(ShipBuyable sb1, ShipBuyable sb2) {
				return -1 * other.compare(sb1, sb2);
			}
		};
	}

	public static Comparator<ShipBuyable> getComparator(final ShipBuyableComparator... multipleOptions) {
		return new Comparator<ShipBuyable>() {
			public int compare(ShipBuyable sb1, ShipBuyable sb2) {
				for (ShipBuyableComparator option : multipleOptions) {
					int result = option.compare(sb1, sb2);
					if (result != 0) {
						return result;
					}
				}
				return 0;
			}
		};
	}
}
