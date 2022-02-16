package com.primepenguin.service;

public interface IPPWooCommerceService extends IPPSalesChannelsPageService {

	public void connectToWooCommerceSalesChannel();
	public void checkIsOrderFetchedInPrimePenguin(String orderNumber);
	public void deactivateSalesChannelIntegration();
}
