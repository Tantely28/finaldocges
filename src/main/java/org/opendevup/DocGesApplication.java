package org.opendevup;

import java.text.ParseException;

import org.opendevup.dao.ClientRepository;
import org.opendevup.dao.PlanningRepository;
import org.opendevup.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DocGesApplication implements CommandLineRunner{
	@Autowired
	private  ClientRepository clientRepository;
	@Autowired
	private  PlanningRepository planningRepository;
	@Autowired
	public static void main(String[] args) throws ParseException {
		SpringApplication.run(DocGesApplication.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
	
		
		/*Client c1=clientRepository.save(new Client("14521", "RAKOTO", "Tantely", "92160", "pajeuad", "antony",
				"fkldf","Entreprise", "542", "021546","2124512"));*/
		/*Client c1=clientRepository.save(new Client("", "Default", "Default", "Default", "Default", "Default",
				"Default","Default", "Default", "Default","Default"));*/
		 
		/*Client c2=clientRepository.save(new Client("rabe","rabe@gmail.com", "75014", "desmoulins", "cachan", "78542", "ges", "particulier", "1524e7", "07521401", "785241"));
		Client c3=clientRepository.save(new Client("ranoro","ranoro@gmail.com", "94400", "hilaire", "ivry sur seine", "47852L", "ueh", "particulier", "147eo", "0615241L", "785412L"));
		Client c4=clientRepository.save(new Client("ranaivo","ranaivo@gmail.com", "91230L", "yhujkl", "evry", "785412L", "tyl", "entreprise", "1524e7", "07412563L", "4582145L"));
		/*Client c2=clientRepository.save(new Client("Rabery","rabery@gmail.com"));
		Compte cp1=compteRepository.save(new CompteCourant("c1",new Date(),90000,c1,6000 ));
		Compte cp2=compteRepository.save(new CompteEpargne("c2",new Date(),6000,c2,5.5));
		
		operationRepository.save(new Versement(new Date(),9000,cp1));
		operationRepository.save(new Versement(new Date(),6000,cp1));
		operationRepository.save(new Versement(new Date(),2300,cp1));
		operationRepository.save(new Retrait(new Date(),9000,cp1));
		
		operationRepository.save(new Versement(new Date(),2300,cp2));
		operationRepository.save(new Versement(new Date(),400,cp2));
		operationRepository.save(new Versement(new Date(),2300,cp2));
		operationRepository.save(new Retrait(new Date(),3000,cp2));
		
		banqueMetier.verser("c1",111111);*/
	}
}
