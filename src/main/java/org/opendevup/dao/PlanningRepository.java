package org.opendevup.dao;

import java.util.Date;
import java.util.List;

import org.opendevup.entities.Planning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlanningRepository extends JpaRepository<Planning, Long>{
	
	/*@Query("select u from Planning u where u.numero_Intervention like :x "
			)
	public Page<Planning> chercherUser(
			@Param("x")String key_word_numero_intervention,
			Pageable pageable);*/

	@Query("select u from Planning u where u.numero_Intervention like :x "
			+ "AND u.client.nom like :y "
			+ "AND u.client.code_postale like :z "
			+ "AND u.client.numero_client like :e "
			+ "AND u.user.departement.nom_depart like :depart "
			+ "AND u.etat.code_etat like :etat "
			+ "AND u.date_Intervention like :date_Inter"
			)
	public List<Planning> chercherUser(
			@Param("x") String num_intervention,
			@Param("y") String nom_client,
			@Param("z") String code_postale,
			@Param("e") String numero_client,
			@Param("depart") String nom_depart,
			@Param("etat") String nom_etat,
			@Param("date_Inter") String date_Inter
			);
}
