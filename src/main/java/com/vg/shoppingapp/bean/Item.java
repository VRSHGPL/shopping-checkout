package com.vg.shoppingapp.bean;

import java.util.Objects;

public class Item {

	private String sku;
	private String name;
	private Double price;

	public Item(String sku, String name, Double price) {
		super();
		this.sku = sku;
		this.name = name;
		this.price = price == null ? 0.0 : price;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Item)) {
			return false;
		}
		Item item = (Item) o;
		return sku == item.sku && Objects.equals(name, item.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sku, name);
	}

}
