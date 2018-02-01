package com.vg.shoppingapp.rule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.vg.shoppingapp.bean.Item;
import com.vg.shoppingapp.service.CheckOutImpl;

/**
 * 
 * @author VG
 *
 */
public class ShoppingCartTest {

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
	public void testCase1() {
		List<PricingRule> pricingRules = new ArrayList<>();
		pricingRules.add(freePricingRule);
		pricingRules.add(bulkPricingRule);
		pricingRules.add(reducedPricingRule);

		CheckOutImpl checkOutImpl = new CheckOutImpl(pricingRules);

		checkOutImpl.scan(atv);
		checkOutImpl.scan(atv);
		checkOutImpl.scan(atv);
		checkOutImpl.scan(vga);
		checkOutImpl.total();

	}

	@Test
	public void testCase2() {
		List<PricingRule> pricingRules = new ArrayList<>();
		pricingRules.add(freePricingRule);
		pricingRules.add(bulkPricingRule);
		pricingRules.add(reducedPricingRule);

		CheckOutImpl checkOutImpl = new CheckOutImpl(pricingRules);
		// atv, ipd, ipd, atv, ipd, ipd, ipd
		checkOutImpl.scan(atv);
		checkOutImpl.scan(ipd);
		checkOutImpl.scan(ipd);
		checkOutImpl.scan(atv);
		checkOutImpl.scan(ipd);
		checkOutImpl.scan(ipd);
		checkOutImpl.scan(ipd);
		checkOutImpl.total();

	}

	@Test
	public void testCase3() {
		List<PricingRule> pricingRules = new ArrayList<>();
		pricingRules.add(freePricingRule);
		pricingRules.add(bulkPricingRule);
		pricingRules.add(reducedPricingRule);

		CheckOutImpl checkOutImpl = new CheckOutImpl(pricingRules);
		// mbp, vga, ipd
		checkOutImpl.scan(mbp);
		checkOutImpl.scan(ipd);
		
		checkOutImpl.total();

	}
}
