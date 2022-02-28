package com.restaurante.encuesta.repository;

import com.restaurante.encuesta.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByCorreo(String correo);
}
