package com.alexandersaul.pruebatecnica.repository;
import com.alexandersaul.pruebatecnica.entity.Entidad;
import com.alexandersaul.pruebatecnica.entity.TipoContribuyente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoContribuyenteRepository extends JpaRepository<TipoContribuyente , Integer> {
    @Query("SELECT tc FROM TipoContribuyente tc WHERE tc.estado = true")
    List<TipoContribuyente> findActiveTipoContribuyentes();
}
