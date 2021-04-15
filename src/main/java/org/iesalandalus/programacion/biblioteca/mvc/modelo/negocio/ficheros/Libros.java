package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.AudioLibro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.LibroEscrito;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.ILibros;

public class Libros implements ILibros {

	private static final String NOMBRE_FICHERO_LIBROS = "datos/libros.dat";

	private List<Libro> coleccionLibros;

	/** Constructor por defecto de la clase Libros */
	public Libros() {
		coleccionLibros = new ArrayList<>();
	}

	@Override
	public void comenzar() {
		leer();
	}

	private void leer() {
		// Referencio al archivo de libros
		File ficheroLibros = new File(NOMBRE_FICHERO_LIBROS);

		// Creo el búfer de lectura para el archivo
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroLibros))) {
			// Asigno a la variable de tipo Alumno la primera entrada de el fichero
			Libro libro = (Libro) entrada.readObject();

			while (libro != null) {
				// Primero inserto alumno en el array coleccionLibros y despues leo la
				// siguiente entrada de el fichero.
				insertar(libro);
				libro = (Libro) entrada.readObject();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se puede abrir el fichero" + NOMBRE_FICHERO_LIBROS);
		} catch (IOException e) {
			System.out.println("Error de E/S.");
		} catch (ClassNotFoundException e) {
			System.out.println("No se encuentra la clase.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void terminar() {
		escribir();
	}

	private void escribir() {
		// Referencio al archivo libros
		File ficheroLibros = new File(NOMBRE_FICHERO_LIBROS);

		// Creo el búfer de escritura para el archivo
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroLibros))) {
			// Para cada entrada de coleccionAlumnos, lo guardo en el fichero alumnos.
			for (Libro libro : coleccionLibros)
				salida.writeObject(libro);

			System.out.println("Se escribió el fichero libros correctamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el fichero");
		} catch (IOException e) {
			System.out.println("Error de E/S");
		}
	}

	/** Método para devolver una copia profunda de Libros */
	@Override
	public List<Libro> get() {
		List<Libro> copiaLibros = copiaProfundaLibros();
		copiaLibros.sort(Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor));

		return copiaLibros;
	}

	/** Método para realizar una copia profunda de Libros */
	private List<Libro> copiaProfundaLibros() {
		List<Libro> copiaLibros = new ArrayList<>();

		// Para todos los libros de coleccionLibros
		for (Libro libro : coleccionLibros) {
			// Si es instancia de LibroEscrito
			if (libro instanceof LibroEscrito)
				copiaLibros.add(new LibroEscrito((LibroEscrito) libro));

			// Si es instancia de AudioLibro
			if (libro instanceof AudioLibro)
				copiaLibros.add(new AudioLibro((AudioLibro) libro));
		}

		return copiaLibros;
	}

	/** Devuelve el tamaño de los libros ya insertados */
	@Override
	public int getTamano() {
		return coleccionLibros.size();
	}

	/** Método para insertar libros nuevos */
	@Override
	public void insertar(Libro libro) throws OperationNotSupportedException {
		if (libro == null)
			throw new NullPointerException("ERROR: No se puede insertar un libro nulo.");

		if (coleccionLibros.contains(libro))
			throw new OperationNotSupportedException("ERROR: Ya existe un libro con ese título y autor.");

		if (libro instanceof LibroEscrito)
			coleccionLibros.add(new LibroEscrito((LibroEscrito) libro));

		if (libro instanceof AudioLibro)
			coleccionLibros.add(new AudioLibro((AudioLibro) libro));
	}

	/** Método para buscar un libro */
	@Override
	public Libro buscar(Libro libro) {
		if (libro == null)
			throw new IllegalArgumentException("ERROR: No se puede buscar un libro nulo.");

		int indice = coleccionLibros.indexOf(libro);

		if (libro instanceof LibroEscrito)
			return (indice == -1) ? null : new LibroEscrito((LibroEscrito) coleccionLibros.get(indice));

		// Dejo este return fuera del if, ya que es igual que si pusiera else en el if
		// anterior, si no es libro escrito, es audiolibro
		return (indice == -1) ? null : new AudioLibro((AudioLibro) coleccionLibros.get(indice));
	}

	/** Método para borrar un libro. */
	@Override
	public void borrar(Libro libro) throws OperationNotSupportedException {
		if (libro == null)
			throw new IllegalArgumentException("ERROR: No se puede borrar un libro nulo.");

		if (!coleccionLibros.contains(libro))
			throw new OperationNotSupportedException("ERROR: No existe ningún libro con ese título y autor.");

		coleccionLibros.remove(libro);
	}
}
