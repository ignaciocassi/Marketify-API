package com.ignaciocassi.market.domain.service;

import com.ignaciocassi.market.domain.Purchase;
import com.ignaciocassi.market.domain.repository.PurchaseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//Servicio utilizado para implementar lógica de negocio y para desacoplar la capa de dominio de la capa de persistencia.

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public Optional<List<Purchase>> getAll() {
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId) {
        return purchaseRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

}
