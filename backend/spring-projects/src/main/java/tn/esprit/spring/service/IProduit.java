package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Produit;

public interface IProduit {
	List<Produit> retrieveAllProduits();

	Produit addProduit(Produit p);

	void deleteProduit(Long id);

	Produit updateProduit(Produit u);

	Produit retrieveProduit(Long id);
	
	String affecterProduitToStock(Long idProduit, Long idStock);

}
