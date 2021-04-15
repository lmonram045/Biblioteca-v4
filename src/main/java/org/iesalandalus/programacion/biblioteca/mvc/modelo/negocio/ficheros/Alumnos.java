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

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.IAlumnos;

public class Alumnos implements IAlumnos {
	
	private static final String NOMBRE_FICHERO_ALUMNOS = "datos/alumnos.dat";

	private List<Alumno> coleccionAlumnos;

	/** Constructor por defecto de la clase Alumnos */
	public Alumnos() {
		coleccionAlumnos = new ArrayList<>();
	}
	
	@Override
	public void comenzar() {
		leer();
	}
	
	/** Método para leer de un archivo */
	private void leer() {
		// Referencio al archivo de alumnos
		File ficheroAlumnos = new File(NOMBRE_FICHERO_ALUMNOS);
		
		// Compruebo si existe el directorio
		File directorioDatos = new File("datos");
		if (!directorioDatos.exists())
			directorioDatos.mkdir();
		
		/*if (!ficheroAlumnos.exists())
			try {
				ficheroAlumnos.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
		
		// Creo el búfer de lectura para el archivo
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroAlumnos))) {
			// Asigno a la variable de tipo Alumno la primera entrada de el fichero
			Alumno alumno = (Alumno) entrada.readObject();
			
			while (alumno != null) {
				// Primero inserto alumno en el array coleccionAlumnos y despues leo la siguiente entrada de el fichero.
				insertar(alumno);
				alumno = (Alumno)entrada.readObject();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se puede abrir el fichero" + NOMBRE_FICHERO_ALUMNOS);
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
	
	/** Método para escribir en un fichero */
	private void escribir() {
		// Referencio al archivo alumnos
		File ficheroAlumnos = new File(NOMBRE_FICHERO_ALUMNOS);
		
		//Creo el búfer de escritura para el archivo
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroAlumnos))) {
			// Para cada entrada de coleccionAlumnos, lo guardo en el fichero alumnos.
			for (Alumno alumno : coleccionAlumnos)
				salida.writeObject(alumno);
			
			System.out.println("Se escribió el fichero alumnos correctamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el fichero");
		} catch (IOException e) {
			System.out.println("Error de E/S");
		}
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
