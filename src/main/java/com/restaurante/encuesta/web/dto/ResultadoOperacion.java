package com.restaurante.encuesta.web.dto;

import lombok.Data;

@Data
public class ResultadoOperacion {
    private int status;
    private String title;
    private String detail;

    public ResultadoOperacion(String title,String detail) {
        this.detail = detail;
        this.title = title;

    }
}
