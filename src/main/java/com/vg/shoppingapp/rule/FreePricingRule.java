package com.vg.shoppingapp.rule;

import java.util.List;
import java.util.Map;

import com.vg.shoppingapp.bean.Item;

/**
 * 
 * @author VG
 *
 */
public class FreePricingRule extends PricingRule {

	private final Item freeItem;
	private final int freeItemQuantity;

	public FreePricingRule(Item discountedItem, Item freeItem,
			int freeItemQuantity) {
		this.discountedItem = discountedItem;
		this.freeItem = freeItem;
		this.freeItemQuantity = freeItemQuantity;
	}

	@Override
	public Double applyDiscount(Map<Item, Integer> itemsPerQuantity,
			List<Item> items) {
		if (null != discountedItem
				&& itemsPerQuantity.containsKey(discountedItem)) {
			itemsPerQuantity.put(freeItem, freeItemQuantity);
			items.add(freeItem);
		}
		return 0.0;

	}

}
