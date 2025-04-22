package main.exception;

public class CaseAlreadyClosedException extends Exception{
	public CaseAlreadyClosedException(String message) {
		super(message!=null ? message : "Error: Case is already closed, writing case records is not allowed");
	}
}
