package com.example.myWeb.Service;

import com.example.myWeb.DTO.AdministradorDTO;
import com.example.myWeb.Entity.Administrador;
import com.example.myWeb.Mapper.AdministradorMapper;
import com.example.myWeb.Repository.AdministradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImp {
    @Autowired
    AdministradorRepository administradorRepository;
    @Autowired
    AdministradorMapper administradorMapper;


    public AdministradorDTO save(AdministradorDTO administradorDTO) {
        Administrador admin = new Administrador();
        admin.setId(administradorDTO.getId());
        admin.setNombre(administradorDTO.getNombre());
        admin.setDni(administradorDTO.getDni());
        admin.setEmail(administradorDTO.getEmail());
        admin.setApellido(administradorDTO.getApellido());
        admin.setPassword(administradorDTO.getPassword());
        admin.setTelefono(administradorDTO.getTelefono());
        admin.setCargo(administradorDTO.getCargo());
        administradorRepository.save(admin);
        return administradorDTO;
    }

    public List<AdministradorDTO> finAll() {
        return administradorMapper.toAdministradorDTOS(administradorRepository.findAll());
    }

    public AdministradorDTO update(long id, AdministradorDTO administradorDTO) {
        Optional<Administrador> optionAdmin = administradorRepository.findById(id);
        if (optionAdmin.isEmpty()) {
            throw new RuntimeException("Id Invalido");
        }
        Administrador admin = new Administrador();

        admin.setId(administradorDTO.getId());
        admin.setDni(administradorDTO.getDni());
        admin.setNombre(administradorDTO.getNombre());
        admin.setApellido(administradorDTO.getApellido());
        admin.setTelefono(administradorDTO.getTelefono());
        admin.setEmail(administradorDTO.getEmail());
        admin.setPassword(administradorDTO.getPassword());
        admin.setCargo(administradorDTO.getCargo());
        administradorRepository.save(admin);

        return administradorDTO;
    }

    public boolean exists (long id){
        return administradorRepository.existsById(id);
    }

    public void delete(long id) {
        administradorRepository.deleteById(id);
    }

}
