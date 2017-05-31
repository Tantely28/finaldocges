package org.opendevup.controller.client;

import java.util.List;

import org.opendevup.dao.AgenceRepository;
import org.opendevup.entities.Agence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgenceRestService{
	@Autowired
	private AgenceRepository agenceRepository;
	
	@RequestMapping(value="agence/listAgence")
	public List<Agence> findAll()
	{
		return agenceRepository.findAll();
	}
}
