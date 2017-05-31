package org.opendevup.controller.client;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.opendevup.dao.AgenceRepository;
import org.opendevup.dao.RoleRepository;
import org.opendevup.dao.UserRepository;
import org.opendevup.entities.Agence;
import org.opendevup.entities.Categorie;
import org.opendevup.entities.Client;
import org.opendevup.entities.Departement;
import org.opendevup.entities.Evenement;
import org.opendevup.entities.Message;
import org.opendevup.entities.Personnel;
import org.opendevup.entities.Planning;
import org.opendevup.entities.Role;
import org.opendevup.entities.User;
import org.opendevup.metier.UserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired 
	private UserMetier userMetier;
	
	/*@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="user/nouveauUser/{categorie}/{date_tran_debut}/{date_tran_fin}",method=RequestMethod.GET)
	public User nouveauUser(User username){
			//@PathVariable("categorie")String categorie,
		
		
		return userRepository.save(username);
	}*/
	
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="user/nouveauUser", method=RequestMethod.POST) 
	public Object saveUser(@RequestBody @Valid User user, BindingResult bindingResult){
		//if errors exist 
		//e.setDate_message(new Date());
		if(bindingResult.hasErrors()){
			Map<String,Object> errors=new HashMap<>();
			errors.put("erros", true);
			for(FieldError fe:bindingResult.getFieldErrors()){
				errors.put(fe.getField(), fe.getDefaultMessage());
			}
			return errors;
		}
		
		//code ascii du nom
		StringBuilder sb = new StringBuilder();
	    for (char c : user.getNom().toCharArray())
	    	 sb.append((int)c);
	    
		String numero_badge=""+sb;
		user.setNumero_badge(numero_badge);
		String username=user.getNom().substring(1)+user.getPrenom().substring(1);
		user.setUsername(username);
		
		return userRepository.save(user);
	}
	
	
	@RequestMapping(value="profil/updateUser/{username}",method=RequestMethod.PUT )
	public String updateUser(@RequestBody User user,@PathVariable("username") String username){
		try {
			user.setUsername(username);
			userMetier.save(user);
		} catch (Exception e) {
			// TODO: handle exception 
			return "Error updating the user : "+e.toString();
		}
		return "User succefully updated ";
	}

	@Secured(value={"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="profil/editUser/{username}",method=RequestMethod.GET)
	public User getUser(@PathVariable("username")String username){
		return userRepository.findOne(username);
	}
	
	
	@RequestMapping(value="user/listUsers")
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
	
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="planning/listUser", method=RequestMethod.GET)
	public List<User> listUserDepart(
			String key_word_user_depart)
	{
		return userRepository.listUserDepart(
				"%"+key_word_user_depart+"%"
				);
	}
	
	@Secured(value={"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="planning/chercherDepartementUser", method=RequestMethod.GET)
	public User chercherDepartementUser(String username)
	{
		return userRepository.chercherDepartementUser(username);
	}
}
