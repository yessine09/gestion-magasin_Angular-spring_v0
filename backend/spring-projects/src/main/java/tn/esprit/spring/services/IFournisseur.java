package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Fournisseur;

public interface IFournisseur {
	List<Fournisseur> retrieveAllFournisseurs();

	Fournisseur addFournisseur(Fournisseur f);

	void deleteFournisseur(Long id);

	Fournisseur updateFournisseur(Fournisseur u);

	Fournisseur retrieveFournisseur(Long id);

}
