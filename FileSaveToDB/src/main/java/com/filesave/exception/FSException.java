package com.filesave.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FSException extends Exception {
	

	private static final long serialVersionUID = -3522577858466598354L;
	
	/** The error code. */
	@JsonProperty("error-code")
	String errorCode;
	
	/**
	 * Instantiates a new FSException exception.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 */
	public FSException(String errorCode,String errorMessage){
		super(errorMessage);
		this.errorCode=errorCode;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@JsonProperty("error-message")
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
