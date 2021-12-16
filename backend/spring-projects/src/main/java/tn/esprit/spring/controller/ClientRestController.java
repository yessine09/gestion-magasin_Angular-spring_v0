	package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.services.IClient;

@RestController
public class ClientRestController {

	@Autowired
	IClient clientService;
    @PreAuthorize("hasAnyRole('Client')") 

	// // http://localhost:8089/SpringMVC/client/retrieve-all-clients
	@GetMapping("/retrive-all-clients")
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
	}
	@GetMapping("/fidelite/{client-id}")
	@ResponseBody
	public boolean fidelite(@PathVariable("client-id")Long clientId) {
		return clientService.fidilite(clientId);
		}
	

}