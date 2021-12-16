package tn.esprit.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.UserDetailsImpl;
import tn.esprit.spring.repository.ClientRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;
    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        Optional<Client> optionalUser = clientRepository.findByEmail(Email);
        return Optional.ofNullable(optionalUser).orElseThrow(()->new UsernameNotFoundException("Email Not Found"))
               .map(UserDetailsImpl::new).get();
    }
}
