package com.primepenguin.service;

import com.primepenguin.model.ProductType;
import com.primepenguin.model.WooCommerceOrder;

public interface IWooCommerceService {

	public void loginToWooCommerce();
	public void createWooCoomerceOrder(ProductType productType);
	public WooCommerceOrder getWooCommerceOrder();
}
