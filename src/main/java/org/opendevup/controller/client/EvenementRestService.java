package org.opendevup.controller.client;


import java.util.List;

import org.opendevup.dao.CategorieRepository;
import org.opendevup.dao.EvenementRepository;
import org.opendevup.entities.Categorie;
import org.opendevup.entities.Client;
import org.opendevup.entities.Etat;
import org.opendevup.entities.Evenement;
import org.opendevup.entities.Heure;
import org.opendevup.entities.Planning;
import org.opendevup.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvenementRestService {
	
	@Autowired
	private EvenementRepository evenementRepository;
	@Autowired
	private CategorieRepository categorieRepository;

	@RequestMapping(value="evenement/listEvenement")
	public List<Evenement> findAll()
	{
		return evenementRepository.findAll();
	}
	
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="evenement/nouveauEvenement/{categorie}/{date_tran_debut}/{date_tran_fin}",method=RequestMethod.GET)
	public Evenement nouveauEvenement(Evenement e, 
			@PathVariable("categorie")String categorie,
			@PathVariable("date_tran_debut")String date_tran_debut,
			@PathVariable("date_tran_fin")String date_tran_fin){
		
		
		Categorie c = new Categorie();
		c=categorieRepository.findOne(categorie);
		e.setCategorie(c);
		
		e.setDate_debut(date_tran_debut);
		e.setDate_fin(date_tran_fin);
		
		return evenementRepository.save(e);
	}
	
	
	/*@Secured(value={"ROLE_ADMIN"})
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
	
	@Secured(value={"ROLE_ADMIN"})
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
	
	@Secured(value={"ROLE_ADMIN"})
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
	}*/
	
}
