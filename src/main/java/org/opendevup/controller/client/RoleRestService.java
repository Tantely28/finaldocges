package org.opendevup.controller.client;

import java.util.List;

import org.opendevup.dao.RoleRepository;
import org.opendevup.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RoleRestService {
	
	@Autowired
	private RoleRepository roleRepository;
	@RequestMapping(value="role/listRoles")
	public List<Role> findAll()
	{
		return roleRepository.findAll();
	}
	
	/*@RequestMapping(value="role/listRoleUser", method=RequestMethod.GET)
	public List<Role> chercherRole(
			String username)
	{
		return roleRepository.chercherRole(
				"%"+username+"%"
				);
	}*/

}
