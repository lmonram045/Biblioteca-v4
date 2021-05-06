package org.iesalandalus.programacion.biblioteca;

import org.iesalandalus.programacion.biblioteca.mvc.controlador.Controlador;
import org.iesalandalus.programacion.biblioteca.mvc.controlador.IControlador;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.IModelo;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.Modelo;
import org.iesalandalus.programacion.biblioteca.mvc.vista.FactoriaVista;
import org.iesalandalus.programacion.biblioteca.mvc.vista.IVista;
import org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.recursos.LocalizadorRecursos;

// Imports para javafx
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application{
	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			//Parent raiz = FXMLLoader.load(getClass().getResource("/panelPrincipal.fxml"));
			Parent raiz = FXMLLoader.load(LocalizadorRecursos.class.getResource("vista/principal.fxml"));
			//FXMLLoader cargadorVentanaPrincipal = new FXMLLoader(LocalizadorRecursos.class.getResource("panelPrincipal.fxml"));
			Scene escena = new Scene(raiz);
			escenarioPrincipal.setTitle("Gestion biblioteca");
			escenarioPrincipal.setScene(escena);
			escenarioPrincipal.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		IModelo modelo = new Modelo(FactoriaFuenteDatos.FICHEROS.crear());
		IVista vista = procesarArgumentosVista(args);
		IControlador controlador = new Controlador(modelo, vista);
		controlador.comenzar(); 
	}
	
	/** Clase para elegir la vista mediante argumentos */
	private static IVista procesarArgumentosVista(String[] args) {
		// Si se pasa de argumento "-vtexto" se pone en modo texto, si no, en modo gráfico.
		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-vtexto"))
				return FactoriaVista.TEXTO.crear();
		}
		
		return FactoriaVista.GRAFICA.crear();
	}

}
