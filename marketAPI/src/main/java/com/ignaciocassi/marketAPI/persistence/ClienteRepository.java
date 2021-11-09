package com.ignaciocassi.marketAPI.persistence;

import com.ignaciocassi.marketAPI.domain.Client;
import com.ignaciocassi.marketAPI.domain.repository.ClientRepository;
import com.ignaciocassi.marketAPI.persistence.crud.ClienteCrudRepository;
import com.ignaciocassi.marketAPI.persistence.entities.Cliente;
import com.ignaciocassi.marketAPI.persistence.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteRepository implements ClientRepository {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private ClientMapper mapper;

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
