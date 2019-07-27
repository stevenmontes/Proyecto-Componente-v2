package com.proyecto.componentes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_PROYECTO")
public class Proyecto {
	@Id
	@Column(name = "CODIGO")
	private String codigo;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "VERSION")
	private String version;
	@OneToMany(mappedBy = "proyecto")
    private List<Funcionalidad> funcionalidades = new ArrayList<Funcionalidad>();
	public Proyecto() {
		super();
	}

	public Proyecto(String codigo, String nombre, String descripcion, String version) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.version = version;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Proyecto [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", version="
				+ version + "]";
	}

}
