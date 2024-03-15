package com.example.myWeb.Controller;

import com.example.myWeb.DTO.ClienteDTO;
import com.example.myWeb.Service.ClienteService;
import com.example.myWeb.Service.ClienteServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ClienteDTO Save(@Valid @org.springframework.web.bind.annotation.RequestBody ClienteDTO clienteDTO){
        return clienteService.save(clienteDTO);
    }

    @PutMapping("/cliente/{id}")
    public ClienteDTO update(@PathVariable long id, @org.springframework.web.bind.annotation.RequestBody  ClienteDTO clienteDTO){
        return clienteService.update(id, clienteDTO);
    }
    @GetMapping
    public List<ClienteDTO> findAll(){
        return clienteService.findAll();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){

        if (!clienteService.exists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Carrera not found with ID: " + id);
        }

        clienteService.delete(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Cliente borrado");

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
