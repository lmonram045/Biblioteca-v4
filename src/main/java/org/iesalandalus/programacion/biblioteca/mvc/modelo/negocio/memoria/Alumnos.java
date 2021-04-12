package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.IAlumnos;

public class Alumnos implements IAlumnos {

	private List<Alumno> coleccionAlumnos;

	/** Constructor por defecto de la clase Alumnos */
	public Alumnos() {
		coleccionAlumnos = new ArrayList<>();
	}

	/** Método para obtener todos los alumnos ordenados por nombre */
	@Override
	public List<Alumno> get() {
		List<Alumno> copiaAlumnos = copiaProfundaAlumnos();
		copiaAlumnos.sort(Comparator.comparing(Alumno::getNombre));

		return copiaAlumnos;
	}

	/**
	 * Método para realizar copia profunda con referencias distintas de los alumnos
	 */
	private List<Alumno> copiaProfundaAlumnos() {
		List<Alumno> copiaAlumnos = new ArrayList<>();

		for (Alumno alumno : coleccionAlumnos)
			copiaAlumnos.add(new Alumno(alumno));

		return copiaAlumnos;
	}

	@Override
	public int getTamano() {
		return coleccionAlumnos.size();
	}

	/** Método para insertar un alumno nuevo */
	@Override
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null)
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");

		if (coleccionAlumnos.contains(alumno))
			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese correo.");

		coleccionAlumnos.add(new Alumno(alumno));
	}

	/** Método para buscar un alumno */
	@Override
	public Alumno buscar(Alumno alumno) {
		if (alumno == null)
			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");

		int indice = coleccionAlumnos.indexOf(alumno);
		alumno = (indice == -1) ? null : new Alumno(coleccionAlumnos.get(indice));

		return alumno;
	}

	/** Método para borrar un alumno */
	@Override
	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null)
			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");

		if (!coleccionAlumnos.contains(alumno))
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese correo.");

		coleccionAlumnos.remove(alumno);
	}

}
