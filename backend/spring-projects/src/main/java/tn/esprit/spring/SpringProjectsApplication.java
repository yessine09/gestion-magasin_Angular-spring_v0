package tn.esprit.spring;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.service.ClientService;
import tn.esprit.spring.service.IClient;
import tn.esprit.spring.service.IProduit;


@EnableSwagger2
@SpringBootApplication
public class SpringProjectsApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectsApplication.class, args);
	}
	@Autowired
	IClient clientService;
	@Autowired
	IProduit produitService;

	@Override
	public void run(String... args) throws Exception {

		// TODO Auto-generated method stub
		//		clientService.addClient(new Client(1L,"ali","benFoulen",new Date(),"rades","client",Client.CategorieClient.FIDELE,Client.Profession.DOCTEUR));
		//		
		//		clientService.addClient(new Client(2L,"ali2","benFoulen2",new Date(),"rades2","client2",Client.CategorieClient.FIDELE,Client.Profession.DOCTEUR));
		//
		//		
		//		
		//		System.out.println("***************************");
		//		
		//		
		//		clientService.retrieveAllClients().forEach(c->{
		//			System.out.println(c.toString());
		//		});
		//		
		//		System.out.println("***************************");
		//		
		//		
		//		Client client = clientService.retrieveClient(1L); 
		//		System.out.println(client.getIdClient());
		//		
		//		System.out.println("***************************");
		//		
		//	clientService.updateClient(new Client(2L,"ali3","benFoulen2",new Date(),"rades2","client2",Client.CategorieClient.FIDELE,Client.Profession.DOCTEUR));
		//	
		//	clientService.deleteClient(2L);
		//
		//	System.out.println("******************************************************");
		//	produitService.addProduit(new Produit(1L,"abc123","app123",10.2f));
		//	
		//	produitService.addProduit(new Produit(2L,"123lmo123","cosmetic",10.22f));
		//	
		//	
		//	System.out.println("***************************");
		//	
		//	
		//	produitService.retrieveAllProduits().forEach(p->{
		//		System.out.println(p.toString());
		//	});
		//	
		//	System.out.println("***************************");
		//	
		//	
		//	Produit produit = produitService.retrieveProduit(1L); 
		//	System.out.println(produit.getIdProduit());
		//	
		//	System.out.println("***************************");
		//
	}
	//	


}