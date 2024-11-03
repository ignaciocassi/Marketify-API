package com.ignaciocassi.market.web.controller;

import com.ignaciocassi.market.domain.Client;
import com.ignaciocassi.market.domain.service.ClientService;
import com.ignaciocassi.market.web.exceptions.ClientNotFoundException;
import com.ignaciocassi.market.web.messages.ResponseStrings;
import io.swagger.annotations.*;
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
    @ApiOperation(value = "Get a client by client ID.", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "Client not found.")
    })
    public ResponseEntity<Client> getClient(@ApiParam(value = "The id of the client.", required = true, example = "2") @PathVariable("id") String clientId) {
        Optional<Client> client = clientService.getClient(clientId);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            throw new ClientNotFoundException(ResponseStrings.CLIENT_NOT_FOUND);
        }
    }

    @PostMapping("/save")
    @ApiOperation(value = "Save a client.", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 201, message = "Client successfully created."),
    })
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

}
