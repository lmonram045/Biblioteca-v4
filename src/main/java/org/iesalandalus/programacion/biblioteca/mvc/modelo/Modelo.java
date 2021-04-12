package org.iesalandalus.programacion.biblioteca.mvc.modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Curso;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.ILibros;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.IPrestamos;

public class Modelo implements IModelo {

	private IAlumnos alumnos;
	private IPrestamos prestamos;
	private ILibros libros;

	/** Constructor por defecto */
	public Modelo(IFuenteDatos fuenteDatos) {
		alumnos = fuenteDatos.crearAlumnos();
		prestamos = fuenteDatos.crearPrestamos();
		libros = fuenteDatos.crearLibros();
	}

	/** Método para insertar un alumno */
	@Override
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.insertar(alumno);
	}

	/** Método para insertar un libro */
	@Override
	public void insertar(Libro libro) throws OperationNotSupportedException {
		libros.insertar(libro);
	}

	/** Método para realizar un préstamo */
	@Override
	public void prestar(Prestamo prestamo) throws OperationNotSupportedException {
		if (prestamo == null)
			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");

		if (alumnos.buscar(prestamo.getAlumno()) == null)
			throw new OperationNotSupportedException("ERROR: No existe el alumno del préstamo.");

		if (libros.buscar(prestamo.getLibro()) == null)
			throw new OperationNotSupportedException("ERROR: No existe el libro del préstamo.");

		prestamos.prestar(prestamo);
	}
	
	/** Método para realizar una devolución */
	@Override
	public void devolver(Prestamo prestamo, LocalDate fecha) throws OperationNotSupportedException {
		if (prestamos.buscar(prestamo) == null)
			throw new OperationNotSupportedException("ERROR: No se puede devolver un préstamo no prestado.");

		prestamos.devolver(prestamo, fecha);
	}
	
	/** Método para buscar un alumno */
	@Override
	public Alumno buscar(Alumno alumno) {
		return alumnos.buscar(alumno);
	}
	
	/** Método para buscar un libro */
	@Override
	public Libro buscar(Libro libro) {
		return libros.buscar(libro);
	}
	
	/** Método para buscar un préstamo */
	@Override
	public Prestamo buscar(Prestamo prestamo) {
		return prestamos.buscar(prestamo);
	}
	
	/** Método para borrar un alumno */
	@Override
	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		// Para cada préstamo de este alumno, borrarlo.
		List<Prestamo> prestamosAlumno = prestamos.get(alumno);
		for (Prestamo prestamo : prestamosAlumno)
			prestamos.borrar(prestamo);
		
		// Una vez borrados los préstamos de ese alumno, borrar el alumno.
		alumnos.borrar(alumno);
	}
	
	/** Método para borrar un libro */
	@Override
	public void borrar(Libro libro) throws OperationNotSupportedException {
		// Para cada préstamo de este libro, borrarlo.
		List<Prestamo> prestamosLibro = prestamos.get(libro);
		for (Prestamo prestamo : prestamosLibro)
			prestamos.borrar(prestamo);
		
		// Una vez borrados los préstamos, borrar libro
		libros.borrar(libro);
	}
	
	/** Método para borrar un préstamo */
	@Override
	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {
		prestamos.borrar(prestamo);
	}
	
	/** Método para obtener una copia profunda de los alumnos */
	@Override
	public List<Alumno> getAlumnos() {
		return alumnos.get();
	}
	
	/** Método para obtener una copia profunda de los libros */
	@Override
	public List<Libro> getLibros() {
		return libros.get();
	}
	
	/** Método para obtener una copia profunda de los préstamos */
	@Override
	public List<Prestamo> getPrestamos() {
		return prestamos.get();
	}
	
	/** Método para obtener una copia profunda de los préstamos de un alumno */
	@Override
	public List<Prestamo> getPrestamos(Alumno alumno) {
		return prestamos.get(alumno);
	}
	
	/** Método para obtener una copia profunda de los préstamos de un libro */
	@Override
	public List<Prestamo> getPrestamos(Libro libro) {
		return prestamos.get(libro);
	}
	
	/** Método para obtener una copia profunda de los préstamos realizados en una fecha concreta */
	@Override
	public List<Prestamo> getPrestamos(LocalDate fecha) {
		return prestamos.get(fecha);
	}
	
	/** Método para obtener una copia profunda de los préstamos realizados en un mes ordenados por curso */
	@Override
	public Map<Curso, Integer> getEstadisticaMensualPorCurso(LocalDate mes) {
		return prestamos.getEstadisticaMensualPorCurso(mes);
	}
}