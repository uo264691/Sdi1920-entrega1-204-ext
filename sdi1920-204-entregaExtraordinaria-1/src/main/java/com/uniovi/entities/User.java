package com.uniovi.entities;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set; //A collection that contains no duplicate elements

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String username;
	private String name;
	private String lastName;
	private String role;
	
	@OneToMany( mappedBy="user1" )
	private Set<Friendship> friends = new HashSet<>();
	
	@OneToMany( mappedBy="user2" )
	private Set<Friendship> isfriendof = new HashSet<>();
	
	public User() {}
	
	public User(String username, String name, String lastName) {
		super();
		this.username = username;
		this.name = name;
		this.lastName = lastName;
	}
	
//	public User(String username, String password) {
//		super();
//		this.username = username;
//		
//		this.password = password;
//	}

	private String password;
	@Transient // propiedad que no se almacena e la tabla.
	private String passwordConfirm;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Friendship> getFriends() {
		return friends;
	}

	public void setFriends(Set<Friendship> friends) {
		this.friends = friends;
	}
	
	public void addFriends(Set<User> requests) {
		if(friends==null)
			friends = new HashSet<>();
		for(User user : requests)
			friends.add(new Friendship(this, user, false));
	}

	public void addFriendRequests(Set<User> requests) {
		if(friends==null)
			friends = new HashSet<>();
		for(User user : requests)
			friends.add(new Friendship(this, user, true));
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}
}