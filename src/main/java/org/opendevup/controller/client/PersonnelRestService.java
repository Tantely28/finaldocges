package org.opendevup.controller.client;


import org.opendevup.dao.PersonnelRepository;
import org.opendevup.entities.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonnelRestService {
	
	@Autowired
	private PersonnelRepository personnelRepository;
	
	//enregistrer
	@Secured(value="ROLE_ADMIN")
	@RequestMapping(value="/savePersonnel", method=RequestMethod.POST)
	public Personnel savePersonnel(@RequestBody Personnel p)
	{
		return personnelRepository.save(p);
	}
	
	@Secured(value="ROLE_ADMIN")
	@RequestMapping(value="/updatePersonnel/{id}", method=RequestMethod.PUT)
	public Personnel updatePersonnel(@RequestBody Personnel p,@PathVariable Long id)
	{
		//return personnelRepository.save(p);
		p.setId(id);
		return personnelRepository.saveAndFlush(p);
	}
	
	@Secured(value={"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="/listPersonnel")
	public Page<Personnel> listPersonnel(int page,int size)
	{
		return personnelRepository.findAll(new PageRequest(page,size));
	}
	
	@RequestMapping(value="/personnel/{id}",method=RequestMethod.GET)
	public Personnel getPersonnel(@PathVariable ("id")Long id){
		return personnelRepository.findOne(id);
	}
	
	//supprimer
	@RequestMapping(value="/personnel/{id}",method=RequestMethod.DELETE)
	public void deletePersonnel(@PathVariable ("id")Long id){
		 personnelRepository.delete(id);
	}
	
}
