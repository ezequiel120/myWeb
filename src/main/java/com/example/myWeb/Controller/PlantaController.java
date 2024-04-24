package com.example.myWeb.Controller;

import com.example.myWeb.DTO.MensajeDTO;
import com.example.myWeb.DTO.PlantaDTO;
import com.example.myWeb.Entity.Imagen;
import com.example.myWeb.Exception.ResourseNotFounException;
import com.example.myWeb.Service.CloudinaryService;
import com.example.myWeb.Service.PlantaServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class PlantaController {

    @Autowired
    PlantaServiceImpl plantaService;

    @Autowired
    CloudinaryService cloudinaryService;

   /* @PostMapping
    public PlantaDTO save(@org.springframework.web.bind.annotation.RequestBody PlantaDTO plantaDTO){
        return plantaService.save(plantaDTO);
    } */

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PlantaDTO> guardar(
            @Valid
            @RequestPart("plantaDTO") String plantaDTOJson,
            @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException
    {
        PlantaDTO plantaDTO= plantaService.JontoPlantaDTO(plantaDTOJson);
        if (plantaDTO ==null)
            return new ResponseEntity(new MensajeDTO("no se convirto a json" + plantaDTOJson ), HttpStatus.BAD_REQUEST);

        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new MensajeDTO("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);

        Imagen imagen = new Imagen();
        imagen.setName  ((String) result.get("original_filename"));
        imagen.setImagenUrl((String) result.get("url"));
        imagen.setImagenId((String) result.get("public_id"));

        PlantaDTO plantaCreada = plantaService.save(plantaDTO, imagen);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(plantaCreada);
    }


    @PutMapping("/planta/{id}")
    public  PlantaDTO update(@PathVariable long id, @org.springframework.web.bind.annotation.RequestBody  PlantaDTO plantaDTO){
       return plantaService.update(id, plantaDTO);
    }



    @PutMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PlantaDTO> modificar(
            @RequestPart("plantaDTO") String plantaDTOJson,
            @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException
    {




        PlantaDTO plantaDTO= plantaService.JontoPlantaDTO(plantaDTOJson);
        if (plantaDTO ==null)
            return new ResponseEntity(new MensajeDTO("no se convirto a json" + plantaDTOJson ), HttpStatus.BAD_REQUEST);

        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new MensajeDTO("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Imagen imagen = new Imagen();
        imagen.setName  ((String) result.get("original_filename"));
        imagen.setImagenUrl((String) result.get("url"));
        imagen.setImagenId((String) result.get("public_id"));

        //CarreraDTO carreraModificada = carreraService.save(carreraDTO, imagen);

        PlantaDTO plantaModificada = plantaService.save(plantaDTO, imagen);

        //        CarreraDTO carreraModificada= carreraService.modificarCarrera(id, carreraDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(plantaModificada);
    }







    @GetMapping("/detail/{id}")
    public PlantaDTO findId( @PathVariable long id) throws ResourseNotFounException{
        return plantaService.finById(id);
    }

    @GetMapping
    public List<PlantaDTO> list()throws ResourseNotFounException {
        return plantaService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){

        if (!plantaService.exists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Planta not found with ID: " + id);
        }

        plantaService.delete(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Planta borrada");

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
