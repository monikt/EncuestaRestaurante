package com.restaurante.encuesta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Pregunta {

    @Id
    @Column(name = "ID_PREGUNTA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pregunta;
    @Column(name = "TEXTO_PREGUNTA",nullable=false)
    private String textoPregunta;
    /*FK*/
    @ManyToOne
    @JoinColumn(name = "ID_ENCUESTA", referencedColumnName = "ID_ENCUESTA")
    @JsonIgnore
    private Encuesta encuesta;
    @ManyToOne
    @JoinColumn(name = "ID_TIPO", referencedColumnName = "ID_TIPO")
    private TipoPregunta tipoPregunta;
    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL)
    private List<OpcionMultiple> opcionesMultiples;
    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL)
    private List<RespuestaAbierta>  respuestaAbiertas;
    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL)
    private List<RespuestaMultiple> respuestaMultiples;


}
