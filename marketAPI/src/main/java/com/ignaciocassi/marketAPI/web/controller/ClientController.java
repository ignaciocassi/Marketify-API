package com.ignaciocassi.marketAPI.web.controller;

import com.ignaciocassi.marketAPI.domain.Client;
import com.ignaciocassi.marketAPI.domain.Product;
import com.ignaciocassi.marketAPI.domain.service.ClientService;
import com.ignaciocassi.marketAPI.web.exceptions.ClientNotFoundException;
import com.ignaciocassi.marketAPI.web.exceptions.ProductNotFoundException;
import com.ignaciocassi.marketAPI.web.messages.ResponseStrings;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    @ApiOperation("Get a client by client ID.")
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
    @ApiOperation("Save a client.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Client successfully created."),
    })
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

}
