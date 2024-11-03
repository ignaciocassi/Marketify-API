package com.ignaciocassi.market.domain.service;

import com.ignaciocassi.market.domain.Client;
import com.ignaciocassi.market.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getClient(String id) {
        return clientRepository.getClient(id);
    }

}
