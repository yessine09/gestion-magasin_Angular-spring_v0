package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.service.IClient;
import tn.esprit.spring.service.IProduit;

@RestController
public class ProduitRestController {
	
	@Autowired
	IProduit produitService;
	// // http://localhost:8088/SpringMVC/servlet/retrieve-all-clients
	
		@GetMapping("/retrieve-all-produits")
		
		//responseBody objet convertir l json (Serialisation)
		@ResponseBody
		public List<Produit> getProduits(){
			List<Produit> listProduits = produitService.retrieveAllProduits();
			return listProduits;
		}
	
		
		@PutMapping("Affecter/{produit-id}/{stock-id}")
		@ResponseBody
	public void affecterProduitToStock(@PathVariable ("produit-id") Long idProduit,@PathVariable ("stock-id") Long idStock) {
		produitService.affecterProduitToStock(idProduit, idStock);
		System.out.println("success");
	}
	

}
