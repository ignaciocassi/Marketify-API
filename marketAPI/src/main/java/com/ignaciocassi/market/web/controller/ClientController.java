package com.ignaciocassi.market.web.controller;

import com.ignaciocassi.market.domain.Client;
import com.ignaciocassi.market.domain.service.ClientService;
import com.ignaciocassi.market.web.exceptions.ClientNotFoundException;
import com.ignaciocassi.market.web.messages.ResponseStrings;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(description = "Get a client by client ID.", security = { @SecurityRequirement(name = "JWT") })
    @ApiResponse(responseCode = "200", description = "OK.")
    @ApiResponse(responseCode = "404", description = "Client not found.")
    public ResponseEntity<Client> getClient(@Parameter(description = "The id of the client.", required = true, example = "2") @PathVariable("id") String clientId) {
        Optional<Client> client = clientService.getClient(clientId);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            throw new ClientNotFoundException(ResponseStrings.CLIENT_NOT_FOUND);
        }
    }

    @PostMapping("/save")
    @Operation(description = "Save a client.", security = { @SecurityRequirement(name = "JWT") })
    @ApiResponse(responseCode = "201", description = "Client successfully created.")
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

}
