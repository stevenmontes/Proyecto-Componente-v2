package com.proyecto.componentes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.componentes.domain.Requerimiento;

public interface RequerimientoRepository extends JpaRepository<Requerimiento, String>{
	
	@Query("select r from Requerimiento r, Funcionalidad f"
			+ " where f.codigo = (?1)")
	List<Requerimiento> getAllByFuncionalidad(String codigo);

	Requerimiento findByCodigo(String codigo);
	
}
