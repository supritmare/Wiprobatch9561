package com.example.mobile.ecxeption;

public class ResourceNotFoundException extends RuntimeException{
	
public ResourceNotFoundException(String msg) {
	super(msg);
}
}
