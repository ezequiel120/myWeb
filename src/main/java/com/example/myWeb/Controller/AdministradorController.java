package com.example.myWeb.Controller;

import com.example.myWeb.DTO.AdministradorDTO;
import com.example.myWeb.DTO.MensajeDTO;
import com.example.myWeb.DTO.PlantaDTO;
import com.example.myWeb.Entity.Imagen;
import com.example.myWeb.Service.AdminService;
import com.example.myWeb.Service.AdminServiceImp;
import com.example.myWeb.Service.CloudinaryService;
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
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    AdminServiceImp adminServiceImp;
    @Autowired
    CloudinaryService cloudinaryService;

   /* @PostMapping
    public AdministradorDTO save(@RequestBody AdministradorDTO administradorDTO) {
        return adminServiceImp.save(administradorDTO);
    }*/


    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AdministradorDTO> guardar(
            @RequestPart("adminDTO") String AdminDTOJson,
            @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException
    {
        AdministradorDTO AdminDTO= adminServiceImp.JontoAdminDTO(AdminDTOJson);
        if (AdminDTO ==null)
            return new ResponseEntity(new MensajeDTO("no se convirto a json" + AdminDTOJson ), HttpStatus.BAD_REQUEST);

        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new MensajeDTO("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);

        Imagen imagen = new Imagen();
        imagen.setName  ((String) result.get("original_filename"));
        imagen.setImagenUrl((String) result.get("url"));
        imagen.setImagenId((String) result.get("public_id"));

        AdministradorDTO AdminCreado =adminServiceImp.save(AdminDTO, imagen);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AdminCreado);
    }


    @PutMapping("/administrador/{id}")
    public AdministradorDTO update(@PathVariable long id, @RequestBody AdministradorDTO administradorDTO){
        return adminServiceImp.update(id,administradorDTO);
    }

    @GetMapping
    public List<AdministradorDTO> findAll(){
        return adminServiceImp.finAll();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){

        if (!adminServiceImp.exists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Carrera not found with ID: " + id);
        }

        adminServiceImp.delete(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "administrador borrado");

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
