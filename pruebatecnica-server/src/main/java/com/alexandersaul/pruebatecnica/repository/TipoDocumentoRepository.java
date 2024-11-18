package com.alexandersaul.pruebatecnica.repository;

import com.alexandersaul.pruebatecnica.entity.TipoContribuyente;
import com.alexandersaul.pruebatecnica.entity.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento , Integer> {
    @Query("SELECT td FROM TipoDocumento td WHERE td.estado = true")
    List<TipoDocumento> findActiveTipoDocumentos();
}
