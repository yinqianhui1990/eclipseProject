package com.yqh.message;

import java.io.Serializable;

public class MessageTest implements Serializable{
	private String sender;
	private String receiver;
	private String date;
	private String message;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageTest(String sender, String receiver, String date, String message) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.date = date;
		this.message = message;
	}
	public MessageTest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
