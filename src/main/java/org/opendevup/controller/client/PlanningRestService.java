package org.opendevup.controller.client;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.opendevup.dao.ClientRepository;
import org.opendevup.dao.EtatRepository;
import org.opendevup.dao.HeureRepository;
import org.opendevup.dao.PlanningRepository;
import org.opendevup.dao.UserRepository;
import org.opendevup.entities.Client;
import org.opendevup.entities.Etat;
import org.opendevup.entities.Evenement;
import org.opendevup.entities.Heure;
import org.opendevup.entities.Message;
import org.opendevup.entities.Planning;
import org.opendevup.entities.User;
import org.opendevup.metier.PlanningMetier;
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
public class PlanningRestService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HeureRepository heureRepository;
	@Autowired
	private EtatRepository etatRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private PlanningRepository planningRepository;
	@Autowired
	private PlanningMetier planningMetier;
	
	
	@RequestMapping(value="planning/listPlanning")
	public List<Planning> findAll()
	{
		return planningRepository.findAll();
	}
	
	
	//@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="planning/chercherUser", method=RequestMethod.GET)
	public List<Planning> chercher(
			String key_word_numero_intervention,
			String key_word_nom_client,
			String key_word_code_postale,
			String key_word_numero_client,
			String key_word_nom_depart,
			String key_word_nom_etat,
			String key_date_Inter
			)
	{
		return planningRepository.chercherUser(
				"%"+key_word_numero_intervention+"%",
				"%"+key_word_nom_client+"%",
				"%"+key_word_code_postale+"%",
				"%"+key_word_numero_client+"%",
				"%"+key_word_nom_depart+"%",
				"%"+key_word_nom_etat+"%",
				"%"+key_date_Inter+"%"
				);
	}
	
	@Secured(value={"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="planning/chercherUserSimple", method=RequestMethod.GET)
	public List<Planning> chercherSimple(
			String key_word_numero_intervention,
			String key_word_nom_client,
			String key_word_code_postale,
			String key_word_numero_client,
			String key_word_nom_depart,
			String key_word_nom_etat,
			String key_date_Inter
			)
	{
		return planningRepository.chercherUser(
				"%"+key_word_numero_intervention+"%",
				"%"+key_word_nom_client+"%",
				"%"+key_word_code_postale+"%",
				"%"+key_word_numero_client+"%",
				"%"+key_word_nom_depart+"%",
				"%"+key_word_nom_etat+"%",
				"%"+key_date_Inter+"%"
				);
	}
	
	@Secured(value={"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="planning/planningFermer/{username}/{heure}/{date}",method=RequestMethod.GET)
	public Planning planningFermer(Planning p, @PathVariable("username")String username,
			@PathVariable("heure")String heure,
			@PathVariable("date")String date){
		
		System.out.println("username="+username);
		System.out.println("heure="+heure);
		System.out.println("date="+date);
		Etat e = new Etat(); 
		Heure h = new Heure();
		User u = new User();
		Client c = new Client();
		
		p.setNom_Intervention("Inter"+username+""+date+""+heure);
		p.setNumero_Intervention("N"+date+""+username+""+heure);
		
		u=userRepository.findOne(username);
		e=etatRepository.findOne("INDISPONIBLE");
		h=heureRepository.findOne(heure);
		c=clientRepository.findOne("14521");
		
		p.setEtat(e);
		p.setClient(c);
		p.setHeure(h);
		p.setUser(u);
		p.setDate_Intervention(date);
		
		return planningRepository.save(p);
	}
	

	@Secured(value={"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="planning/planningFermerTousCreneau/{username}/{date}",method=RequestMethod.GET)
	public Planning planningFermerTousCreneau(Planning p, @PathVariable("username")String username,
			@PathVariable("date")String date){
		
		
		Etat e = new Etat(); 
		Heure h = new Heure();
		User u = new User();
		Client c = new Client();
		
		p.setNom_Intervention("Inter"+username+""+date+""+"TOUS");
		p.setNumero_Intervention("N"+date+""+username+""+"TOUS");
		
		u=userRepository.findOne(username);
		e=etatRepository.findOne("INDISPONIBLE");
		h=heureRepository.findOne("TOUS");
		c=clientRepository.findOne("14521");
		
		p.setEtat(e);
		p.setHeure(h);
		p.setUser(u);
		p.setClient(c);
		p.setDate_Intervention(date);
		
		return planningRepository.save(p);
	}
	
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="planning/nouveauPlanning/{user}/{date}/{heure}/{client}/{description}",method=RequestMethod.GET)
	public Planning nouveauPlanning(Planning p, 
			@PathVariable("user")String user,
			@PathVariable("date")String date,
			@PathVariable("heure")String heure,
			@PathVariable("client")String client,
			@PathVariable("description")String description){
		
		
		Etat e = new Etat(); 
		Heure h = new Heure();
		User u = new User();
		Client c = new Client();
		
		p.setNom_Intervention("Inter"+user+""+date+""+heure);
		p.setNumero_Intervention("N"+date+""+user+""+heure);
		p.setDescription(description);
		
		u=userRepository.findOne(user);
		e=etatRepository.findOne("VIDE");
		h=heureRepository.findOne(heure);
		c=clientRepository.findOne(client);
		
		p.setEtat(e);
		p.setHeure(h);
		p.setUser(u);
		p.setClient(c);
		p.setDate_Intervention(date);
		
		return planningRepository.save(p);
	}


	//uSE Metier
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="planning/deletePlanning/{id_Intervention}")
	public String delete(@PathVariable long id_Intervention){
		try {
			Planning planning = planningMetier.findById(id_Intervention);
			//System.out.println("id="+planning.getId_Intervention());
			planningMetier.delete(planning);
		} catch (Exception e) {
			// TODO: handle exception 
			return "Error deleting the planning : "+e.toString();
		}
		return "Planning succefully delete ";
	}
	
	@Secured(value={"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="planning/updatePlanning/{id_Intervention}",method=RequestMethod.PUT )
	public String update(@RequestBody Planning planning,@PathVariable long id_Intervention){
		try {
			planning.setId_Intervention(id_Intervention);
			planningMetier.save(planning);
		} catch (Exception e) {
			// TODO: handle exception 
			return "Error updating the planning : "+e.toString();
		}
		return "Planning succefully updated ";
	}
	
	@Secured(value={"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="planning/editPlanning/{id}",method=RequestMethod.GET)
	public Planning getPlanning(@PathVariable ("id")long id){
		return planningRepository.findOne(id);
	}
	
	
	@Secured(value={"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="planning/updateEtatPlanning/{id_Intervention}",method=RequestMethod.PUT )
	public String updateEtat(@RequestBody Planning planning,@PathVariable long id_Intervention){
		try {
			planning.setId_Intervention(id_Intervention);
			planningMetier.save(planning);
		} catch (Exception e) {
			// TODO: handle exception 
			return "Error updating the state planning : "+e.toString();
		}
		return "State Planning succefully updated ";
	}
	
	
}
