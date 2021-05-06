package org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.controladores;


import javafx.scene.text.Font;

import org.iesalandalus.programacion.biblioteca.mvc.controlador.IControlador;
import org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class ControladorPrincipal {
	private IControlador controladorMVC;
	
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;		
	}
	
	@FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private void acercaDe(ActionEvent event) {

    }

    @FXML
    private void salir(ActionEvent event) {
    	if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?", null)) {
			controladorMVC.terminar();
			System.exit(0);
		}
    }

	

}
