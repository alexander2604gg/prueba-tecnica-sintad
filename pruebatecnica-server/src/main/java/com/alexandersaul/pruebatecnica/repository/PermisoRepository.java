package com.alexandersaul.pruebatecnica.repository;

import com.alexandersaul.pruebatecnica.entity.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso , Integer> {
}
