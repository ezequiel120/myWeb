package com.example.myWeb.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class PersonaDTO {
    long id;
    long telefono;
    String nombre;
    String apellido;
    String email;
    long dni;
    String password;
}
