package org.opendevup.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
@Entity
public class Planning implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id_Intervention;
	private String numero_Intervention;
	private String date_Intervention;
	private String nom_Intervention;
	@ManyToOne
	@JoinColumn(name="CODE_CLIENT")
	private Client client;
	@ManyToOne
	@JoinColumn(name="CODE_USER")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="CODE_ETAT")
	private Etat etat;
	
	@ManyToOne
	@JoinColumn(name="CODE_HEURE")
	private Heure heure;
	
	private String Description;
	private String Rapport;


	public Planning() {
		super();
		// TODO Auto-generated constructor stub
	}

	


	public Planning(String numero_Intervention, String date_Intervention, String nom_Intervention, Client client,
			User user, Etat etat, Heure heure, String Description, String Rapport) {
		super();
		this.numero_Intervention = numero_Intervention;
		this.date_Intervention = date_Intervention;
		this.nom_Intervention = nom_Intervention;
		this.client = client;
		this.user = user;
		this.etat = etat;
		this.heure = heure;
		this.Description = Description;
		this.Description = Rapport;
	}


	public String getRapport() {
		return Rapport;
	}




	public void setRapport(String rapport) {
		Rapport = rapport;
	}




	public Long getId_Intervention() {
		return id_Intervention;
	}




	public void setId_Intervention(Long id_Intervention) {
		this.id_Intervention = id_Intervention;
	}




	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}


	public String getNumero_Intervention() {
		return numero_Intervention;
	}


	public void setNumero_Intervention(String numero_Intervention) {
		this.numero_Intervention = numero_Intervention;
	}


	public String getDate_Intervention() {
		return date_Intervention;
	}


	public void setDate_Intervention(String date_Intervention) {
		this.date_Intervention = date_Intervention;
	}


	public String getNom_Intervention() {
		return nom_Intervention;
	}


	public void setNom_Intervention(String nom_Intervention) {
		this.nom_Intervention = nom_Intervention;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Heure getHeure() {
		return heure;
	}


	public void setHeure(Heure heure) {
		this.heure = heure;
	}


	public Etat getEtat() {
		return etat;
	}


	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	


}
