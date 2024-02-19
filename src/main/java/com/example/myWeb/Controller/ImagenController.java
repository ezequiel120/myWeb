package com.example.myWeb.Controller;

import com.example.myWeb.DTO.MensajeDTO;
import com.example.myWeb.Entity.Imagen;
import com.example.myWeb.Service.CloudinaryService;
import com.example.myWeb.Service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/imagenes")
@CrossOrigin
public class ImagenController {
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ImagenService imagenService;


    @GetMapping("/list")
    public ResponseEntity<List<Imagen>> list(){
        List<Imagen> list = imagenService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile )throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new MensajeDTO("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Imagen imagen = new Imagen();
        imagen.setName  ((String) result.get("original_filename"));
        imagen.setImagenUrl((String) result.get("url"));
        imagen.setImagenId((String) result.get("public_id"));
        imagenService.save(imagen);
        return new ResponseEntity(new MensajeDTO("imagen subida"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id)throws IOException {
        if(!imagenService.exists(id))
            return new ResponseEntity(new MensajeDTO("no existe"), HttpStatus.NOT_FOUND);
        Imagen imagen = imagenService.getOne(id).get();
        Map result = cloudinaryService.delete(imagen.getImagenId());
        imagenService.delete(id);
        return new ResponseEntity(new MensajeDTO("imagen eliminada"), HttpStatus.OK);
    }
}
