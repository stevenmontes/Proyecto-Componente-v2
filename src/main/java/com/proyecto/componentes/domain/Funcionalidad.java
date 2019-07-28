package com.proyecto.componentes.domain;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_FUNCIONALIDAD")
public class Funcionalidad {
	@Id
	@Column(name = "CODIGO")
	private String codigo;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "PRIORIDAD")
	private int prioridad;
	@ManyToOne
	@JoinColumn(name = "ID_PROYECTO")
	private Proyecto proyecto;

	@Column(name = "ESTADO")
	private boolean estado;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "TBL_REQUERIMIENTOxFUNCIONALIDAD", joinColumns = @JoinColumn(name = "ID_REQUERIMIENTO", referencedColumnName = "CODIGO"), inverseJoinColumns = @JoinColumn(name = "ID_FUNCIONALIDAD", referencedColumnName = "CODIGO"))
	private Set<Requerimiento> requerimientos;

	public Funcionalidad(String codigo, String nombre, String descripcion, int prioridad, Proyecto proyecto,
			Requerimiento nReq) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
		this.proyecto = proyecto;
		this.requerimientos = Stream.of(nReq).collect(Collectors.toSet());
		this.requerimientos.forEach(x -> x.getFuncionalidades().add(this));
	}

	public Funcionalidad() {

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

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Set<Requerimiento> getRequerimientos() {
		return requerimientos;
	}

	public void setRequerimientos(Set<Requerimiento> requerimientos) {
		this.requerimientos = requerimientos;
	}

	@Override
	public String toString() {
		return "Funcionalidad [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", prioridad=" + prioridad + ", proyecto=" + proyecto + ", estado=" + estado + "]";
	}

}
