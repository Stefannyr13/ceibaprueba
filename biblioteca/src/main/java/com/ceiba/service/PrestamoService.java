package com.ceiba.service;

import com.ceiba.dto.PrestamoDto;
import com.ceiba.dto.ResponseDto;
import com.ceiba.entity.TipoUsuario;
import com.ceiba.interfacee.AdministrarPrestamos;
import com.ceiba.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class PrestamoService implements AdministrarPrestamos {

    @Autowired
    private PrestamoRepositoryService prestamoRepService;

    @Override
    public ResponseDto realizarPrestamo(PrestamoDto prestamo)  {
        ResponseDto respuestaGenerada;
        if(prestamo.getTipoUsuario()== TipoUsuario.USUARIO_AFILIADO.getTipo()
                || prestamo.getTipoUsuario()== TipoUsuario.USUARIO_EMPLEADO.getTipo()
                || prestamo.getTipoUsuario()== TipoUsuario.USUARIO_INVITADO.getTipo() ) {

            Boolean invitadoTienePrestamos = validarPrestamosInvitado(prestamo);
            definirFechaMaximaParaPrestamo(prestamo);
            if(!invitadoTienePrestamos){
                PrestamoDto prestamoGuardado = prestamoRepService.realizarPrestamo(prestamo);
                respuestaGenerada = generarRespuestaExitosa(prestamoGuardado);
            } else  {
                respuestaGenerada = generarRespuestaFallida(prestamo.getIdentificacionUsuario(),
                        String.format(Utility.MENSAJE_FALLIDO_POR_USUARIO_INVITADO,prestamo.getIdentificacionUsuario() )  );
            }
        } else {
            respuestaGenerada = generarRespuestaFallida(null,
                    Utility.MENSAJE_FALLIDO_POR_USUARIO_INEXISTENTE);
        }

        return respuestaGenerada;
    }
    @Override
    public PrestamoDto consultarPrestamo (Long id){

        return prestamoRepService.consultarPrestamo(id);
    }

    private ResponseDto generarRespuestaFallida(String identificacionUsuario, String mensajeError) {

        return new ResponseDto(mensajeError);
    }

    private void definirFechaMaximaParaPrestamo(PrestamoDto prestamo) {
        LocalDate fechaMaxima = calcularFechaMaxima(prestamo.getTipoUsuario());
        prestamo.setFechaMaximaDevolucion(fechaMaxima);
    }

    private LocalDate calcularFechaMaxima(int tipoPrestamo) {
        LocalDate fechaMaximaDevolucion = LocalDate.now();
        switch (tipoPrestamo) {
            case Utility.USUARIO_AFILIADO:
                sumarDiasFechaPorTipoUsuario(fechaMaximaDevolucion, Utility.CANTIDAD_DIAS_POR_AFILIADO);
                break;
            case Utility.USUARIO_EMPLEADO:
                sumarDiasFechaPorTipoUsuario(fechaMaximaDevolucion, Utility.CANTIDAD_DIAS_POR_EMPLEADO);
                break;
            case Utility.USUARIO_INVITADO:
                sumarDiasFechaPorTipoUsuario(fechaMaximaDevolucion, Utility.CANTIDAD_DIAS_POR_INVITADO );
                break;
        }
        return fechaMaximaDevolucion;
    }

    private void sumarDiasFechaPorTipoUsuario(LocalDate fechaMaximaDevolucion, int cantidadDiasPorTipo) {
        int contadorDiasHabiles = 0;
        int descuentoDiasNoHabiles = 0;
        while(contadorDiasHabiles < cantidadDiasPorTipo){
            fechaMaximaDevolucion.plusDays(contadorDiasHabiles);
            if ((fechaMaximaDevolucion.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    fechaMaximaDevolucion.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                descuentoDiasNoHabiles++;
            }
            contadorDiasHabiles++;
        }
        fechaMaximaDevolucion.plusDays(descuentoDiasNoHabiles);
    }

    private ResponseDto generarRespuestaExitosa(PrestamoDto prestamoGuardado) {
        return  new ResponseDto(prestamoGuardado.getId() ,prestamoGuardado.getFechaMaximaDevolucion());
    }

    private Boolean validarPrestamosInvitado(PrestamoDto prestamo) {
        String identificacionUsuario = prestamo.getIdentificacionUsuario();
        if(prestamo.getTipoUsuario()== Utility.USUARIO_INVITADO){
            return  prestamoRepService.consultarPrestamosUsuario(identificacionUsuario);
        }
        return false;
    }


}
