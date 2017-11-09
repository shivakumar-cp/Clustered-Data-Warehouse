package com.bloomberg.util;

import com.bloomberg.core.MyException;

/**
 * 
 * @author Shivakumar C P
 *
 */
public class LogException extends MyException {

	private static final long serialVersionUID = 3L;

	public LogException(String message) {

		super(message);
	}

	public LogException(String format, Object... args) {

		super(format, args);
	}

	public LogException(RuntimeException innerExcep, String message) {

		super(message, innerExcep);
	}

	public LogException(RuntimeException innerExcep, String format, Object... args) {

		super(innerExcep, format, args);
	}

	public LogException(Exception innerExcep, String message) {

		super(innerExcep, message);
	}

	public LogException(Exception innerExcep, String format, Object... args) {

		super(innerExcep, format, args);
	}
}
