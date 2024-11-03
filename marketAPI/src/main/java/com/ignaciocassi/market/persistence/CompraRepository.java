package com.ignaciocassi.market.persistence;

import com.ignaciocassi.market.domain.Purchase;
import com.ignaciocassi.market.domain.repository.PurchaseRepository;
import com.ignaciocassi.market.persistence.crud.CompraCrudRepository;
import com.ignaciocassi.market.persistence.entities.Compra;
import com.ignaciocassi.market.persistence.mapper.PurchaseMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

//Repositorio de la capa de persistencia utilizado por la capa de dominio.
//Implementa la especifiación del repositorio PurchaseRepository.
@Repository
public class CompraRepository implements PurchaseRepository {

    private final CompraCrudRepository compraCrudRepository;

    private final PurchaseMapper mapper;

    public CompraRepository(CompraCrudRepository compraCrudRepository, PurchaseMapper mapper) {
        this.compraCrudRepository = compraCrudRepository;
        this.mapper = mapper;
    }

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
