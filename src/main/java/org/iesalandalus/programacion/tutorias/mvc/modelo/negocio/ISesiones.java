package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public interface ISesiones {

	List<Sesion> get();

	int getTamano();

	List<Sesion> get(Tutoria tutoria);

	void insertar(Sesion sesion) throws OperationNotSupportedException;

	Sesion buscar(Sesion sesion);

	void borrar(Sesion sesion) throws OperationNotSupportedException;

}