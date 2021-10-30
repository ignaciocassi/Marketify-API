package com.ignaciocassi.marketAPI.domain.service;

import com.ignaciocassi.marketAPI.domain.Purchase;
import com.ignaciocassi.marketAPI.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//Servicio utilizado para implementar lógica de negocio y para desacoplar la capa de dominio de la capa de persistencia.

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

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
