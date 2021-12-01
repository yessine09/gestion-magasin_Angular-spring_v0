package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.StockRepository;

@Service
public class ProduitService implements IProduit {
	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private StockRepository stockRepository;


	@Override
	public List<Produit> retrieveAllProduits() {
		// TODO Auto-generated method stub
		//List<Produit> produits = (List<Produit>) produitRepository.findAll();
		List<Produit> produits =  produitRepository.findAll();
		return produits;
	}

	@Override
	public Produit addProduit(Produit p) {
		// TODO Auto-generated method stub
		produitRepository.save(p);
		return p;
	}

	@Override
	public void deleteProduit(Long id) {
		// TODO Auto-generated method stub
		produitRepository.deleteById(id);

	}

	@Override
	public Produit updateProduit(Produit u) {
		// TODO Auto-generated method stub
		return produitRepository.save(u);
	}

	@Override
	public Produit retrieveProduit(Long id) {
		// TODO Auto-generated method stub
		Produit produit = produitRepository.findById(id).orElse(null);
		return produit;
	}

	@Override
	public String affecterProduitToStock(Long idProduit, Long idStock) {
				
		Stock stock= stockRepository.findById(idStock).get();
		Produit produit=produitRepository.findById(idProduit).get();
		
		produit.setStock(stock);
		produitRepository.save(produit);
		return "success";
		
		
		
	}
	
	
	



}
