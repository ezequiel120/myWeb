package com.example.myWeb.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "administrador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Administrador extends Persona {
    @Column(name = "cargo")
    private String cargo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagen_id", nullable = false)//
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @NonNull
    private Imagen imagen=new Imagen();

    public Administrador(String cargo, String apellido, String nombre, String email, long dni, long password) {
        this.cargo = cargo;
    }
}
