package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.repository.FactureRepository;
@Service
@Slf4j
public class FactureServiceImpl implements IFacture {
	@Autowired
	FactureRepository factureRepository;
	@Override
	public List<Facture> retrieveAllFactures() {
		List<Facture> factures = (List<Facture>) factureRepository.findAll();
		return factures;
	}

	@Override
	public void cancelFacture(Long id) {
		factureRepository.deleteById(id);			
	}

	@Override
	public Facture retrieveFacture(Long id) {
		Facture facture= factureRepository.findById(id).orElse(null);
		return facture;	// TODO Auto-generated method stub
	}

	@Override
	public Facture addFacture(Facture f) {
		factureRepository.save(f);
		return f;
	}

	@Override
	public Facture updateFacture(Facture u) {
		return factureRepository.save(u);
	}

}
