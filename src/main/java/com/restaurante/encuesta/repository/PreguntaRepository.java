package com.restaurante.encuesta.repository;

import com.restaurante.encuesta.model.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntaRepository  extends JpaRepository<Pregunta, Integer> {
}
