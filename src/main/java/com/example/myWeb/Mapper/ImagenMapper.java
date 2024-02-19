package com.example.myWeb.Mapper;

import com.example.myWeb.DTO.ImagenDTO;
import com.example.myWeb.Entity.Imagen;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ImagenMapper {
    static final ImagenMapper Instance= Mappers.getMapper(ImagenMapper.class);
    ImagenDTO toImagenDTO(Imagen imagen);

    Imagen toImagen(ImagenDTO DTO);
    List<ImagenDTO> toImagenesDTO(List<Imagen> imagenes);

    List<Imagen> toImagenes(List<ImagenDTO> imagenesDTO);
}
