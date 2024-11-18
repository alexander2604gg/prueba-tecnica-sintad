package com.alexandersaul.pruebatecnica.repository;

import com.alexandersaul.pruebatecnica.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol , Integer> {
}
