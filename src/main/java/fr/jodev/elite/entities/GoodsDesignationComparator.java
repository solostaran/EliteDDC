package fr.jodev.elite.entities;

import java.util.Comparator;

/**
 * Multi-criteria sort.
 * @author JRD
 * @see <a href="http://stackoverflow.com/questions/1421322/how-do-i-sort-a-list-with-multiple-sort-parameters#1421537">How do I sort a list with multiple sort parameters ?</a>
 */
public enum GoodsDesignationComparator implements Comparator<GoodsDesignation> {
	ID_CATEGORY_SORT {
		public int compare(GoodsDesignation gd1, GoodsDesignation gd2) {
			return Long.valueOf(gd1.getCategory().getIdGoodsCategory()).compareTo(gd2.getCategory().getIdGoodsCategory());
		}},
		
	NAME_SORT {
		public int compare(GoodsDesignation gd1, GoodsDesignation gd2) {
			return gd1.getName().toLowerCase().compareTo(gd2.getName().toLowerCase());
		}};

	public static Comparator<GoodsDesignation> descending(final Comparator<GoodsDesignation> other) {
		return new Comparator<GoodsDesignation>() {
			public int compare(GoodsDesignation gd1, GoodsDesignation gd2) {
				return -1 * other.compare(gd1, gd2);
			}
		};
	}
	
	public static Comparator<GoodsDesignation> getComparator(final GoodsDesignationComparator... multipleOptions) {
		return new Comparator<GoodsDesignation>() {
			public int compare(GoodsDesignation gd1, GoodsDesignation gd2) {
				for (GoodsDesignationComparator option : multipleOptions) {
					int result = option.compare(gd1, gd2);
					if (result != 0) {
						return result;
					}
				}
				return 0;
			}
		};
	}
}
