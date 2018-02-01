package com.vg.shoppingapp.rule;

import java.util.List;
import java.util.Map;

import com.vg.shoppingapp.bean.Item;

/**
 * 
 * @author VG
 *
 */
public class BulkPricingRule extends PricingRule {

	private final int bulkSize;
	private final Double reducedPricePerItem;

	public BulkPricingRule(Item discountedItem, int bulkSize,
			Double reducedPricePerItem) {
		this.discountedItem = discountedItem;
		this.bulkSize = bulkSize;
		this.reducedPricePerItem = reducedPricePerItem != null ? reducedPricePerItem
				: 0.0;
	}

	@Override
	public Double applyDiscount(Map<Item, Integer> itemsPerQuantity,
			List<Item> items) {

		if (null != discountedItem
				&& itemsPerQuantity.containsKey(discountedItem)
				&& itemsPerQuantity.get(discountedItem) > bulkSize) {

			return (discountedItem.getPrice() - reducedPricePerItem)
					* itemsPerQuantity.get(discountedItem);
		}

		return 0.0;

	}
}
