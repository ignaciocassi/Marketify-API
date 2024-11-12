package com.ignaciocassi.market.domain.service;

import com.ignaciocassi.market.domain.Purchase;
import com.ignaciocassi.market.domain.repository.ClientRepository;
import com.ignaciocassi.market.domain.repository.PurchaseRepository;
import com.ignaciocassi.market.web.exceptions.ClientNotFoundException;
import com.ignaciocassi.market.web.messages.ResponseStrings;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final ClientRepository clientRepository;

    public PurchaseService(PurchaseRepository purchaseRepository,
                           ClientRepository clientRepository) {
        this.purchaseRepository = purchaseRepository;
        this.clientRepository = clientRepository;
    }

    public Optional<List<Purchase>> getAll() {
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId) {
        validateClientExists(clientId);
        return purchaseRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase) {
        validateClientExists(purchase.getClientId());
        return purchaseRepository.save(purchase);
    }

    public Optional<List<Purchase>> getByContainingProduct(int productId) {
        Optional<List<Purchase>> byContainingProduct = purchaseRepository.getByContainingProduct(productId);
        return byContainingProduct;
    }

    private void validateClientExists(String clientId) {
        if (clientRepository.getClient(clientId).isEmpty()) {
            throw new ClientNotFoundException(ResponseStrings.CLIENT_NOT_FOUND);
        }
    }

}
