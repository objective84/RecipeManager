package com.recipemanager.model.buisiness.exception;

public class ServiceLoadFailException extends Exception{
	
	public ServiceLoadFailException(final String inMessage, final Throwable inNestedException)
    {
        super(inMessage, inNestedException);
    }
}
