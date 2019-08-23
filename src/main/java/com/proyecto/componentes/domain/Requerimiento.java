package com.proyecto.componentes.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_REQUERIMIENTO")
public class Requerimiento {
	@Id
	@Column(name = "CODIGO")
	private String codigo;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@ManyToMany(mappedBy = "requerimientos")
	private Set<Funcionalidad> funcionalidades = new HashSet<>();

	public Requerimiento() {
		super();
	}

	public Requerimiento(String codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(Set<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

}
