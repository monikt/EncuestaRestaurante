package com.restaurante.encuesta.web;

import com.restaurante.encuesta.model.Encuesta;
import com.restaurante.encuesta.model.Pregunta;
import com.restaurante.encuesta.model.RespuestaAbierta;
import com.restaurante.encuesta.model.RespuestaMultiple;
import com.restaurante.encuesta.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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
    List<RespuestaAbierta> listarRespuestaPreguntaA(@PathVariable Integer id) {
        return respuestaARepository.findByPregunta(preguntaRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @GetMapping("/getRespuestasMultiples/{id}")
    List<RespuestaMultiple> listarRespuestasPreguntaM(@PathVariable Integer id) {
        return respuestaMRepository.findByPregunta(preguntaRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @PostMapping(value = "/responderPreguntasMultiples/{idEncuesta}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    String registarRespuestasMultiples(@RequestBody List<RespuestaMultiple> respuestasMultiple,
                                     @PathVariable Integer idEncuesta) {

        List<Pregunta> preguntasRelacionadasE = encuestaRepository.findById(idEncuesta).orElseThrow(EntityNotFoundException::new).getPreguntas();
        long count =
                preguntasRelacionadasE.stream().filter(preguntab -> respuestasMultiple.stream().allMatch(respuestaM -> respuestaM.getPregunta().getId_pregunta().equals(preguntab.getId_pregunta()))).count();
        if (count > 0) {
            respuestasMultiple.forEach(respuestaMultiple -> {
                boolean YaRespondioPregunta = respuestaMRepository.findByPreguntaAndCliente(respuestaMultiple.getPregunta(), respuestaMultiple.getCliente()).isPresent();
                if (!YaRespondioPregunta) {
                    if (!clienteRepository.findById(respuestaMultiple.getCliente().getIdCliente()).isPresent())
                        clienteRepository.save(respuestaMultiple.getCliente());
                    respuestaMRepository.save(respuestaMultiple);
                }

            });
        }else {


        }


    }


}
