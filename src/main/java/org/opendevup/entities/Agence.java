package org.opendevup.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Agence implements Serializable{
	@Id 
	private String nom_agence;
	@OneToMany(mappedBy="agence")
	private Collection<Departement> departement;
	
	public Agence() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Agence(String nom_agence, Collection<Departement> departement) {
		super();
		this.nom_agence = nom_agence;
		//this.departement = departement;
	}
	public String getNom_agence() {
		return nom_agence;
	}
	public void setNom_agence(String nom_agence) {
		this.nom_agence = nom_agence;
	}
	/*public Collection<Departement> getDepartement() {
		return departement;
	}
	public void setDepartement(Collection<Departement> departement) {
		this.departement = departement;
	}*/
	
	
}
