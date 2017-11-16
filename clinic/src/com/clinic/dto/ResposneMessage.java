package com.clinic.dto;

import java.io.Serializable;

public class ResposneMessage implements Serializable{

	private String message;


	public ResposneMessage() {
		super();
	}
	
	public ResposneMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
