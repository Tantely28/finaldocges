package org.opendevup.metier;

import org.opendevup.dao.UserRepository;
import org.opendevup.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMetier {
	
	@Autowired
	private UserRepository userRepository;
	public Object findAll()
	{
		return userRepository.findAll();
	}
	
	public User findById(String username)
	{
		return userRepository.findOne(username);
	}
	
	public User save(User u)
	{
		return userRepository.save(u);
	}
	public void delete(User user)
	{
		userRepository.delete(user);
	}
}


	