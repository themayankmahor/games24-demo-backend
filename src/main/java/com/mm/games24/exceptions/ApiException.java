package com.mm.games24.exceptions;

public class ApiException extends RuntimeException{
	
	public ApiException()
	{
		super();
	}

	public ApiException(String message)
	{
		super(message);
	}
}
