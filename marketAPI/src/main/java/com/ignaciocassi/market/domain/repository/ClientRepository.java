package com.ignaciocassi.market.domain.repository;

import com.ignaciocassi.market.domain.Client;

import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> getClient(String id);
}
