package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Client;

public interface IClient {
	List<Client> retrieveAllClients();

	Client addClient(Client c);

	void deleteClient(Long id);

	Client updateClient(Client u);

	Client retrieveClient(Long id);
	boolean fidilite(Long id );

}
