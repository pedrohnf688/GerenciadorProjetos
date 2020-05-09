package com.pedrohnf688.api.helper;

public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = -7280632537621797703L;

	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
