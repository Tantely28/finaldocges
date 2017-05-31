package org.opendevup.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Message implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Size(min=3,max=20)
	private String title;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date date_message;
	@NotNull
	@Size(min=3,max=200)
	private String contenu;
	
	//for JPA and for me 
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//for me
	public Message(String title, Date date_message, String contenu) {
		super();
		this.title = title;
		this.date_message = date_message;
		this.contenu = contenu;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate_message() {
		return date_message;
	}
	public void setDate_message(Date date_message) {
		this.date_message = date_message;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	

}
