package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.service.IClient;

@RestController
public class ClientRestController {

	@Autowired
	IClient clientService;
	// // http://localhost:8088/SpringMVC/servlet/retrieve-all-clients
	
		@GetMapping("/retrieve-all-clients")
		//responseBody objet convertir l json (Serialisation)
		
		@ResponseBody
		public List<Client> getClients(){
			List<Client> listClients = clientService.retrieveAllClients();
			return listClients;
		}
	//recupérer selon l'id
	// http://localhost:8089/SpringMVC/client/retrieve-client/8
	@GetMapping("/retrieve-client/{client-id}")
	@ResponseBody
	public Client retriveClient(@PathVariable("client-id")Long clientId) {
		return clientService.retrieveClient	(clientId);
		}
	

	
	// http://localhost:8089/SpringMVC/client/add-client
	@PostMapping("/add-client")
	// ki converti men json lel objet (Deserialisation)
	@ResponseBody
	
	//RequestBody bch tchouf objet client mwjoud wala le, ken mch mwjoud tzidou si nn update
	public Client addClient(@RequestBody Client c)
	{
	Client client = clientService.addClient(c);
	return client;
	}
	
	// http://localhost:8089/SpringMVC/client/remove-client/{client-id}
	@DeleteMapping("/remove-client/{client-id}")
	@ResponseBody
	public void removeClient(@PathVariable("client-id") Long clientId) {	
	clientService.deleteClient(clientId);
	System.out.println("Deleted successfuly");
	}
	
	// http://localhost:8089/SpringMVC/client/modify-client
	@PutMapping("/modify-client")
	@ResponseBody
	public Client modifyClient(@RequestBody Client client) {
	return clientService.updateClient(client);
	}
	
	
	
}
