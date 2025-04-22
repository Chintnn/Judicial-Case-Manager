package main.exception;

public class DuplicateHearingException extends Exception {
	public DuplicateHearingException(String message) {
		super(message!=null ? message : "Error: Duplicate hearing date inserted");
	}
}
