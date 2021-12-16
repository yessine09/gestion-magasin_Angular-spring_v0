package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.FactureRepository;
@Configuration
@EnableScheduling
@Service
@Slf4j
public class ClientServiceImpl implements IClient{
	@Autowired
	ClientRepository clientRepository ;
	@Autowired
	FactureRepository factrep;
	@Override
	public List<Client> retrieveAllClients() {
		List<Client> clients = (List<Client>) clientRepository.findAll();
		return clients;
	}	
	@Scheduled(cron = "* 1 * * * ?")
	public void retrieveAllClientsize() {
		List<Client> clients = (List<Client>) clientRepository.findAll();
		System.out.println("Nombre des Clients "+clients.size());
		
	}

	@Override
	public Client addClient(Client c) {
		clientRepository.save(c);
		return c;
	}

	@Override
	public void deleteClient(Long id) {
		clientRepository.deleteById(id);		
		
	}

	@Override
	public Client updateClient(Client u) {
		return clientRepository.save(u);

	}


	@Override
	public Client retrieveClient(Long id) {
		Client client = clientRepository.findById(id).orElse(null);
		return client;	
	}
	@Override
	public boolean fidilite(Long id ){
		Client c=clientRepository.findById(id).orElse(null);
		int s=c.getFactures().size();
		if(s>=2){
			return true;
		}
		else 
		return false;
		
	}

}
