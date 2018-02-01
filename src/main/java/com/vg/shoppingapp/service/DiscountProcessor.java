package com.vg.shoppingapp.service;

import java.util.List;
import java.util.Map;

import com.vg.shoppingapp.bean.Item;
import com.vg.shoppingapp.rule.PricingRule;

/**
 * 
 * @author VG
 *
 */
public class DiscountProcessor {

	public static Double calculateDiscountOnItems(
			List<PricingRule> pricingRules,
			Map<Item, Integer> itemsPerQuantity, List<Item> items) {

		return pricingRules
				.stream()
				.filter(rule -> null != rule)
				.mapToDouble(
						rule -> rule.applyDiscount(itemsPerQuantity, items))
				.sum();

	}
}
