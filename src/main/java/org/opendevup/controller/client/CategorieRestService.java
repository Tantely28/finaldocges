package org.opendevup.controller.client;

import java.util.List;

import org.opendevup.dao.CategorieRepository;
import org.opendevup.entities.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategorieRestService {
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@RequestMapping(value="categorie/listCategorie")
	public List<Categorie> findAll()
	{
		return categorieRepository.findAll();
	}
}
