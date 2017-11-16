package com.clinic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "message")
@NamedQueries(value = {
		@NamedQuery(name="GetMessagesTo", query="from Message b where b.receiver.id = :userid"),
		@NamedQuery(name="GetMessagesFrom", query="from Message b where b.sender.id = :userid"),
})
public class Message implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "sender_user_id")
	@ManyToOne
	private User sender;
	
	@JoinColumn(name = "receiver_user_id")
	@ManyToOne
	private User receiver;
	
	@Column
	private String content;
	
	@Column
	private Date dateandtime;

	public Message() {
		super();
	}

	public Message(Long id, User sender, User receiver, String content, Date dateandtime) {
		super();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.dateandtime = dateandtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateandtime() {
		return dateandtime;
	}

	public void setDateandtime(Date dateandtime) {
		this.dateandtime = dateandtime;
	}
	
}
