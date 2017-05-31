package org.opendevup.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Etat implements Serializable{
	
	@Id
	private String code_etat;
	
	@OneToMany(mappedBy="etat",fetch=FetchType.LAZY)
	private Collection<Planning> planning;

	public Etat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Etat(String code_etat) {
		super();
		this.code_etat = code_etat;
	}
	public Etat(Collection<Planning> planning) {
		super();
	}
	public String getCode_etat() {
		return code_etat;
	}
	public void setCode_etat(String code_etat) {
		this.code_etat = code_etat;
	}
}
