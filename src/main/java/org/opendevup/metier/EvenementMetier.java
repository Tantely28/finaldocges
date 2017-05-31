package org.opendevup.metier;

import org.opendevup.dao.EvenementRepository;
import org.opendevup.entities.Evenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EvenementMetier {
	@Autowired
	private EvenementRepository evenementRepository;
	public Object findAll()
	{
		return evenementRepository.findAll();
	}
	
	public Evenement findById(Long id)
	{
		return evenementRepository.findOne(id);
	}
	
	public Evenement save(Evenement evenement)
	{
		return evenementRepository.save(evenement);
	}
	
	public void delete(Evenement evenement)
	{
		evenementRepository.delete(evenement);
	}
}

