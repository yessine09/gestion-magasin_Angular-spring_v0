package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;
import java.util.HashMap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Client.CategorieClient;
import tn.esprit.spring.entity.Facture;


@Repository
public interface DetailFactureRepository extends JpaRepository<DetailFacture, Long>{
	
	 List<DetailFacture> findByFacture(Facture f);
	
	//search selon prix ou pourcentage  ou qte
	@Query("SELECT df FROM DetailFacture df WHERE df.produit.libelle LIKE %:keyword%"
	        + " OR CONCAT(df.pourcentageRemise, '') LIKE %:keyword%"
	        + " OR df.qte LIKE %:keyword%"
	        + " OR CONCAT(df.prixTotal, '') LIKE %:keyword%")
	List<DetailFacture> search(@Param("keyword") String keyword);
	
	
	//best product from detail facture
//	@Query("SELECT max(df.produit),df.facture.client.categorieClient FROM DetailFacture df where df.facture.dateFacture between :startDate and :endDate and df.facture.active=true ")
//	List<?> bestProduct(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	

	
	
	@Query("SELECT   sum(df.prixTotal)   , df.createdAt  FROM DetailFacture df GROUP BY  df.createdAt ORDER BY  df.createdAt")
	List<?> getPrixDate();
	
	
	
	// sum prix total pour chaque jour

	
	
}
