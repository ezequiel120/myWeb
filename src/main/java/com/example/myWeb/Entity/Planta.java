package com.example.myWeb.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "planta")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "precio")
    private int precio;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagen_id", nullable = false)//
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @NonNull
    private Imagen imagen=new Imagen();
}
