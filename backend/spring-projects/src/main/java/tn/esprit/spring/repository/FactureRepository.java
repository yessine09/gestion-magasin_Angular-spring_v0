package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Client.CategorieClient;
import tn.esprit.spring.entity.Facture;
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
	
	@Query("SELECT f FROM Facture f WHERE montantFacture like %?1% ")
	List<Facture> findByName(Float montantFacture) ;
	
//	//get facutre selon idclient
	@Query("select f from Facture f where f.client.id= :idClient")
	List<Facture> FacturesByClient(@Param("idClient") Long idClient);
	
	//client selon id
	@Query("SELECT f FROM Facture f WHERE f.client = :facture")
	List<Facture> retrieveFactureByClient(@Param("facture") Client
	client);
	
	
	@Query("select sum (f.montantFacture) from Facture f where f.client.categorieClient=:categorieClient and f.active=TRUE and f.dateFacture between :startDate and :endDate")
	float calculer(@Param("categorieClient") CategorieClient categorieClient,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	@Query("select SUM(f.montantFacture) , f.client   from Facture f group by f.client order by SUM(f.montantFacture) desc ")
	List<?> getCAByCategorie();
	//float getCAByCategorie();
	
	
	//calculer chifre d'affaire selon categorieCLient entre deux dates

	
	

}
