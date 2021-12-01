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

import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.service.IDetailFacture;
import tn.esprit.spring.service.IFacture;





@RestController
public class DetailFactureRestController {
	
	
	@Autowired
	IDetailFacture detailFactureService;

	// @Transactional permet de délimiter une transaction (entre le début et la fin de la méthode) et de définir le comportement transactionnel d'une méthode.
	// http://localhost:8088/SpringMVC/servlet/retrieve-all-detailFactures
			@GetMapping("/retrieve-all-detailFactures")		
			@ResponseBody
			public List<DetailFacture> getClients(){
				List<DetailFacture> listDetailFactures = detailFactureService.retrieveAllDetailFactures();
				return listDetailFactures;
			}
			
		//recupérer selon l'id	
		// http://localhost:8088/SpringMVC/servlet/retrieve-detailFacture/id
		@GetMapping("/retrieve-detailFacture/{detailFacture-id}")
		@ResponseBody
		public DetailFacture retrieveDetailFacture(@PathVariable("detailFacture-id")Long detailFactureId) {
			return detailFactureService.retrieveDetailFacutre(detailFactureId);
			}
		

		
		// http://localhost:8088/SpringMVC/servlet/add-detailFacture
		@PostMapping("/add-detailFacture/{produit-id}")
		@ResponseBody
		public DetailFacture addDetailFacture(@RequestBody DetailFacture df,@PathVariable("produit-id") Long produitId)
		{
			DetailFacture dFacture = detailFactureService.addDetailFacture(df,produitId,2L);
		    return dFacture;
		}
//		@PostMapping("/add-detailFacture")
//		// ki converti men json lel objet (Deserialisation)
//		@ResponseBody
//		//RequestBody bch tchouf objet client mwjoud wala le, ken mch mwjoud tzidou si nn update
//		public DetailFacture addDetailFacture(@RequestBody DetailFacture f)
//		{
//		DetailFacture detailFacture = detailFactureService.addDetailFacture(f);
//		return detailFacture ;
//		}
		
		// http://localhost:8088/SpringMVC/servlet/remove-detailFacture/{detailFacture-id}
		@DeleteMapping("/remove-detailFacture/{detailFacture-id}")
		@ResponseBody
		public void removeDetailFacture (@PathVariable("detailFacture-id") Long detailFactureId) {
			
		detailFactureService.deleteDetailFacture(detailFactureId);
		System.out.println("Deleted successfuly");
		
		}
		
		// http://localhost:8088/SpringMVC/servlet/modify-detailFacture
		@PutMapping("/modify-detailFacture")
		@ResponseBody
		public DetailFacture modifyDetailFacture(@RequestBody DetailFacture detailFacture) {
		return detailFactureService.updateDetailFacture(detailFacture);
		}
		
}
