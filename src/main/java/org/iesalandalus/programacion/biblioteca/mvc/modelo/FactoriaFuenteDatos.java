package org.iesalandalus.programacion.biblioteca.mvc.modelo;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.ficheros.FactoriaFuenteDatosFicheros;

public enum FactoriaFuenteDatos {
	FICHEROS {
		public IFuenteDatos crear() {
			return new FactoriaFuenteDatosFicheros();
		}
	};

	public abstract IFuenteDatos crear();

}
