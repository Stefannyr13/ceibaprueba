package com.ceiba.service;

import com.ceiba.dto.PrestamoDto;
import com.ceiba.entity.Prestamo;
import com.ceiba.entity.TipoUsuario;
import com.ceiba.repository.PrestamoRepositorio;
import com.ceiba.utility.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PrestamoRepositoryService {

    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    ModelMapper modelMapper = new ModelMapper();

    public PrestamoDto realizarPrestamo(PrestamoDto prestamoDto){
        Prestamo prestamo = modelMapper.map(prestamoDto, Prestamo.class);
        prestamo.setTipoUsuario(convertirTipoUsiario(prestamoDto.getTipoUsuario()));
        Prestamo prestamoRetorno =  prestamoRepositorio.save(prestamo);
        return modelMapper.map(prestamoRetorno, PrestamoDto.class);
    }
    public TipoUsuario convertirTipoUsiario(int tipo){

        TipoUsuario tipoU=null;
        if(tipo==TipoUsuario.USUARIO_AFILIADO.getTipo()){
            tipoU=TipoUsuario.USUARIO_AFILIADO;
        }else if(tipo==TipoUsuario.USUARIO_EMPLEADO.getTipo()){
            tipoU=TipoUsuario.USUARIO_EMPLEADO;
        }else if(tipo==TipoUsuario.USUARIO_INVITADO.getTipo()){
            tipoU=TipoUsuario.USUARIO_INVITADO;
        }

        return tipoU;
    }

    public boolean consultarPrestamosUsuario (String identificacionUsuario) {
        Optional<Prestamo> prestamoEncontrado =  prestamoRepositorio.
                encontrarPorIdentificacionUsuario(identificacionUsuario);
        return prestamoEncontrado.isPresent();
    }

    // creamos otro método para consultar prestamoRepositorio.findById(id)
    public PrestamoDto consultarPrestamo (Long id){
        Optional<Prestamo> prestamo= prestamoRepositorio.findById(id);
        return modelMapper.map(prestamo.get(),PrestamoDto.class);
    }
}
