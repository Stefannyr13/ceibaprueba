package com.ceiba.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.tomcat.jni.Local;

import javax.xml.ws.Response;
import java.time.LocalDate;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {


    private Long id;
    private LocalDate fechaMaximaDevolucion;
    private String mensaje;

    public ResponseDto(String mensaje) {
        this.mensaje = mensaje;
    }

    public ResponseDto(Long id, LocalDate fechaMaximaDevolucion) {
        this.id = id;
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }

    public void setFechaMaximaDevolucion(LocalDate fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }



    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
