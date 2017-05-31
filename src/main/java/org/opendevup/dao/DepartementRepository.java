package org.opendevup.dao;

import java.util.List;

import org.opendevup.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartementRepository extends JpaRepository<Departement, String>{

	@Query("select d from Departement d where d.nom_depart like :x")
	public Departement chercherDepartement(@Param("x")String nom_departement);
	/*@Query("select d from Departement d where d.nom_agence like :x")
	public List<Departement> ListDepartement(@Param("x")String nom_agence);*/
}
