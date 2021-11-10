package com.ignaciocassi.marketAPI.web.controller;

import com.ignaciocassi.marketAPI.domain.Purchase;
import com.ignaciocassi.marketAPI.domain.service.PurchaseService;
import com.ignaciocassi.marketAPI.web.exceptions.ProductNotFoundException;
import com.ignaciocassi.marketAPI.web.exceptions.PurchaseNotFoundException;
import com.ignaciocassi.marketAPI.web.messages.ResponseStrings;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    @ApiOperation(value = "Get all the purchases.", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No purchases were found.")
    })
    public ResponseEntity<List<Purchase>> getAll() {
        //return purchaseService.getAll()
        //        .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
        //        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        Optional<List<Purchase>> purchases = purchaseService.getAll();
        if (!purchases.get().isEmpty()) {
            return new ResponseEntity<>(purchases.get(), HttpStatus.OK);
        } else {
            throw new PurchaseNotFoundException(ResponseStrings.NO_PURCHASES_LISTED);
        }
    }

    @GetMapping("/client/{idClient}")
    @ApiOperation(value = "Get all the purchases from a client by client ID.", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No purchases were found for that client ID.")
    })
    public ResponseEntity<List<Purchase>> getByClient(@ApiParam(value = "The ID of the client.", required = true, example = "4546221") @PathVariable("idClient") String clientId) {
        Optional<List<Purchase>> purchases = purchaseService.getByClient(clientId);
        if (!purchases.get().isEmpty()) {
            return new ResponseEntity<>(purchases.get(), HttpStatus.OK);
        } else {
            throw new PurchaseNotFoundException(ResponseStrings.NO_PURCHASES_MADE);
        }
    }

    @PostMapping("/save")
    @ApiOperation(value = "Save a purchase.", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 201, message = "Purchase successfully created.")
    })
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase),HttpStatus.CREATED);
    }

}
