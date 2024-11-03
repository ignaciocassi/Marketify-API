package com.ignaciocassi.market.persistence.mapper;

import com.ignaciocassi.market.domain.Category;
import com.ignaciocassi.market.persistence.entities.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    //Mapper toCategory Convierte (Categoria -> Category)
    //Define los mappings para todos los atributos de Category.
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);
    List<Category> toCategories(List<Categoria> categorias);

    //Indica que la conversión es la inversa del mapping inferido.
    //En el caso del Mapping del atributo productos en Categoria, no tiene un atributo que le corresponda en Category.
    //Por lo que se debe ignorar el mapeo de ese atributo.
    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
    List<Categoria> toCategorias(List<Category> categories);
}
