package org.opendevup.controller.client;

import java.util.List;

import org.opendevup.dao.AgenceRepository;
import org.opendevup.dao.DepartementRepository;
import org.opendevup.entities.Agence;
import org.opendevup.entities.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartementRestService {

	@Autowired
	private DepartementRepository departementRepository;
	
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="departement/listDepartement")
	public List<Departement> findAll()
	{
		return departementRepository.findAll();
	}
	
	@RequestMapping(value="/chercherDepartement")
	public Departement chercherDepartement(String nom_departement)
	{
		return departementRepository.chercherDepartement(nom_departement);
	}
	
	
	
}
