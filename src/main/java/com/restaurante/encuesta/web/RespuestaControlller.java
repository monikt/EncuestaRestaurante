package com.restaurante.encuesta.web;

import com.restaurante.encuesta.model.*;
import com.restaurante.encuesta.repository.*;
import com.restaurante.encuesta.web.dto.ResultadoOperacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/respuesta")
public class RespuestaControlller {
    @Autowired
    RespuestaARepository respuestaARepository;
    @Autowired
    PreguntaRepository preguntaRepository;
    @Autowired
    RespuestaMRepository respuestaMRepository;
    @Autowired
    EncuestaRepository encuestaRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/getRespuestaAbiertas/{id}")
    List<RespuestaAbierta> listarRespuesta(@PathVariable Integer id) {
        return respuestaARepository.findByPregunta(preguntaRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @GetMapping("/getRespuestasMultiples/{id}")
    List<RespuestaMultiple> listarRespuestasPorEncuesta(@PathVariable Integer id) {
        return respuestaMRepository.respuestaMultiplesxEncuesta(id);
    }

    @PostMapping(value = "/responderPreguntasMultiples/{idEncuesta}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultadoOperacion registarRespuestasMultiples(@RequestBody List<RespuestaMultiple> respuestasMultiple,
                                                   @PathVariable Integer idEncuesta) {

        ResultadoOperacion resultadoOperacion = new ResultadoOperacion("Operación ejecutada correctamente", "se han guardado sus respuestas");
        List<Pregunta> preguntasRelacionadasE =encuestaRepository.findById(idEncuesta)
                .orElseThrow(EntityNotFoundException::new).
                getPreguntas().stream().filter(pregunta -> pregunta.getTipoPregunta().getTipoPregunta().equals("SELEC_MULTIPLE")).collect(Collectors.toList());
        long count =
                preguntasRelacionadasE.stream().
                        filter(preguntab ->
                                respuestasMultiple.stream()
                                        .anyMatch(respuestaM -> respuestaM.getPregunta().getId_pregunta().equals(preguntab.getId_pregunta()))).count();
        if (count == preguntasRelacionadasE.size()) {
            int contadorRegistro = 0;
            for (RespuestaMultiple respuestaMultiple : respuestasMultiple) {
                Cliente cliente = clienteRepository.findByCorreo(respuestaMultiple.getCliente().getCorreo());
                if (cliente == null) {
                    Cliente clienteSaved = clienteRepository.save(respuestaMultiple.getCliente());
                    respuestaMultiple.setCliente(clienteSaved);
                    respuestaMRepository.save(respuestaMultiple);
                    contadorRegistro++;
                } else {
                    if (respuestaMRepository.findByPreguntaAndCliente(respuestaMultiple.getPregunta(), cliente).isEmpty()) {
                        System.out.println("entra bandera -->" + respuestaMultiple.getPregunta());
                        respuestaMultiple.setCliente(cliente);
                        respuestaMRepository.save(respuestaMultiple);
                        contadorRegistro++;
                    }
                }
            }
            if (contadorRegistro == 0)
                resultadoOperacion = new ResultadoOperacion("Operación Ivalida", "El cliente ya respondio la encuesta");
        } else {
            resultadoOperacion = new ResultadoOperacion("Operación Invalida", "Las preguntas deben pertenecer a la encuesta que desea responder");
        }
        return resultadoOperacion;

    }


}
