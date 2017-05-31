package org.opendevup.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Departement implements Serializable{
	
	@Id
	private String nom_depart;
	@ManyToOne
	@JoinColumn(name="NOM_AGENCE")
	private Agence agence ;

	/*@OneToMany(mappedBy="departement")
	private Collection<User> user;*/
	

	public Departement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Departement(String nom_depart, Agence agence, Collection<User> user) {
		super();
		this.nom_depart = nom_depart;
		this.agence = agence;
		//this.user = user;
	}

	public String getNom_depart() {
		return nom_depart;
	}

	public void setNom_depart(String nom_depart) {
		this.nom_depart = nom_depart;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	/*public Collection<User> getUser() {
		return user;
	}

	public void setUser(Collection<User> user) {
		this.user = user;
	}*/
	
}
