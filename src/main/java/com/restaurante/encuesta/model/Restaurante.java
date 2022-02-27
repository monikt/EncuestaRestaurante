package com.restaurante.encuesta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity

public class Restaurante {
    @Id
    @Column(name = "ID_RESTAURANTE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_restaurante;
    @Column(name = "NOMBRE",nullable=false)
    private String nombre;
    @Column(name = "DIRECCION",nullable=false)
    private String direccion;
    @Column(name = "CIUDAD",nullable=false)
    private String ciudad;
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Encuesta> encuestas;
}
