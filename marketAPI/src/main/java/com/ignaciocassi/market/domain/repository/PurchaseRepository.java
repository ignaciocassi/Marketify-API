package com.ignaciocassi.market.domain.repository;

import com.ignaciocassi.market.domain.Purchase;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    //Especificación del repositorio.
    //Define las operaciones que serán utilizadas en la capa de dominio y que deben ser implementadas
    //Por el respositorio.

    Optional<List<Purchase>> getAll();

    Optional<List<Purchase>> getByClient(String clientId);

    Purchase save(Purchase purchase);

    Optional<List<Purchase>> getByContainingProduct(int productId);
}
