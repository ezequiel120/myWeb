package com.example.myWeb.Service;

import com.example.myWeb.DTO.AdministradorDTO;
import com.example.myWeb.DTO.PlantaDTO;
import com.example.myWeb.Entity.Imagen;

import java.util.List;

public interface AdminService {

    public AdministradorDTO save(AdministradorDTO AdminDTO, Imagen imagen) ;

    public List<AdministradorDTO> findAll();
}
