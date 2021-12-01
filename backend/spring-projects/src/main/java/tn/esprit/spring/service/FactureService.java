package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.Facture;

import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.DetailFactureRepository;
import tn.esprit.spring.repository.FactureRepository;
import org.springframework.data.domain.Sort;

@Service
public class FactureService implements IFacture{

	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private DetailFactureRepository detailFactureRepository;
	
	
	
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
	
	@Override
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
	}
   public List<Facture> listAll() {
    return factureRepository.findAll(Sort.by("montantFacture").ascending());
}
	

}
