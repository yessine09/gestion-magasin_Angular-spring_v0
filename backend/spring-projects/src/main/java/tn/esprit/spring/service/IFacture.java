package tn.esprit.spring.service;

import java.util.List;

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
	


}
