package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Publicacion {
	@Id
	@GeneratedValue
	private long id;
	
	private String title;
	private String text;
	private String username;
	
	
	public Publicacion() {}
	public Publicacion(String title, String text, String username) {
		super();
		this.title = title;
		this.text = text;
		this.username=username;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
