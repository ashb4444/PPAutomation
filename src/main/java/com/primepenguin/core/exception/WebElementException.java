package com.primepenguin.core.exception;

public class WebElementException extends Exception{

	private static final long serialVersionUID = 1L;

	public WebElementException(String message)
	{
		super(message);
	}
	
	public WebElementException(String message, Throwable clause)
	{
		super(message, clause);
	}
}
