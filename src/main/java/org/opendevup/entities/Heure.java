package org.opendevup.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Heure implements Serializable{

	@Id
	private String code_heure;
	
	public Heure() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Heure(String code_heure) {
		super();
		this.code_heure = code_heure;
	}

	public String getCode_heure() {
		return code_heure;
	}

	public void setCode_heure(String code_heure) {
		this.code_heure = code_heure;
	}
	
	@OneToMany(mappedBy="heure",fetch=FetchType.LAZY)
	private Collection<Planning> planning;
	

}
