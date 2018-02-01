package com.vg.shoppingapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.vg.shoppingapp.bean.Item;
import com.vg.shoppingapp.rule.PricingRule;

/**
 * 
 * @author VG
 *
 */
public class CheckOutImpl implements CheckOut {

	protected final List<PricingRule> pricingRules;
	private final Map<Item, Integer> totalQuantityOfItemsPurchased;
	private final List<Item> cart;

	public CheckOutImpl(List<PricingRule> pricingRules) {
		this.pricingRules = pricingRules;
		this.totalQuantityOfItemsPurchased = new HashMap<>();
		this.cart = new ArrayList<>();
	}

	@Override
	public void scan(Item item) {
		cart.add(item);

		Integer count = totalQuantityOfItemsPurchased.get(item);
		if (count == null) {
			totalQuantityOfItemsPurchased.put(item, 1);
		} else {
			totalQuantityOfItemsPurchased.put(item, count + 1);
		}
	}

	@Override
	public Double total() {
		Double total = totalQuantityOfItemsPurchased
				.keySet()
				.stream()
				.filter(item -> null != item)
				.collect(
						Collectors.summingDouble(item -> item.getPrice()
								* totalQuantityOfItemsPurchased.get(item)));

		if (pricingRules != null && pricingRules.size() > 0) {
			Double amountDiscounted = DiscountProcessor
					.calculateDiscountOnItems(pricingRules,
							totalQuantityOfItemsPurchased, cart);

			total = total - amountDiscounted;

		}

		this.logger(total);
		return total;
	}

	private void logger(Double newTotal) {
		String allItems = cart.stream().filter(entry -> null != entry)
				.map(entry -> entry.getSku()).collect(Collectors.joining(" ,"));

		System.out.println("SKUs Scanned:  " + allItems + " Total expected: $"
				+ newTotal);

	}

}
