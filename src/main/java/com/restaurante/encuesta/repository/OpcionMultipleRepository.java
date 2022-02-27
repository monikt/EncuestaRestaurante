package com.restaurante.encuesta.repository;

import com.restaurante.encuesta.model.OpcionMultiple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpcionMultipleRepository extends JpaRepository<OpcionMultiple, Integer> {
}
