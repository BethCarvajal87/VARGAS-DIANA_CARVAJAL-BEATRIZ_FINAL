package com.backend.integrador.integradorClinica.repository;


import com.backend.integrador.integradorClinica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
}
