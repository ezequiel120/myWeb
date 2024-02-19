package com.example.myWeb.Mapper;

import com.example.myWeb.DTO.PlantaDTO;
import com.example.myWeb.Entity.Planta;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper(componentModel="Spring",uses = {CategoriaMapper.class,ImagenMapper.class})
public interface PlantaMapper {
    PlantaMapper INSTANCE= Mappers.getMapper(PlantaMapper.class);

    Planta toPlanta(PlantaDTO plantaDTO);

    PlantaDTO toPlantaDTO(Planta planta);
    List<Planta>toPlantaList(List<PlantaDTO> plantaDTOS);

    List<PlantaDTO> toPlantaDTOS(List<Planta> plantas);
}
