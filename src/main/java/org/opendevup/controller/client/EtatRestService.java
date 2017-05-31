package org.opendevup.controller.client;

import java.util.List;

import org.opendevup.dao.EtatRepository;
import org.opendevup.entities.Agence;
import org.opendevup.entities.Etat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtatRestService {
	
	@Autowired
	private EtatRepository etatRepository;
	
	@RequestMapping(value="etat/listEtat")
	public List<Etat> findAll()
	{
		return etatRepository.findAll();
	}
}
