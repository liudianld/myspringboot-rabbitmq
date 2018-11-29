package com.liudian.rabbitmq.entity;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = -4948131024359752016L;

	private String id;
	private String name;
	private String messageId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

}
