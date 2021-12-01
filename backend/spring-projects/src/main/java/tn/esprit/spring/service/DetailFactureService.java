package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.DetailFactureRepository;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.repository.ProduitRepository;

@Service
public class DetailFactureService implements IDetailFacture {

	@Autowired
	private DetailFactureRepository detailFactureRepository;
	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	private FactureRepository factureRepository;


	@Override
	public List<DetailFacture> retrieveAllDetailFactures() {
		// TODO Auto-generated method stub

		List<DetailFacture> detailFactures= detailFactureRepository.findAll();

		return detailFactures;
	}

	@Override
	public DetailFacture addDetailFacture(DetailFacture f) {
		// TODO Auto-generated method stub
		detailFactureRepository.save(f);
		return f;
	}

	@Override
	public void deleteDetailFacture(Long id) {
		detailFactureRepository.deleteById(id);

	}

	@Override
	public DetailFacture updateDetailFacture(DetailFacture u) {
		// TODO Auto-generated method stub
		return detailFactureRepository.save(u);
	}

	@Override
	public DetailFacture retrieveDetailFacutre(Long id) {
		// TODO Auto-generated method stub
		DetailFacture detailFacture= detailFactureRepository.findById(id).orElse(null);
		return detailFacture;
	}

	/*@Override
	public DetailFacture addDetailFacture(DetailFacture df, Long idProduit, Long idFacture) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public DetailFacture addDetailFacture(DetailFacture df,Long idProduit,Long idFacture) {
		Produit p=produitRepository.getById(idProduit);
		df.setProduit(p);
		df.setMontantRemise(((p.getPrixUnitaire()*df.getQte())*df.getPourcentageRemise())/100);
		df.setPrixTotal((p.getPrixUnitaire()*df.getQte())-df.getMontantRemise());
		df.setFacture(factureRepository.getById(idFacture));
		System.out.println(df.getQte());
		System.out.println(df.getPourcentageRemise());
		return detailFactureRepository.save(df);
	}


}
