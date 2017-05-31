package org.opendevup.controller.client;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.opendevup.dao.ClientRepository;
import org.opendevup.entities.Client;
import org.opendevup.entities.Etat;
import org.opendevup.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//API RESTFULL CLIENT 

@RestController
public class ClientRestService {
	@Autowired
	private ClientRepository clientRepository;
	private int page;
	
	@Secured(value={"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="/chercherClients", method=RequestMethod.GET)
	public Page<Client> chercher(
			String key_word_nom, 
			String key_word_telephone,
			String key_word_code_postale,
			String key_word_ville,
			String key_word_numero_client,
			String key_word_ref_client,
			//String key_word_numero_serie,
			@RequestParam(name="page",defaultValue="0")int page,  
			@RequestParam(name="size",defaultValue="5")int size){
		return clientRepository.chercherClients(
				"%"+key_word_nom+"%",
				"%"+key_word_telephone+"%",
				"%"+key_word_code_postale+"%",
				"%"+key_word_ville+"%",
				"%"+key_word_numero_client+"%",
				"%"+key_word_ref_client+"%",
				//"%"+key_word_numero_serie+"%",
				new PageRequest(page,size));
	}
	@RequestMapping(value="/chercherClientsType", method=RequestMethod.GET)
	public Page<Client> chercherClientType(String nom,String telephone,String postale,String ville,String numero_client,String ref_client,String type_entreprise,int page,int size){
		Page<Client> c=this.chercher(nom,telephone,postale,ville,numero_client,ref_client, page, size);
		
		return null;
	}
	@Secured(value="ROLE_ADMIN")
	@RequestMapping(value="/clients/nouveauClient", method=RequestMethod.POST)
	//@RequestBody -> send data format Json, validation @Valid, BindinResult -> message d'erreur  
	public Object saveMessage(@RequestBody @Valid Client c, BindingResult bindingResult){
		//if errors exist 
		if(bindingResult.hasErrors()){
			Map<String,Object> errors=new HashMap<>();
			errors.put("erros", true);
			for(FieldError fe:bindingResult.getFieldErrors()){
				errors.put(fe.getField(), fe.getDefaultMessage());
			}
			return errors;
		}
		return clientRepository.save(c);
	}
	
	@RequestMapping(value="client/listClient")
	public List<Client> findAll()
	{
		return clientRepository.findAll();
	}
}
