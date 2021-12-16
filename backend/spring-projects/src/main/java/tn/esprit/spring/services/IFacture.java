package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Facture;

public interface IFacture {
	List<Facture> retrieveAllFactures();
	Facture addFacture(Facture f);
	Facture updateFacture(Facture u);
	void cancelFacture(Long id);
	Facture retrieveFacture(Long id);

}
