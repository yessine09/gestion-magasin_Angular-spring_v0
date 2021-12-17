package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.DetailFacture;


public interface IDetailFacture {
	
	List<DetailFacture> retrieveAllDetailFactures();

	DetailFacture addDetailFacture(DetailFacture f);

	void deleteDetailFacture(Long id);

	DetailFacture updateDetailFacture(DetailFacture u);

	DetailFacture retrieveDetailFacutre(Long id);
	
	DetailFacture addDetailFacture(DetailFacture df,Long idProduit,Long idFacture);
	
	List<DetailFacture> retrieveIdFactures(Long idFacture);
	
//List<Dashboard> dashPrixDate();
	
	List<DetailFacture> retrieveDetailFactureByIdFacture(Long idF);
	
	List<DetailFacture> search(String keyword);
	
//	List<?> bestProduct(Date startDate, Date endDate);
	
	List<?> getPrixDate();
	


}
