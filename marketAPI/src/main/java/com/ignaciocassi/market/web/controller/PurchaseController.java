package com.ignaciocassi.market.web.controller;

import com.ignaciocassi.market.domain.Purchase;
import com.ignaciocassi.market.domain.service.PurchaseService;
import com.ignaciocassi.market.web.exceptions.PurchaseNotFoundException;
import com.ignaciocassi.market.web.messages.ResponseStrings;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all the purchases.",
            authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No purchases were found.")
    })
    public ResponseEntity<List<Purchase>> getAll() {
        return purchaseService.getAll()
                .filter(purchases -> !purchases.isEmpty())
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElseThrow(() -> new PurchaseNotFoundException(ResponseStrings.NO_PURCHASES_LISTED));
    }

    @GetMapping("/client/{idClient}")
    @ApiOperation(value = "Get all the purchases from a client by client ID.",
            authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No purchases were found for that client ID.")
    })
    public ResponseEntity<List<Purchase>> getByClient(
            @ApiParam(value = "The ID of the client.", required = true, example = "4546221")
            @PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId)
                .filter(purchases -> !purchases.isEmpty())
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElseThrow(() -> new PurchaseNotFoundException(ResponseStrings.NO_PURCHASES_MADE));
    }

    @PostMapping("/save")
    @ApiOperation(value = "Save a purchase.",
            authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 201, message = "Purchase successfully created.")
    })
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }

}
