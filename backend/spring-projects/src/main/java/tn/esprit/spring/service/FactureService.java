package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Client.CategorieClient;
import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.DetailFactureRepository;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.repository.ProduitRepository;

import org.springframework.data.domain.Sort;

@Service
public class FactureService implements IFacture{

	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private DetailFactureRepository detailFactureRepository;
	
	@Autowired
	ProduitRepository repoProduit;
	
	
	
	
	@Override
	public List<Facture> retrieveAllFactures() {
		// TODO Auto-generated method stub
		//List<Facture> factures=  factureRepository.findAll();
		List<Facture> factures= (List<Facture>) factureRepository.findAll();
		return factures;
	}

	@Override
	public Facture addFacture(Facture f) {
		// TODO Auto-generated method stub
		factureRepository.save(f);
		return f;
	}
	
	

	@Override
	public void deleteFacture(Long id) {
		// TODO Auto-generated method stub
		factureRepository.deleteById(id);
	}

	@Override
	public Facture updateFacture(Facture u) {
		// TODO Auto-generated method stub
		return factureRepository.save(u);	}

	@Override
	public Facture retrieveFacutre(Long id) {
		// TODO Auto-generated method stub
		Facture facture= factureRepository.findById(id).orElse(null);
		return facture;
	}
	
	@Transactional
	public Facture addFacture(Facture f, Long idClient) {
		f.setClient(clientRepository.getById(idClient));
		List<DetailFacture> list=detailFactureRepository.findAll();
		float montantRemise=0;
		float montantFacture=0;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getFacture().getIdFacture()==f.getIdFacture()){
				montantRemise+=list.get(i).getMontantRemise();
				montantFacture+=list.get(i).getPrixTotal();
			}
		}
		f.setMontantRemise(montantRemise);
	f.setActive(true);
	return factureRepository.save(f);
//		Facture f=factureRepository.getById(idFacture);
//		f.setClient(clientRepository.getById(idClient));
//		f.setDateFacture(new Date());
//		f.setActive(true);
//		f.setCreatedAt(new Date());
//		List<DetailFacture> detailsFacture=(List<DetailFacture>) f.getDetailFactures();
//		Facture facture=addDetailsFacture(f,detailsFacture);
	//	return repo.save(facture);
	}
   public List<Facture> listAll() {
    return factureRepository.findAll(Sort.by("montantFacture").ascending());
}
   
   public Facture addDetailsFacture(Facture f,List<DetailFacture>detailsFacture){
		float montantRemise=0;
		float montantFacture=0;
		for(DetailFacture detail:detailsFacture){
			Produit produit=repoProduit.getById(detail.getProduit().getIdProduit());
			float prixTotalDetail=detail.getQte()*produit.getPrixUnitaire();
			float montantRemiseDetail=(prixTotalDetail*detail.getPourcentageRemise())/100;
			float prixTotalDetailRemise=prixTotalDetail-montantRemiseDetail;
			detail.setMontantRemise(montantRemiseDetail);
			detail.setPrixTotal(prixTotalDetailRemise);
			montantFacture+=prixTotalDetailRemise;
			montantRemise+=montantRemiseDetail;
			detail.setProduit(produit);
			detail.setFacture(f);
			//detail.setCreatedAt(new Date());
			detailFactureRepository.save(detail);
		}
		f.setMontantFacture(montantFacture);
		f.setMontantRemise(montantRemise);
		return f;
	}

@Override
public List<Facture> getFacturesByClient(Long idClient) {
	return factureRepository.FacturesByClient(idClient);
}

@Override
public float getChiffreAffaireParCategorieClient(CategorieClient categorieClient, Date startDate, Date endDate) {

		return factureRepository.calculer(categorieClient, startDate, endDate);
}

@Override
public Facture add(Long idClient) {
	Facture f=new Facture();
	f.setClient(clientRepository.getById(idClient));
	f.setDateFacture(new Date());
	f.setActive(true);
	return factureRepository.save(f);
}

@Override
public List<?> getCaByCategorie() {
	return factureRepository.getCAByCategorie();
}

@Override
public List<Facture> retrieveFactureByClient(Client client) {
	// TODO Auto-generated method stub
			List<Facture> facture = factureRepository.retrieveFactureByClient(client);
			return facture;
		}

}
