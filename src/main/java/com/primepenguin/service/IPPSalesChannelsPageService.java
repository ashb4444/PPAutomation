package com.primepenguin.service;

import com.primepenguin.model.PrimePenguinOrder;

public interface IPPSalesChannelsPageService {

	public void checkIsOrderFetchedInPrimePenguin(String dashboardLocatorType, String dashboardLocatorValue, String orderNumber);
	public void saveDefaultProductMapping();
	public PrimePenguinOrder getPrimePenguinOrder();
	public void checkIsOrderStatusUpdated(Long ppOrderId, String status);
}
