package com.clinic.dto;

import java.io.Serializable;
import java.util.List;

public class AdminMessage implements Serializable{

	private String message;
	
	private List<Long> userIds;
	
	public AdminMessage(){
		
	}
	
	public AdminMessage(String message, List<Long> userIds){
		this.message = message;
		this.userIds = userIds;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	
}
