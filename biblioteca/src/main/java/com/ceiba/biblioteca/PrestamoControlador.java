package com.ceiba.biblioteca;


import com.ceiba.dto.PrestamoDto;
import com.ceiba.dto.ResponseDto;
import com.ceiba.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PrestamoControlador {

    @Autowired
    private PrestamoService administrarPrestamo;

    @PostMapping("/prestamos")
    public ResponseEntity<ResponseDto> create(@RequestBody PrestamoDto prestamo) throws Exception {
            ResponseDto respuestaGenerada = administrarPrestamo.realizarPrestamo(prestamo);
            return  respuestaGenerada.getMensaje() == null ? new ResponseEntity<>(respuestaGenerada,HttpStatus.OK) :
                    new ResponseEntity<>(respuestaGenerada, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/prestamos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PrestamoDto consultar (@PathVariable String id){
        Long idPrestamo=Long.parseLong(id);
        PrestamoDto prestamo = administrarPrestamo.consultarPrestamo(idPrestamo);
        return  prestamo;
    }




}

