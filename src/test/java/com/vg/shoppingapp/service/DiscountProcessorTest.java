package com.vg.shoppingapp.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.vg.shoppingapp.bean.Item;
import com.vg.shoppingapp.rule.BulkPricingRule;
import com.vg.shoppingapp.rule.FreePricingRule;
import com.vg.shoppingapp.rule.PricingRule;
import com.vg.shoppingapp.rule.ReducedPricingRule;

/**
 * 
 * @author VG
 *
 */
@Ignore
public class DiscountProcessorTest {

	private Map<Item, Integer> totalQuantityOfItemsPurchased = new HashMap<>();;
	private List<Item> cart = new ArrayList<>();;

	Item ipd;
	Item mbp;
	Item atv;
	Item vga;

	FreePricingRule freePricingRule;
	BulkPricingRule bulkPricingRule;
	ReducedPricingRule reducedPricingRule;

	@Before
	public void setUp() {
		ipd = new Item("ipd", "Super iPad", 549.99);
		mbp = new Item("mbp", "MacBook Pro", 1399.99);
		atv = new Item("atv", "Apple TV", 109.50);
		vga = new Item("vga", "VGA adapter", 30.00);

		freePricingRule = new FreePricingRule(mbp, vga, 1);
		bulkPricingRule = new BulkPricingRule(ipd, 4, 499.99);
		reducedPricingRule = new ReducedPricingRule(atv, 3, 1);

	}

	@Test
	public void testBase() throws Exception {
		List<PricingRule> pricingRules = new ArrayList<>();

		Double dis = DiscountProcessor.calculateDiscountOnItems(pricingRules,
				totalQuantityOfItemsPurchased, cart);

		assertEquals(dis, new Double(0.0));

	}

	@Test
	public void testBulkDiscount() throws Exception {
		List<PricingRule> pricingRules = new ArrayList<>();
		pricingRules.add(bulkPricingRule);

		cart.add(ipd);
		cart.add(ipd);
		cart.add(ipd);
		cart.add(ipd);
		cart.add(ipd);

		totalQuantityOfItemsPurchased.put(ipd, 5);

		Double dis = DiscountProcessor.calculateDiscountOnItems(pricingRules,
				totalQuantityOfItemsPurchased, cart);

		assertEquals(dis, new Double(250.0));

	}

	@Test
	public void testBulkDiscountNoDis() {
		List<PricingRule> pricingRules = new ArrayList<>();
		pricingRules.add(bulkPricingRule);

		cart.add(ipd);
		cart.add(ipd);
		cart.add(ipd);

		totalQuantityOfItemsPurchased.put(ipd, 3);

		Double dis = DiscountProcessor.calculateDiscountOnItems(pricingRules,
				totalQuantityOfItemsPurchased, cart);
		System.out.println(dis);

		assertEquals(dis, new Double(0.0));

	}

	@Test
	public void testReducedDiscount() {
		List<PricingRule> pricingRules = new ArrayList<>();
		pricingRules.add(reducedPricingRule);

		cart.add(atv);
		cart.add(atv);
		cart.add(atv);

		totalQuantityOfItemsPurchased.put(atv, 3);

		Double dis = DiscountProcessor.calculateDiscountOnItems(pricingRules,
				totalQuantityOfItemsPurchased, cart);

		assertEquals(dis, new Double(109.5));

	}

	@Test
	public void testDiscountNoDis() {
		List<PricingRule> pricingRules = new ArrayList<>();
		pricingRules.add(reducedPricingRule);

		cart.add(atv);

		totalQuantityOfItemsPurchased.put(atv, 1);

		Double dis = DiscountProcessor.calculateDiscountOnItems(pricingRules,
				totalQuantityOfItemsPurchased, cart);

		assertEquals(dis, new Double(0.0));

	}

	@Test
	public void testCombinedDiscount() throws Exception {
		List<PricingRule> pricingRules = new ArrayList<>();
		pricingRules.add(reducedPricingRule);
		pricingRules.add(bulkPricingRule);

		cart.add(ipd);
		cart.add(ipd);
		cart.add(ipd);
		cart.add(ipd);
		cart.add(ipd);
		totalQuantityOfItemsPurchased.put(ipd, 5);

		cart.add(atv);
		cart.add(atv);
		cart.add(atv);
		cart.add(atv);
		cart.add(atv);

		totalQuantityOfItemsPurchased.put(atv, 5);

		Double dis = DiscountProcessor.calculateDiscountOnItems(pricingRules,
				totalQuantityOfItemsPurchased, cart);

		assertEquals(dis, new Double(359.5));

	}

}
