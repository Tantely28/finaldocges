package org.opendevup.metier;

import org.opendevup.dao.PlanningRepository;
import org.opendevup.entities.Planning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanningMetier {
	@Autowired
	private PlanningRepository planningRepository;
	public Object findAll()
	{
		return planningRepository.findAll();
	}
	
	public Planning findById(Long id)
	{
		return planningRepository.findOne(id);
	}
	
	public Planning save(Planning planning)
	{
		return planningRepository.save(planning);
	}
	
	public void delete(Planning planning)
	{
		planningRepository.delete(planning);
	}
}