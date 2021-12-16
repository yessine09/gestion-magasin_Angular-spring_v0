package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.DetailProduit;

public interface IDetailProduit {
	List<DetailProduit> retrieveAllDetailProduits();

	DetailProduit addDetailProduit(DetailProduit dp);

	void deleteDetailProduit(Long id);

	DetailProduit updateDetailProduit(DetailProduit u);

	DetailProduit retrieveDetailProduit(Long id);

}
