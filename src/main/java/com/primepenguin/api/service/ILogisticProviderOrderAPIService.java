package com.primepenguin.api.service;

public interface ILogisticProviderOrderAPIService {

	public void getAccessToken();
	public void createOrderFulfillment(long orderId, String[] inprogressStatuses);
	public void updateOrderFulfillmentStatus(long orderId, String[] fulfilledStatuses);
	public void createOrderRefund(long orderId);
}
