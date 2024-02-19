package com.example.myWeb.DTO;
import com.example.myWeb.Entity.Categoria;
import lombok.*;

@Data
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
public class PlantaDTO {
    long id;
    String nombre;

    String tipo;

    int precio;

    String descripcion;

    Categoria categoria;
    ImagenDTO imagen;
}
