package org.opendevup.dao;
import org.opendevup.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ClientRepository extends JpaRepository<Client,String>{
	
	@Query("select cl from Client cl where cl.nom like :nom "
			+ "AND cl.telephone like :telephone "
			+ "AND cl.code_postale like :codePostale "
			+ "AND cl.ville like :ville "
			+ "AND cl.numero_client like :numeroClient "
			+ "AND cl.reference like :reference "
			//+ "AND cl.numero_serie like : numeroSerie"
			)
	public Page<Client> chercherClients(
			@Param("nom")String key_word_nom,
			@Param("telephone")String key_word_telephone,
			@Param("codePostale")String key_word_code_postale,
			@Param("ville")String key_word_ville,
			@Param("numeroClient")String key_word_numero_client,
			@Param("reference")String key_word_ref_client,
			//@Param("numeroSerie")String key_word_numero_serie,
			Pageable pageable);
	
}
