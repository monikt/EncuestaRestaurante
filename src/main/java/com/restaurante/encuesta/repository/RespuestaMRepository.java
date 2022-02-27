package com.restaurante.encuesta.repository;

import com.restaurante.encuesta.model.Cliente;
import com.restaurante.encuesta.model.Pregunta;
import com.restaurante.encuesta.model.RespuestaMultiple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RespuestaMRepository extends JpaRepository<RespuestaMultiple,Integer> {
 List <RespuestaMultiple> findByPregunta(Pregunta p);
Optional <RespuestaMultiple> findByPreguntaAndCliente(Pregunta p, Cliente c);
}
