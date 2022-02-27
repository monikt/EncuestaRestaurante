package com.restaurante.encuesta.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class RespuestaAbierta {

    @Id
    @Column(name = "ID_RESPUESTA_AB")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRespuestaAbierta;
    @Column(name = "TEXTO_RESPUESTA",nullable=false)
    private String textoRespuesta;
    @ManyToOne
    @JoinColumn(name = "ID_PREGUNTA", referencedColumnName = "ID_PREGUNTA")
     private Pregunta pregunta;
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    private Cliente cliente;

}
