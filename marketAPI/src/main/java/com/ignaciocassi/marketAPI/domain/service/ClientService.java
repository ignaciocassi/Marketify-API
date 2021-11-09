package com.ignaciocassi.marketAPI.domain.service;

import com.ignaciocassi.marketAPI.domain.Client;
import com.ignaciocassi.marketAPI.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getClient(String id) {
        return clientRepository.getClient(id);
    }

}
