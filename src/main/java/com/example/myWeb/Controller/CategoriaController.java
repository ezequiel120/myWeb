package com.example.myWeb.Controller;

import com.example.myWeb.DTO.CategoriaDTO;
import com.example.myWeb.Entity.Categoria;
import com.example.myWeb.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cat")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @PostMapping
    public CategoriaDTO save(@RequestBody  CategoriaDTO categoriaDTO){
        return categoriaService.SAVE(categoriaDTO);
    }

    @GetMapping
    public List<CategoriaDTO>findALL(){
        return categoriaService.FindAll();
    }

    @PutMapping("/categoria/{id}")
    public CategoriaDTO update(@PathVariable long id,@RequestBody CategoriaDTO categoriaDTO){
        return categoriaService.update(id,categoriaDTO);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){

        if (!categoriaService.exists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Categoria not found with ID: " + id);
        }

        categoriaService.delete(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Categoria borrada");

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
