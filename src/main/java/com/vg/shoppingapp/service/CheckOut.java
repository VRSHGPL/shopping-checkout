package com.vg.shoppingapp.service;

import com.vg.shoppingapp.bean.Item;

/**
 * @author VG
 *
 */
public interface CheckOut {

	void scan(Item item);

	Double total();

}
