package com.example.myWeb.Service;

import com.example.myWeb.DTO.PlantaDTO;
import com.example.myWeb.Entity.Cliente;
import com.example.myWeb.Entity.Imagen;
import com.example.myWeb.Entity.Planta;
import com.example.myWeb.Exception.ResourseNotFounException;
import com.example.myWeb.Mapper.CategoriaMapper;
import com.example.myWeb.Mapper.PlantaMapper;
import com.example.myWeb.Repository.PlantaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantaServiceImpl {
    @Autowired
    PlantaRepository plantaRepository;

    @Autowired
    PlantaMapper plantaMapper;

    @Autowired
    CategoriaMapper categoriaMapper;

    @Transactional
    public PlantaDTO save(PlantaDTO plantaDTO, Imagen imagen) {
        Planta planta=new Planta();
        planta.setId(plantaDTO.getId());
        planta.setNombre(plantaDTO.getNombre());
        planta.setDescripcion(plantaDTO.getDescripcion());
        planta.setTipo(plantaDTO.getTipo());
        planta.setPrecio(plantaDTO.getPrecio());
        planta.setCategoria(plantaDTO.getCategoria());
        planta.setImagen(imagen);
        plantaRepository.save(planta);
        return plantaDTO;
    }
    public List<PlantaDTO>findAll(){
        return plantaMapper.toPlantaDTOS(plantaRepository.findAll());
    }

    //busqueda por id

    public PlantaDTO finById(long id) throws ResourseNotFounException {

            Planta planta= plantaRepository.findById(id).orElseThrow(()-> new ResourseNotFounException("carrera con id"+id+"no se encontro"));
            return plantaMapper.toPlantaDTO(planta);
    }

    public boolean exists(long id) {
        return plantaRepository.existsById(id);
    }

    public PlantaDTO update(long id,PlantaDTO plantaDTO){
        Optional<Planta> optionalPlanta = plantaRepository.findById(id);
        if (optionalPlanta.isEmpty()) {
            throw new RuntimeException("Id Invalido");
        }
        Planta planta=new Planta();
        planta.setId(plantaDTO.getId());
        planta.setNombre(plantaDTO.getNombre());
        planta.setDescripcion(plantaDTO.getDescripcion());
        planta.setTipo(plantaDTO.getTipo());
        planta.setPrecio(plantaDTO.getPrecio());
        planta.setCategoria(plantaDTO.getCategoria());
        plantaRepository.save(planta);
        return plantaDTO;
    }


    public PlantaDTO JontoPlantaDTO( String plantaDTOJson)
    {
        PlantaDTO  plantaDTO;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            plantaDTO =  objectMapper.readValue(plantaDTOJson, PlantaDTO.class);
            return plantaDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null; //new ResponseEntity(new MensajeDTO("no se convirto a json" + carreraDTOJson ), HttpStatus.BAD_REQUEST);
        }

    }


    public void delete(long id){
        plantaRepository.deleteById(id);
    }
}
