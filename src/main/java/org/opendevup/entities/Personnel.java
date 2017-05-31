package org.opendevup.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Personnel implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotEmpty
	private String nom;
	private String prenom;
	private String societe;
	private String email;
	private Long telephone;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date date_de_naissance;
	private String sexe;
	@JoinColumn
	private Long id_adresse;
	private String numéro_badge;
	public Personnel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Personnel(String nom, String prenom, String societe, String email, Long telephone, Date date_de_naissance,
			String sexe, Long id_adresse, String numéro_badge) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.societe = societe;
		this.email = email;
		this.telephone = telephone;
		this.date_de_naissance = date_de_naissance;
		this.sexe = sexe;
		this.id_adresse = id_adresse;
		this.numéro_badge = numéro_badge;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getTelephone() {
		return telephone;
	}
	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}
	public Date getDate_de_naissance() {
		return date_de_naissance;
	}
	public void setDate_de_naissance(Date date_de_naissance) {
		this.date_de_naissance = date_de_naissance;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public Long getId_adresse() {
		return id_adresse;
	}
	public void setId_adresse(Long id_adresse) {
		this.id_adresse = id_adresse;
	}
	public String getNuméro_badge() {
		return numéro_badge;
	}
	public void setNuméro_badge(String numéro_badge) {
		this.numéro_badge = numéro_badge;
	}
	
}
