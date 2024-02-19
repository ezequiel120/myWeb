package com.example.myWeb.Mapper;

import com.example.myWeb.DTO.ClienteDTO;
import com.example.myWeb.Entity.Cliente;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper(componentModel="Spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE= Mappers.getMapper(ClienteMapper.class);

    Cliente toCliente(ClienteDTO clienteDTO);
    ClienteDTO toClienteDTO(Cliente cliente);
    List<Cliente>toClientes(List<ClienteDTO> clienteDTOS);
    List<ClienteDTO>toClienteDTOS(List<Cliente> clientes);
}
