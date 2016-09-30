package com.mcreceiverdemo.exceptions;

public class InvalidDataException extends RuntimeException{
	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = 4657491283614755650L;

	public InvalidDataException(String msg) {
		super(msg);
	}

	public InvalidDataException(String msg, Throwable t) {
		super(msg, t);
	}
}
