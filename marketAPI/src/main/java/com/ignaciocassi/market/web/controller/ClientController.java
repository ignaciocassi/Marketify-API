package com.ignaciocassi.market.web.controller;

import com.ignaciocassi.market.domain.Client;
import com.ignaciocassi.market.domain.service.ClientService;
import com.ignaciocassi.market.web.exceptions.ClientNotFoundException;
import com.ignaciocassi.market.web.messages.ResponseStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") String clientId) {
        Optional<Client> client = clientService.getClient(clientId);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            throw new ClientNotFoundException(ResponseStrings.CLIENT_NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

}
