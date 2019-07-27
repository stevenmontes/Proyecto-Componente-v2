package com.proyecto.componentes.domain;

public class Respuesta {
	public String mensaje;
	public boolean estado;
	public Object info;

	public Respuesta() {
		super();
	}

	public Respuesta(String mensaje, boolean estado, Object info) {
		super();
		this.mensaje = mensaje;
		this.estado = estado;
		this.info = info;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public void Correcto(String pMensaje) {
		this.mensaje = pMensaje;
		this.estado = true;
	}

	public void Correcto(String pMensaje, Object pInfo) {
		this.mensaje = pMensaje;
		this.info = pInfo;
		this.estado = true;
	}

	public void Error(String pMensaje) {
		this.mensaje = pMensaje;
	}
}
