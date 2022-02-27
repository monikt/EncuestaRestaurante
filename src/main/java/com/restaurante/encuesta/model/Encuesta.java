package com.restaurante.encuesta.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Encuesta {

    @Id
    @Column(name = "ID_ENCUESTA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_encuesta;
    @Column(name = "NOMBRE_ENCUESTA",nullable=false)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "ID_RESTAURANTE", referencedColumnName = "ID_RESTAURANTE")
    @JsonIgnore
    private Restaurante restaurante;
    @OneToMany(mappedBy = "encuesta", cascade = CascadeType.ALL)
    private List<Pregunta> preguntas;
}
