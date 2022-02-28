package com.restaurante.encuesta.repository;

import com.restaurante.encuesta.model.Pregunta;
import com.restaurante.encuesta.model.RespuestaAbierta;
import com.restaurante.encuesta.model.RespuestaMultiple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RespuestaARepository  extends JpaRepository<RespuestaAbierta, Integer> {
     List<RespuestaAbierta> findByPregunta(Pregunta pregunta);

}
