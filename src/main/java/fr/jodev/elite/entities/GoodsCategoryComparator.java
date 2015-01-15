package fr.jodev.elite.entities;

import java.util.Comparator;

/**
 * Multi-criteria sort.
 * @author JRD
 * @see <a href="http://stackoverflow.com/questions/1421322/how-do-i-sort-a-list-with-multiple-sort-parameters#1421537">How do I sort a list with multiple sort parameters ?</a>
 */
public enum GoodsCategoryComparator implements Comparator<GoodsCategory> {
	ID_CATEGORY_SORT {
		public int compare(GoodsCategory gc1, GoodsCategory gc2) {
			return Long.valueOf(gc1.getIdGoodsCategory()).compareTo(gc2.getIdGoodsCategory());
		}},
		
	NAME_SORT {
		public int compare(GoodsCategory gc1, GoodsCategory gc2) {
			return gc1.getName().toLowerCase().compareTo(gc2.getName().toLowerCase());
		}};

	public static Comparator<GoodsCategory> descending(final Comparator<GoodsCategory> other) {
		return new Comparator<GoodsCategory>() {
			public int compare(GoodsCategory gc1, GoodsCategory gc2) {
				return -1 * other.compare(gc1, gc2);
			}
		};
	}
	
	public static Comparator<GoodsCategory> getComparator(final GoodsCategoryComparator... multipleOptions) {
		return new Comparator<GoodsCategory>() {
			public int compare(GoodsCategory gc1, GoodsCategory gc2) {
				for (GoodsCategoryComparator option : multipleOptions) {
					int result = option.compare(gc1, gc2);
					if (result != 0) {
						return result;
					}
				}
				return 0;
			}
		};
	}
}
