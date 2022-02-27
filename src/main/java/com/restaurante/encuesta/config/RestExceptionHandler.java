package com.restaurante.encuesta.config;

import com.restaurante.encuesta.web.dto.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    ErrorDetail handleEntityNotFoundException(EntityNotFoundException  ex) {
        ErrorDetail errorDetail = new ErrorDetail();
        // llenar los detalles del error
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());

        errorDetail.setTitle("Recurso de BD no encontrado");
        errorDetail.setDetail("El elemento buscado no existe");
        return errorDetail;
    }
}
