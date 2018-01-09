package com.demo.utils;

public class UserValidation extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserValidation(String error){
		super(error);
	}
}
