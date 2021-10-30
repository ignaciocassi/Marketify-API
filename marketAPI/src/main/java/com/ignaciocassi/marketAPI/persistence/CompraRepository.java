package com.ignaciocassi.marketAPI.persistence;

import com.ignaciocassi.marketAPI.domain.Purchase;
import com.ignaciocassi.marketAPI.domain.repository.PurchaseRepository;
import com.ignaciocassi.marketAPI.persistence.crud.CompraCrudRepository;
import com.ignaciocassi.marketAPI.persistence.entities.Compra;
import com.ignaciocassi.marketAPI.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

//Repositorio de la capa de persistencia utilizado por la capa de dominio.
//Implementa la especifiación del repositorio PurchaseRepository.

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public Optional<List<Purchase>> getAll() {
        List<Compra> compras = (List<Compra>) compraCrudRepository.findAll();
        return Optional.of(mapper.toPurchases(compras));
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(comprasProducto -> comprasProducto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
