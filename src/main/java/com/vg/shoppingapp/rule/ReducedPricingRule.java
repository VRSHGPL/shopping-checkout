package com.vg.shoppingapp.rule;

import java.util.List;
import java.util.Map;

import com.vg.shoppingapp.bean.Item;

/**
 * 
 * @author VG
 *
 */
public class ReducedPricingRule extends PricingRule {

	private final int minQuantityToBePurchansed;
	private final int reductionInQuantity;

	public ReducedPricingRule(Item discountedItem,
			int minQuantityToBePurchansed, int reductionInQuantity) {
		this.discountedItem = discountedItem;
		this.minQuantityToBePurchansed = minQuantityToBePurchansed;
		this.reductionInQuantity = reductionInQuantity;
	}

	@Override
	public Double applyDiscount(Map<Item, Integer> itemsPerQuantity,
			List<Item> items) {
		if (null != discountedItem
				&& itemsPerQuantity.containsKey(discountedItem)
				&& minQuantityToBePurchansed > 0) {
			int noOfFreeItems = (itemsPerQuantity.get(discountedItem) / minQuantityToBePurchansed)
					* reductionInQuantity;
			return noOfFreeItems * discountedItem.getPrice();
		}

		return 0.0;

	}

}
