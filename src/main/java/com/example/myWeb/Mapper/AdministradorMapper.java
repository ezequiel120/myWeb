package com.example.myWeb.Mapper;

import com.example.myWeb.DTO.AdministradorDTO;
import com.example.myWeb.Entity.Administrador;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper(componentModel="Spring")
public interface AdministradorMapper {
    AdministradorMapper INSTANCE= Mappers.getMapper(AdministradorMapper.class);

    Administrador toAdministrador(AdministradorDTO administradorDTO);
    AdministradorDTO toAdministradorDTO(Administrador administrador);

    List<Administrador> toAdmisntradorlist(List<AdministradorDTO> administradorDTOS);

    List<AdministradorDTO>toAdministradorDTOS(List<Administrador> administradors);
}
