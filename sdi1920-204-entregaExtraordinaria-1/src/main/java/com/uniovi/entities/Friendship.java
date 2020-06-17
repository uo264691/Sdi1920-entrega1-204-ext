package com.uniovi.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Friendship
{
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private User user1;
	@ManyToOne
	private User user2;
	
	private boolean isRequest;
	
	public Friendship() {}
	
	public Friendship(User user1, User user2, boolean isRequest) {
		setUser1(user1);
		setUser2(user2);
		setIsRequest(isRequest);
	}
	
	public void setUser1(User user1) {
		this.user1=user1;
	}
	
	public void setUser2(User user2) {
		this.user2=user2;
	}
	
	public void setIsRequest(boolean isRequest) {
		this.isRequest=isRequest;
	}

	public boolean isRequest() {
		return isRequest;
	}
	
}
