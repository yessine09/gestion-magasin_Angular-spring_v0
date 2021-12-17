package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Client.CategorieClient;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;

public interface IFacture {
	List<Facture> retrieveAllFactures();

	Facture addFacture(Facture f);

	void deleteFacture(Long id);

	Facture updateFacture(Facture u);

	Facture retrieveFacutre(Long id);
	
	Facture addFacture(Facture f, Long idClient);
	
	List<Facture> listAll();
	
	List<Facture> getFacturesByClient(Long idClient);
	
	float getChiffreAffaireParCategorieClient(CategorieClient categorieClient,
			Date startDate, Date endDate);
	public Facture add( Long idClient);
	
	List<?>  getCaByCategorie();
	
	List<Facture> retrieveFactureByClient(Client client);
	


}
