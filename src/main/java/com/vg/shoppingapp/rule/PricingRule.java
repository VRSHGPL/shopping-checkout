package com.vg.shoppingapp.rule;

import java.util.List;
import java.util.Map;

import com.vg.shoppingapp.bean.Item;

/**
 * @author VG
 *
 */
public abstract class PricingRule {

	protected Item discountedItem;

	public abstract Double applyDiscount(Map<Item, Integer> itemsPerQuantity,
			List<Item> items);
}
