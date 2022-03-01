package com.restaurante.encuesta.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class OpcionMultiple {
    @Id
    @Column(name = "ID_OPCION_MULTIPLE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOpcionMultiple;
    @Column(name = "TEXTO_OPCION_MUL",nullable=false)
    private String textoOpcionMultiple;
    @Column(name = "LETRA_OPCION_MULTIPLE",nullable=false)
    private String letra;

    @PrePersist
    private void asignarLetra() {
        textoOpcionMultiple = letra + ". "+ textoOpcionMultiple;
    }

    @ManyToOne
    @JoinColumn(name = "ID_PREGUNTA", referencedColumnName = "ID_PREGUNTA")
    @JsonIgnore
    private Pregunta pregunta;
    @OneToMany (mappedBy = "opcionMultiple", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<RespuestaMultiple> respuestaMultiples;

}
