package org.opendevup.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Client implements Serializable
{
	@Id
	private String numero_client;
	@NotNull
	@Size(min=3,max=20)
	private String nom;
	private String prenom;
	private String code_postale;
	private String rue;
	@NotNull
	@Size(min=3,max=20)
	private String ville;
	
	private String partenaire;
	private String type_client;
	private String numero_serie;
	private String telephone;
	private String reference;
	
	@OneToMany(mappedBy="client",fetch=FetchType.LAZY)
	private Collection<Planning> planning;
	
	/*public Collection<Planning> getPlanning() {
		return planning;
	}

	public void setPlanning(Collection<Planning> planning) {
		this.planning = planning;
	}*/

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String numero_client, String nom, String prenom, String code_postale, String rue, String ville,
			String partenaire, String type_client, String numero_serie, String telephone, String reference) {
		super();
		this.numero_client = numero_client;
		this.nom = nom;
		this.prenom = prenom;
		this.code_postale = code_postale;
		this.rue = rue;
		this.ville = ville;
		this.partenaire = partenaire;
		this.type_client = type_client;
		this.numero_serie = numero_serie;
		this.telephone = telephone;
		this.reference = reference;
		//this.planning = planning;
	}

	public String getNumero_client() {
		return numero_client;
	}

	public void setNumero_client(String numero_client) {
		this.numero_client = numero_client;
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

	public String getCode_postale() {
		return code_postale;
	}

	public void setCode_postale(String code_postale) {
		this.code_postale = code_postale;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPartenaire() {
		return partenaire;
	}

	public void setPartenaire(String partenaire) {
		this.partenaire = partenaire;
	}

	public String getType_client() {
		return type_client;
	}

	public void setType_client(String type_client) {
		this.type_client = type_client;
	}

	public String getNumero_serie() {
		return numero_serie;
	}

	public void setNumero_serie(String numero_serie) {
		this.numero_serie = numero_serie;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	/*public Collection<Planning> getPlanning() {
		return planning;
	}

	public void setPlanning(Collection<Planning> planning) {
		this.planning = planning;
	}
*/
	
	
	
}
