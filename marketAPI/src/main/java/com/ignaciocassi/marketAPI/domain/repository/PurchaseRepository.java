package com.ignaciocassi.marketAPI.domain.repository;

import com.ignaciocassi.marketAPI.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    //Especificación del repositorio.
    //Define lo que las implementaciones del repositorio deberán hacer.

    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);
}
