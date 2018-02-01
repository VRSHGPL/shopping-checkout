package com.vg.shoppingapp.rule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.vg.shoppingapp.bean.Item;
import com.vg.shoppingapp.service.CheckOutImpl;

/**
 * 
 * @author VG
 *
 */
@Ignore
public class TestRules {

	Item ipad;
	Item mac;
	Item tv;
	Item adap;

	FreePricingRule freePricingRule;
	BulkPricingRule bulkPricingRule;
	ReducedPricingRule reducedPricingRule;

	@Before
	public void setUp() {
		ipad = new Item("ipad", "iPad", 10.0);
		mac = new Item("mac", "MacBook", 20.0);
		tv = new Item("tv", "TV", 30.0);
		adap = new Item("adap", "adapter", 40.0);

		freePricingRule = new FreePricingRule(mac, adap, 1);
		bulkPricingRule = new BulkPricingRule(ipad, 4, 5.0);
		reducedPricingRule = new ReducedPricingRule(tv, 3, 1);

	}

	@Test
	public void testFreeRule() {
		List<PricingRule> pricingRules = new ArrayList<>();
		pricingRules.add(freePricingRule);

		CheckOutImpl checkOutImpl = new CheckOutImpl(pricingRules);

		checkOutImpl.scan(mac);
		checkOutImpl.total();

	}

	@Test
	public void testTwoRules() {
		List<PricingRule> pricingRules = new ArrayList<>();
		pricingRules.add(freePricingRule);
		pricingRules.add(bulkPricingRule);

		CheckOutImpl checkOutImpl = new CheckOutImpl(pricingRules);

		checkOutImpl.scan(mac);
		checkOutImpl.scan(ipad);
		checkOutImpl.total();

	}

	@Test
	public void testBulkRules() {
		List<PricingRule> pricingRules = new ArrayList<>();
		pricingRules.add(freePricingRule);
		pricingRules.add(bulkPricingRule);

		CheckOutImpl checkOutImpl = new CheckOutImpl(pricingRules);

		checkOutImpl.scan(mac);
		checkOutImpl.scan(ipad);
		checkOutImpl.scan(ipad);
		checkOutImpl.scan(ipad);
		checkOutImpl.scan(ipad);
		checkOutImpl.scan(ipad);
		checkOutImpl.total();

	}

	@Test
	public void testReducedRules() {
		List<PricingRule> pricingRules = new ArrayList<>();
		// pricingRules.add(freePricingRule);
		pricingRules.add(reducedPricingRule);

		CheckOutImpl checkOutImpl = new CheckOutImpl(pricingRules);

		checkOutImpl.scan(tv);
		checkOutImpl.scan(tv);
		checkOutImpl.scan(tv);
		checkOutImpl.scan(tv);
		checkOutImpl.scan(tv);
		checkOutImpl.scan(tv);
		checkOutImpl.total();

	}

}
