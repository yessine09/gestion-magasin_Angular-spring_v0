package tn.esprit.spring.services;
import java.util.List;

import tn.esprit.spring.entities.Produit;

public interface IProduit {
	
	List<Produit> retrieveAllProduits();

	Produit addProduit(Produit p);

	void deleteProduit(Long id);

	Produit updateProduit(Produit u);

	Produit retrieveProduit(Long id);

}
