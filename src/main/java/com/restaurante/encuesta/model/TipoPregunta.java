package com.restaurante.encuesta.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TipoPregunta {
    @Id
    @Column(name = "ID_TIPO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipo;
    @Column(name = "TIPO_PREGUNTA", nullable=false)
    private String tipoPregunta;
}
