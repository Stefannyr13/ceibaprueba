package com.ceiba.repository;

import com.ceiba.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo,Long> {

    @Query("select p from Prestamo p where p.identificacionUsuario = :identificacionUsuario")
    Optional<Prestamo> encontrarPorIdentificacionUsuario(String identificacionUsuario);

    @Query("select p from Prestamo p where p.id = :id")
    Optional<Prestamo> encontrarPorId(Long id);
}
