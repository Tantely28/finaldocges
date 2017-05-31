package org.opendevup.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User implements Serializable {
	
	@Id
	private String Username;
	@NotNull
	@Size(min=3,max=20)
	private String password;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date date_de_naissance;
	private String email;
	
	@NotNull
	@Size(min=3,max=20)
	private String nom;
	
	@NotNull
	@Size(min=3,max=20)
	private String prenom;
	private String numero_badge;
	private String sexe;
	private String telephone;
	@ManyToMany
	@JoinTable(name="USERS_ROLES")
	private Collection<Role> roles;
	
	
	

	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	private Collection<Planning> planning;
	
	@ManyToOne
	@JoinColumn(name="NOM_DEPARTEMENT")
	private Departement departement ;

	
	
	/*@ManyToOne
	@JoinColumn(name="NOM_EVENEMENT")
	private Evenement evenement ;*/

	/*public Collection<Planning> getPlanning() {
		return planning;
	}

	public void setPlanning(Collection<Planning> planning) {
		this.planning = planning;
	}*/

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, Date date_de_naissance, String email, String nom,
			String prenom, String numero_badge, String sexe, String telephone, Collection<Role> roles,
			Departement departement) {
		super();
		Username = username;
		this.password = password;
		this.date_de_naissance = date_de_naissance;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.numero_badge = numero_badge;
		this.sexe = sexe;
		this.telephone = telephone;
		this.roles = roles;
		//this.departement = departement;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDate_de_naissance() {
		return date_de_naissance;
	}

	public void setDate_de_naissance(Date date_de_naissance) {
		this.date_de_naissance = date_de_naissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getNumero_badge() {
		return numero_badge;
	}

	public void setNumero_badge(String numero_badge) {
		this.numero_badge = numero_badge;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	
}
