package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Fournisseur;
import tn.esprit.spring.repository.FournisseurRepository;

@Service
@Slf4j
public class IFournisseurServiceImpl implements IFournisseur {
	@Autowired
	FournisseurRepository fournisseurRepository;
	@Override
	public List<Fournisseur> retrieveAllFournisseurs() {
		List<Fournisseur> fournisseurs = (List<Fournisseur>) fournisseurRepository.findAll();
		return fournisseurs;
	}

	@Override
	public Fournisseur addFournisseur(Fournisseur f) {
		fournisseurRepository.save(f);
		return f;
	}

	@Override
	public void deleteFournisseur(Long id) {
		fournisseurRepository.deleteById(id);
		
	}

	@Override
	public Fournisseur updateFournisseur(Fournisseur u) {
		return fournisseurRepository.save(u);
	}

	@Override
	public Fournisseur retrieveFournisseur(Long id) {
		Fournisseur fournisseur = fournisseurRepository.findById(id).orElse(null);
		return fournisseur;	// TODO Auto-generated method stub
	}




}
