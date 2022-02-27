package com.restaurante.encuesta.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class RespuestaMultiple {
    @Id
    @Column(name = "ID_RESPUESTA_MULT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRespuestaMultiple;
    @ManyToOne
    @JoinColumn(name = "ID_PREGUNTA", referencedColumnName = "ID_PREGUNTA")
    private Pregunta pregunta;
    @ManyToOne
    @JoinColumn(name = "ID_OPCION_MULTIPLE", referencedColumnName = "ID_OPCION_MULTIPLE")
    private OpcionMultiple opcionMultiple;
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    private Cliente cliente;

}
