package com.primepenguin.test;

import com.primepenguin.api.repo.PrimePenguinOrderStatus;
import com.primepenguin.api.service.ILogisticProviderOrderAPIService;
import com.primepenguin.api.service.impl.LogisticProviderOrderAPIServiceImpl;

public class Test {

	public static void main(String[] args) {
		
		ILogisticProviderOrderAPIService s = new LogisticProviderOrderAPIServiceImpl();
		s.getAccessToken();
		s.updateOrderFulfillmentStatus(4820921, PrimePenguinOrderStatus.FULFILLED);
	}
}
