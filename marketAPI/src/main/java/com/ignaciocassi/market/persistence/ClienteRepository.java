package com.ignaciocassi.market.persistence;

import com.ignaciocassi.market.domain.Client;
import com.ignaciocassi.market.domain.repository.ClientRepository;
import com.ignaciocassi.market.persistence.crud.ClienteCrudRepository;
import com.ignaciocassi.market.persistence.entities.Cliente;
import com.ignaciocassi.market.persistence.mapper.ClientMapper;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class ClienteRepository implements ClientRepository {

    private final ClienteCrudRepository clienteCrudRepository;

    private final ClientMapper mapper;

    public ClienteRepository(ClienteCrudRepository clienteCrudRepository, ClientMapper mapper) {
        this.clienteCrudRepository = clienteCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public Client save(Client client) {
        Cliente cliente = mapper.toCliente(client);
        return mapper.toClient(clienteCrudRepository.save(cliente));
    }

    @Override
    public Optional<Client> getClient(String id) {
        Optional<Cliente> cliente = clienteCrudRepository.findById(id);
        if (cliente.isPresent()) {
            return Optional.of(mapper.toClient(cliente.get()));
        } else {
            return Optional.empty();
        }
    }
}
