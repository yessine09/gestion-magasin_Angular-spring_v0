package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Fournisseur;
import tn.esprit.spring.services.IFournisseur;
@RestController

public class FournisseurRestController {
	@Autowired
	IFournisseur forr;
	
	@GetMapping("/retrive-all-forr")
	//responseBody objet convertir l json (Serialisation)
	@ResponseBody
	public List<Fournisseur> getFour(){
		List<Fournisseur> four = forr.retrieveAllFournisseurs();
		return four;
	}
}
