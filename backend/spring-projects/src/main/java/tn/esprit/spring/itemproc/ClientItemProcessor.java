package tn.esprit.spring.itemproc;

import java.util.Set;

import org.springframework.batch.item.ItemProcessor;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Role;

public class ClientItemProcessor implements ItemProcessor<Client, Client> {

	@Override
	public Client process(Client Client) throws Exception {

		return Client;
	}

}
