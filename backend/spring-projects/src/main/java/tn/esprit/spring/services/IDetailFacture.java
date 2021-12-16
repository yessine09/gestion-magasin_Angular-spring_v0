package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.DetailFacture;

public interface IDetailFacture {
	List<DetailFacture> retrieveAllDetailFactures();

	DetailFacture addDetailFacture(DetailFacture df);

	void deleteDetailFacture(Long id);

	DetailFacture updateDetailFacture(DetailFacture u);

	DetailFacture retrieveDetailFacture(Long id);

}
