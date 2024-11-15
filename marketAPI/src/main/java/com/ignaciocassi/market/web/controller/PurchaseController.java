package com.ignaciocassi.market.web.controller;

import com.ignaciocassi.market.domain.Purchase;
import com.ignaciocassi.market.domain.service.PurchaseService;
import com.ignaciocassi.market.web.exceptions.PurchaseNotFoundException;
import com.ignaciocassi.market.web.messages.ResponseStrings;
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
    public ResponseEntity<List<Purchase>> getAll() {
        return purchaseService.getAll()
                .filter(purchases -> !purchases.isEmpty())
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElseThrow(() -> new PurchaseNotFoundException(ResponseStrings.NO_PURCHASES_LISTED));
    }

    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<Purchase>> getByClient(
           @PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId)
                .filter(purchases -> !purchases.isEmpty())
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElseThrow(() -> new PurchaseNotFoundException(ResponseStrings.NO_PURCHASES_MADE));
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }

}
