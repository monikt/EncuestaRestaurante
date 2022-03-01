package com.restaurante.encuesta.web;

import com.restaurante.encuesta.model.*;
import com.restaurante.encuesta.repository.*;
import com.restaurante.encuesta.web.dto.ErrorDetail;
import com.restaurante.encuesta.web.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/respuesta")
public class RespuestaControlller {

    Logger logger = LoggerFactory.getLogger(RespuestaControlller.class);
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
        return respuestaARepository.respuestAbiertaxEncuesta(id);
    }

    @GetMapping("/getRespuestasMultiples/{id}")
    List<RespuestaMultiple> listarRespuestasPorEncuesta(@PathVariable Integer id) {
        return respuestaMRepository.respuestaMultiplesxEncuesta(id);
    }

    @PostMapping(value = "/responderPreguntas/{idEncuesta}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ErrorDetail registarEncuesta(@RequestBody List<Pregunta> preguntas,
                          @PathVariable Integer idEncuesta) {

        ErrorDetail errorDetail = null;
        List<ValidationError> listaErrores = new ArrayList<>();
        logger.debug("Inicio de registro respuestas multiples ....");
        /*Se obtienen las preguntas que son de tipo select multiple para comparar que sean las mismas que se reciben como parametro*/
        List<Pregunta> preguntasRelacionadasE = encuestaRepository.findById(idEncuesta)
                .orElseThrow(EntityNotFoundException::new).
                getPreguntas();
        /* se Filtra cuales preguntas recibidas como parametro corresponde a las de bd de datos, esto valida que se respondan todas las preguntas*/
        try {
            long count =
                    preguntasRelacionadasE.stream().
                            filter(preguntab ->
                                    preguntas.stream()
                                            .anyMatch(p -> p.getId_pregunta().equals(preguntab.getId_pregunta()))).count();
            logger.debug("Numero de preguntas relacionadas en json vs la encuesta enviada " + count);
            if (count == preguntas.size()) {
                boolean insertoRespuesta = false;
                for (Pregunta pregunta : preguntas) {
                    for (RespuestaAbierta respuestaAbierta: pregunta.getRespuestaAbiertas()) {
                          Cliente cl =  clienteRepository.findByCorreo(respuestaAbierta.getCliente().getCorreo());
                          if (cl == null) {
                              Cliente clienteSaved = clienteRepository.save(respuestaAbierta.getCliente());
                              respuestaAbierta.setCliente(clienteSaved);
                              respuestaAbierta.setPregunta(pregunta);
                              respuestaARepository.save(respuestaAbierta);
                              insertoRespuesta = true;
                          }else if(respuestaARepository.findByPreguntaAndCliente(pregunta,cl).isEmpty()){
                                respuestaAbierta.setCliente(cl);
                                respuestaAbierta.setPregunta(pregunta);
                                RespuestaAbierta ra =  respuestaARepository.save(respuestaAbierta);
                                insertoRespuesta = true;
                          }
                      }


                    for (RespuestaMultiple respuestaMultiple: pregunta.getRespuestaMultiples()) {
                       Cliente cl =  clienteRepository.findByCorreo(respuestaMultiple.getCliente().getCorreo());
                       if (cl == null) {
                           Cliente clienteSaved = clienteRepository.save(respuestaMultiple.getCliente());
                           respuestaMultiple.setCliente(clienteSaved);
                           respuestaMultiple.setPregunta(pregunta);
                           respuestaMRepository.save(respuestaMultiple);
                           insertoRespuesta = true;
                       }else if(respuestaMRepository.findByPreguntaAndClienteAndOpcionMultiple_IdOpcionMultiple(pregunta,cl,respuestaMultiple.getOpcionMultiple().getIdOpcionMultiple()).isEmpty()){
                           respuestaMultiple.setCliente(cl);
                           respuestaMultiple.setPregunta(pregunta);
                           respuestaMRepository.save(respuestaMultiple);
                           insertoRespuesta = true;
                       }

                    }
                }
                errorDetail = (insertoRespuesta) ? new ErrorDetail(new Date().getTime(), 1, "Respuestas Registradas", "Se registro de manera correcta las respuestas") :
                        new ErrorDetail(new Date().getTime(), 0, "Respuestas invalidas", "El cliente enviado ya respondio la encuesta");


             }else {
                errorDetail = new ErrorDetail(new Date().getTime(),0,"Preguntas invalidas","El número de preguntas no corresponde a las mismas asociadas a la encuesta");
            }
        } catch (Exception exception) {
            errorDetail = new ErrorDetail(new Date().getTime(),0,"Error registrando la encuesta","se presento un error registrando las preguntas ... intente mas tarde");
            logger.error("Se presento un error diligenciando los datos de la encuesta error: ", exception);
        }

        return  errorDetail;
    }

}
