package com.restaurante.encuesta.web;

import ch.qos.logback.core.net.server.Client;
import com.restaurante.encuesta.model.*;
import com.restaurante.encuesta.repository.ClienteRepository;
import com.restaurante.encuesta.repository.EncuestaRepository;
import com.restaurante.encuesta.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/encuesta")
public class EncuestaController {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private EncuestaRepository encuestaRepository;


    @GetMapping("/getRestaurantes")
    List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/getEncuestas")
    List<Encuesta> listarEncuestas() {
        List<Encuesta> encuestas = encuestaRepository.findAll();
        encuestas.forEach(encuesta -> encuesta.getPreguntas().forEach(pregunta -> {
                            pregunta.setOpcionesMultiples(pregunta.getOpcionesMultiples().stream()
                                    .sorted((o1, o2) -> o1.getLetra().compareTo(o2.getLetra())).collect(Collectors.toList()));
                            pregunta.setRespuestaAbiertas(new ArrayList<>());
                            pregunta.setRespuestaMultiples(new ArrayList<>());
                        }

                )

        );
        return encuestas;
    }

    @GetMapping("/getEncuesta/{id}")
    Encuesta listarEncuesta(@PathVariable Integer id) {
        Encuesta encuesta = encuestaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        encuesta.getPreguntas().forEach(pregunta -> {
                    pregunta.setOpcionesMultiples(pregunta.getOpcionesMultiples().stream()
                            .sorted((o1, o2) -> o1.getLetra().compareTo(o2.getLetra())).collect(Collectors.toList()));
                    pregunta.setRespuestaAbiertas(new ArrayList<>());
                    pregunta.setRespuestaMultiples(new ArrayList<>());
                }
        );

        return encuesta;
    }


}