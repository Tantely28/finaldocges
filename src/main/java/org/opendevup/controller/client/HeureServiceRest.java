package org.opendevup.controller.client;

import java.util.List;

import org.opendevup.dao.HeureRepository;
import org.opendevup.entities.Heure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeureServiceRest {

	@Autowired
	private HeureRepository heureRepository;
	
	@RequestMapping(value="heure/listHeure")
	public List<Heure> findAll()
	{
		return heureRepository.findAll();
	}
}
