package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ISesiones;

public class Sesiones implements ISesiones {
	private List<Sesion> coleccionSesiones;
	
	public Sesiones(){
		coleccionSesiones=new ArrayList<>();
	}
	
	@Override
	public List<Sesion> get() {
		//Las sesiones se ordenarán por tutoría y por fecha.
		List<Sesion> sesionesOrdenadas = copiaProfundaSesiones();
		Comparator<Profesor> comparadorProfesor = Comparator.comparing(Profesor::getDni);
		Comparator<Tutoria> comparadorTutoria = Comparator.comparing(Tutoria::getProfesor, comparadorProfesor).thenComparing(Tutoria::getNombre);
		sesionesOrdenadas.sort(Comparator.comparing(Sesion::getTutoria,comparadorTutoria).thenComparing(Sesion::getFecha));
		return sesionesOrdenadas;
	}
	
	private List<Sesion> copiaProfundaSesiones() {
		List<Sesion> coleccionCopia= new ArrayList<>();
		for (Sesion sesion:coleccionSesiones) {
			coleccionCopia.add(new Sesion(sesion));
		}
		return coleccionCopia;
	}
	
	@Override
	public int getTamano() {
		return coleccionSesiones.size();
	}
	
	@Override
	public List<Sesion> get(Tutoria tutoria) {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: La tutoría no puede ser nula.");
		}
		
		List<Sesion> coleccionCopia= new ArrayList<>();
		
		for (Sesion	sesion:coleccionSesiones) {
			if(coleccionSesiones.get(coleccionSesiones.indexOf(sesion)).getTutoria().equals(tutoria)){
				coleccionCopia.add(new Sesion(sesion));
			}
		}
		//Cuando se listen las sesiones de una tutoría se mostrarán ordenadas por fecha de la sesión.
		coleccionCopia.sort(Comparator.comparing(Sesion::getFecha));
		return coleccionCopia;
	}

	@Override
	public void insertar(Sesion sesion) throws OperationNotSupportedException{
		if (sesion==null) {
			throw new NullPointerException("ERROR: No se puede insertar una sesión nula.");
		}
		if(buscar(sesion)!=null) {
			throw new OperationNotSupportedException("ERROR: Ya existe una sesión con esa fecha.");
		}
		coleccionSesiones.add(new Sesion(sesion));
		System.out.println("sesion introducido correctamente.");		
	}
	
	@Override
	public Sesion buscar(Sesion sesion) {
		if(sesion==null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una sesión nula.");
		}
		Sesion encontrado = null; //si no encuentra cita este método devuelve null.
		int i = coleccionSesiones.indexOf(sesion); //busca el método equals de Sesion para obtener el índice
		if (i != -1) {
			encontrado= new Sesion(coleccionSesiones.get(i));
		}
		
		return encontrado; //devuelvo una copia de la cita encontrada
	}

	@Override
	public void borrar(Sesion sesion) throws OperationNotSupportedException {
		if (sesion==null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una sesión nula.");
		}
		if (coleccionSesiones.contains(sesion)){ //Usa el método equals de sesion para buscarlo
			coleccionSesiones.remove(sesion); //Usa el método equals de sesion para borrarlo
			System.out.println("sesion borrado correctamente.");
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna sesión con esa fecha.");
		}
	}
}
