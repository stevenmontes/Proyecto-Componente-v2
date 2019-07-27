package com.proyecto.componentes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.componentes.domain.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, String>{

	Proyecto findByCodigo(String codigo);

}
