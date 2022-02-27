package com.restaurante.encuesta.repository;

import com.restaurante.encuesta.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante,Integer> {
}
