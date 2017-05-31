package org.opendevup.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Evenement implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id_evenement;
	
	private String date_debut;
	private String date_fin;
	

	@ManyToOne
	@JoinColumn(name="Type_evenement")
	private Categorie categorie;
	  
	  //private Collection<User> user;

	
	public Evenement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Evenement(String date_debut, String date_fin,Categorie categorie)
			{
		super();
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.categorie = categorie;
		//this.user = user;
	}


	public Long getId_evenement() {
		return id_evenement;
	}
	public void setId_evenement(Long id_evenement) {
		this.id_evenement = id_evenement;
	}
	
	public String getDate_debut() {
		return date_debut;
	}


	public void setDate_debut(String date_debut) {
		this.date_debut = date_debut;
	}


	public String getDate_fin() {
		return date_fin;
	}


	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/*public Collection<User> getUser() {
		return user;
	}


	public void setUser(Collection<User> user) {
		this.user = user;
	}*/
	
	
}
