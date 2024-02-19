package com.example.myWeb.Service;

import com.example.myWeb.DTO.AdministradorDTO;
import com.example.myWeb.DTO.CategoriaDTO;
import com.example.myWeb.Entity.Administrador;
import com.example.myWeb.Entity.Categoria;
import com.example.myWeb.Mapper.CategoriaMapper;
import com.example.myWeb.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    CategoriaMapper categoriaMapper;

    public CategoriaDTO SAVE(CategoriaDTO categoriaDTO) {

        Categoria cat = new Categoria();
        cat.setId(categoriaDTO.getId());
        cat.setNombre(categoriaDTO.getNombre());
        cat.setDescripcion(categoriaDTO.getDescripcion());
        categoriaRepository.save(cat);
        return categoriaDTO;
    }

    public List<CategoriaDTO> FindAll() {
        return categoriaMapper.toCategoriaDTOS(categoriaRepository.findAll());
    }

    public CategoriaDTO update(long id, CategoriaDTO categoriaDTO) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isEmpty()) {
            throw new RuntimeException("Id Invalido");
        }
        Categoria cat=new Categoria();
        cat.setId(categoriaDTO.getId());
        cat.setNombre(categoriaDTO.getNombre());
        cat.setDescripcion(categoriaDTO.getDescripcion());
        categoriaRepository.save(cat);
        return categoriaDTO;
    }

    public boolean exists (long id){
        return categoriaRepository.existsById(id);
    }

    public void delete(long id){
        categoriaRepository.deleteById(id);
    }
}
