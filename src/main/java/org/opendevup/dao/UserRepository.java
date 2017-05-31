package org.opendevup.dao;

import java.util.List;

import org.opendevup.entities.Role;
import org.opendevup.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String>{

	@Query("select u from User u where u.departement.nom_depart like :x")
	public List<User> listUserDepart(@Param("x") String key_word_user_depart);

	@Query("select u from User u where u.Username like :x")
	public User chercherDepartementUser(@Param("x") String username);
	
	
}
