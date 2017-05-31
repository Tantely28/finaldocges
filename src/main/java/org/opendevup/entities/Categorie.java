package org.opendevup.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie implements Serializable{
	@Id
	private String nom_categorie;
	private String description;
	@OneToMany(mappedBy="categorie",fetch=FetchType.LAZY)
	private Collection<Evenement> evenement;
	
	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categorie(String nom_categorie, String description) {
		super();
		this.nom_categorie = nom_categorie;
		this.description = description;
	}
	public String getNom_categorie() {
		return nom_categorie;
	}
	public void setNom_categorie(String nom_categorie) {
		this.nom_categorie = nom_categorie;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
