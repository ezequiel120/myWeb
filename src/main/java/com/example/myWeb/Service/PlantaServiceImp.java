package com.example.myWeb.Service;

import com.example.myWeb.DTO.PlantaDTO;
import com.example.myWeb.Entity.Imagen;

import java.util.List;

public interface PlantaServiceImp {
    public PlantaDTO save(PlantaDTO plantaDTO, Imagen imagen) ;

    public List<PlantaDTO> findAll();
}
