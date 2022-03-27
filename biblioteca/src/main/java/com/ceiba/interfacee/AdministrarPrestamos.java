package com.ceiba.interfacee;

import com.ceiba.dto.PrestamoDto;
import com.ceiba.dto.ResponseDto;


public interface AdministrarPrestamos {

    ResponseDto realizarPrestamo(PrestamoDto prestamo);

    PrestamoDto consultarPrestamo(Long id);

}
