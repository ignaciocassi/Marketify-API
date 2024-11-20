package com.ignaciocassi.market.web.controller;

import com.ignaciocassi.market.domain.Purchase;
import com.ignaciocassi.market.domain.service.PurchaseService;
import com.ignaciocassi.market.web.exceptions.PurchaseNotFoundException;
import com.ignaciocassi.market.web.messages.ResponseStrings;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(description = "Get all the purchases.",
            security = { @SecurityRequirement(name = "JWT") })
    @ApiResponse(responseCode = "200", description = "OK.")
    @ApiResponse(responseCode = "404", description = "No purchases were found.")
    public ResponseEntity<List<Purchase>> getAll() {
        return purchaseService.getAll()
                .filter(purchases -> !purchases.isEmpty())
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElseThrow(() -> new PurchaseNotFoundException(ResponseStrings.NO_PURCHASES_LISTED));
    }

    @GetMapping("/client/{idClient}")
    @Operation(description = "Get all the purchases from a client by client ID.",
            security = { @SecurityRequirement(name = "JWT") })
    @ApiResponse(responseCode = "200", description = "OK.")
    @ApiResponse(responseCode = "404", description = "No purchases were found for that client ID.")
    public ResponseEntity<List<Purchase>> getByClient(
            @Parameter(description = "The ID of the client.", required = true, example = "4546221")
            @PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId)
                .filter(purchases -> !purchases.isEmpty())
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElseThrow(() -> new PurchaseNotFoundException(ResponseStrings.NO_PURCHASES_MADE));
    }

    @PostMapping("/save")
    @Operation(description = "Save a purchase.",
            security = { @SecurityRequirement(name = "JWT") })
    @ApiResponse(responseCode = "201", description = "Purchase successfully created.")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }

}
