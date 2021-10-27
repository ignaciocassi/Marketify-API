package com.ignaciocassi.marketAPI.domain.service;

import com.ignaciocassi.marketAPI.domain.Purchase;
import com.ignaciocassi.marketAPI.domain.repository.PurchaseRepository;
import com.ignaciocassi.marketAPI.persistence.crud.CompraCrudRepository;
import com.ignaciocassi.marketAPI.persistence.crud.ProductoCrudRepository;
import com.ignaciocassi.marketAPI.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll() {
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId) {
        return purchaseRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

}
