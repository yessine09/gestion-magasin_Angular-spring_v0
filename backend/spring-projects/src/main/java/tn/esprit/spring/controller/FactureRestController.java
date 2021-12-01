
package tn.esprit.spring.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpServletResponse;

import org.aspectj.apache.bcel.classfile.Module.Export;
import org.hibernate.tool.schema.spi.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.pdfgenerator.FacturePDFExporter;
import tn.esprit.spring.service.IClient;
import tn.esprit.spring.service.IFacture;

@RestController
public class FactureRestController {

	@Autowired
	IFacture factureService;
	
	
	// http://localhost:8088/SpringMVC/servlet/retrieve-all-factures
		@GetMapping("/retrieve-all-factures")		
		@ResponseBody
		public List<Facture> getFactures(){
			List<Facture> listFactures = factureService.retrieveAllFactures();
			return listFactures;
		}
		
	//recupérer selon l'id
	// http://localhost:8088/SpringMVC/servlet/retrieve-facture/id
	@GetMapping("/retrieve-facture/{facture-id}")
	@ResponseBody
	public Facture retrieveFacture(@PathVariable("facture-id")Long factureId) {
		return factureService.retrieveFacutre(factureId);
		}
	

	
	// http://localhost:8088/SpringMVC/servlet/add-facture
	@PostMapping("/add-facture")
	// ki converti men json lel objet (Deserialisation)
	@ResponseBody
	
	//RequestBody bch tchouf objet client mwjoud wala le, ken mch mwjoud tzidou si nn update
	public Facture addFacture(@RequestBody Facture f)
	{
	Facture facture = factureService.addFacture(f);
	return facture;
	}
	
	// http://localhost:8088/SpringMVC/servlet/remove-facture/{facture-id}
	@DeleteMapping("/remove-facture/{facture-id}")
	@ResponseBody
	public void removeFacture (@PathVariable("facture-id") Long factureId) {
		
	factureService.deleteFacture(factureId);
	System.out.println("Deleted successfuly");
	}
	
	// http://localhost:8088/SpringMVC/servlet/modify-facture
	@PutMapping("/modify-facture")
	@ResponseBody
	public Facture modifyFacture(@RequestBody Facture facture) {
	return factureService.updateFacture(facture);
	}
	
	// http://localhost:8088/SpringMVC/servlet/facture/export
	@GetMapping("/facture/export")
	public  void exportToPdf  (HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename=facture_" + currentDateTime +  ".pdf" ;
		
		response.setHeader(headerKey, headerValue);
		
		List<Facture> listFactures = factureService.listAll();
		
		FacturePDFExporter exporter = new FacturePDFExporter(listFactures);
		exporter.export(response);
		
		
		
		
		
	}
	
	
}
