package com.ignaciocassi.marketAPI.persistence.mapper;

import com.ignaciocassi.marketAPI.domain.Product;
import com.ignaciocassi.marketAPI.persistence.entities.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

//Indica que es un componente Mapper con modelo tipo "Spring".
//Indicamos que vamos a usar Category con su data Mapper dentro de ProductMapper.
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    //Mapper toProduct Convierte (Producto -> Product)
    //Define los mappings para todos los atributos de Product.
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category"),
    })
    Product toProduct(Producto producto);
    //Conversión en plural.
    List<Product> toProducts(List<Producto> productos);

    //Mapper toProducto Convierte (Product -> Producto)
    //Indica que la conversión es la inversa del mapping inferido.
    //En el caso del Mapping del atributo codigoBarras en Producto, no queremos exponerlo en la API, por lo que no
    //formará parte de Product (capa de dominio), será ignorado en la conversión.
    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
    //Conversión en plural.
    List<Producto> toProductos(List<Product> products);

}
