package com.example.myWeb.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "administrador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Administrador extends Persona {
    @Column(name = "cargo")
    private String cargo;

    public Administrador(String cargo, String apellido, String nombre, String email, long dni, long password) {
        this.cargo = cargo;
    }
}
