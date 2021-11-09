package com.ignaciocassi.marketAPI.domain.repository;

import com.ignaciocassi.marketAPI.domain.Client;

import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> getClient(String id);
}
