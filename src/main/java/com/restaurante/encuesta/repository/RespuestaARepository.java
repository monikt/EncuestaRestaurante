package com.restaurante.encuesta.repository;

import com.restaurante.encuesta.model.Cliente;
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
     Optional <RespuestaAbierta> findByPreguntaAndCliente(Pregunta p, Cliente c);
     @Query(nativeQuery = false, value = "select p from RespuestaAbierta p inner join p.pregunta pr where pr.encuesta.id_encuesta = :id")
     List<RespuestaAbierta> respuestAbiertaxEncuesta(@Param("id") Integer id);

}
