package com.example.myWeb.Controller;

import com.example.myWeb.DTO.AdministradorDTO;
import com.example.myWeb.Service.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    AdminServiceImp adminServiceImp;


    @PostMapping
    public AdministradorDTO save(@RequestBody AdministradorDTO administradorDTO) {
        return adminServiceImp.save(administradorDTO);
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
