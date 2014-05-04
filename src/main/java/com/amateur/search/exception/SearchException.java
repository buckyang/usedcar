package com.amateur.search.exception;

public class SearchException extends Exception {

	private static final long	serialVersionUID	= 1280857685463979995L;

	public SearchException(Throwable e) {
		super(e);
	}



	public SearchException(String message, Throwable e) {
		super(message, e);
	}


}
