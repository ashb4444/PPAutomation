package com.primepenguin.api.repo;

public enum StatusCode {

	SUCCESS(200), BAD_REQUEST(400), NOT_FOUND(404), SERVER_ERROR(500);
	
	private int statusCode; 
	public int getStatusCode() {
		return statusCode;
	}
	private StatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
