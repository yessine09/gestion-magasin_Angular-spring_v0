package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.repository.ClientRepository;

@Service
public class ClientService implements IClient {
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<Client> retrieveAllClients() {
		// TODO Auto-generated method stub
		//List<Client> Clients =(List<Client>) clientRepository.findAll();
		List<Client> Clients = clientRepository.findAll();
		return Clients;
	}
		
	

	@Override
	public Client addClient(Client c) {
		// TODO Auto-generated method stub
				clientRepository.save(c);
				return c;
	}

	@Override
	public void deleteClient(Long id) {
		// TODO Auto-generated method stub
		clientRepository.deleteById(id);
		System.out.println("Client deleted successfully");
		
	}

	@Override
	public Client updateClient(Client c) {
		// TODO Auto-generated method stub
		clientRepository.save(c);
		return c;
		
	}

	@Override
	public Client retrieveClient(Long id) {
		// TODO Auto-generated method stub
		Client client= clientRepository.findById(id).orElse(null);
		return client;
	}
	

}
