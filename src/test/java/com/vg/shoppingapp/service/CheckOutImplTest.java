package com.vg.shoppingapp.service;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import com.vg.shoppingapp.bean.Item;

/**
 * 
 * @author VG
 *
 */
@Ignore
public class CheckOutImplTest {

	@Test
	public void testNoItem() {

		CheckOutImpl checkOutImpl = new CheckOutImpl(null);
		checkOutImpl.scan(null);

		assertEquals(checkOutImpl.total(), new Double(0.0));
	}

	@Test
	public void testTotal() {

		CheckOutImpl checkOutImpl = new CheckOutImpl(null);

		checkOutImpl.scan(new Item("1", "one", 1.0));
		checkOutImpl.scan(new Item("2", "two", 2.0));
		checkOutImpl.scan(new Item("3", "three", 3.0));
		checkOutImpl.scan(new Item("4", "four", 4.5));

		assertEquals(checkOutImpl.total(), new Double(10.5));

	}

}
