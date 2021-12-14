package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.DetailFacture;


public interface IDetailFacture {
	
	List<DetailFacture> retrieveAllDetailFactures();

	DetailFacture addDetailFacture(DetailFacture f);

	void deleteDetailFacture(Long id);

	DetailFacture updateDetailFacture(DetailFacture u);

	DetailFacture retrieveDetailFacutre(Long id);
	
	DetailFacture addDetailFacture(DetailFacture df,Long idProduit,Long idFacture);


}
