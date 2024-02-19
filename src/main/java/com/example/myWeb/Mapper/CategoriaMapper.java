package com.example.myWeb.Mapper;

import com.example.myWeb.DTO.CategoriaDTO;
import com.example.myWeb.Entity.Categoria;
import lombok.NonNull;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper(componentModel="Spring")

public interface CategoriaMapper {
    CategoriaMapper INSTANCE= Mappers.getMapper(CategoriaMapper.class);

    CategoriaDTO toCategoriDTO(Categoria categoria);
    Categoria toCategoria(CategoriaDTO categoriaDTO);
    List<CategoriaDTO> toCategoriaDTOS(List<Categoria> categorias);

    List<Categoria> toCategorias( List<CategoriaDTO> categoriaDTOS);
}
