package com.example.myWeb.Service;

import com.example.myWeb.DTO.ClienteDTO;
import com.example.myWeb.Entity.Cliente;
import com.example.myWeb.Mapper.ClienteMapper;
import com.example.myWeb.Repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteMapper clienteMapper;

    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente clien= new Cliente();
        clien.setId(clienteDTO.getId());
        clien.setNombre(clienteDTO.getNombre());
        clien.setApellido(clienteDTO.getApellido());
        clien.setDni(clienteDTO.getDni());
        clien.setTelefono(clienteDTO.getTelefono());
        clien.setSede(clienteDTO.getSede());
        clien.setEmail(clienteDTO.getEmail());
        clien.setPassword(clienteDTO.getPassword());
        clienteRepository.save(clien);
        return clienteDTO;

    }

    public List<ClienteDTO> findAll() {
        return clienteMapper.toClienteDTOS(clienteRepository.findAll());
    }

    public boolean exists(long id) {
        return clienteRepository.existsById(id);
    }

    public ClienteDTO update(long id, ClienteDTO clienteDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isEmpty()) {
            throw new RuntimeException("Id Invalido");
        }
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setDni(clienteDTO.getDni());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setPassword(clienteDTO.getPassword());
        cliente.setSede(clienteDTO.getSede());
        cliente.setTelefono(clienteDTO.getTelefono());

        clienteRepository.save(cliente);
        return clienteDTO;
    }

    public void delete(long id) {
        clienteRepository.deleteById(id);
    }
}
