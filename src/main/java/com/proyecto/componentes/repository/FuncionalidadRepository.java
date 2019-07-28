package com.proyecto.componentes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.componentes.domain.Funcionalidad;


public interface FuncionalidadRepository extends JpaRepository<Funcionalidad, String> {
	Funcionalidad findByCodigo(String codigo);

}
