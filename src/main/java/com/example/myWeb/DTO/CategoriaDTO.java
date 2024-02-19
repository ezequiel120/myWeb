package com.example.myWeb.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
@Getter
public class CategoriaDTO {
    long id;

    String descripcion;

    String nombre;
}
