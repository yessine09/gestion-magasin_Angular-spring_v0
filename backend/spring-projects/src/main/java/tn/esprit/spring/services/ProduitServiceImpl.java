package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.repository.ProduitRepository;
@Service
@Slf4j
public class ProduitServiceImpl implements IProduit{
	
	@Autowired
	private ProduitRepository produitRepository;

	@Override
	public List<Produit> retrieveAllProduits() {
		List<Produit> produits = (List<Produit>) produitRepository.findAll();
		return produits;
		
	}

	@Override
	public Produit addProduit(Produit p) {
		produitRepository.save(p);
		return p;
	}

	@Override
	public void deleteProduit(Long id) {
		produitRepository.deleteById(id);		
	}

	@Override
	public Produit updateProduit(Produit u) {
		return produitRepository.save(u);
	}

	@Override
	public Produit retrieveProduit(Long id) {
		Produit produit = produitRepository.findById(id).orElse(null);
		return produit;	// TODO Auto-generated method stub
		
	}

}
