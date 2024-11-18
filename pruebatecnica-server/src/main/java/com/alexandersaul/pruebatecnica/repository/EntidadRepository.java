package com.alexandersaul.pruebatecnica.repository;

import com.alexandersaul.pruebatecnica.entity.Entidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EntidadRepository extends JpaRepository<Entidad, Integer> {
    @Query("SELECT e FROM Entidad e WHERE e.estado = true")
    Page<Entidad> findActiveEntidades(Pageable pageable);
    Optional<Entidad> findByNroDocumento(String nroDocumento);
}
