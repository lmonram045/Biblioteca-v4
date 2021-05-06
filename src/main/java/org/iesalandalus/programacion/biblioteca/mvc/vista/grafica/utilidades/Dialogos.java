package org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.utilidades;

import org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.recursos.LocalizadorRecursos;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Dialogos {

	private static final String CSS = "estilos/estilos.css";
	private static final String ID_BT_ACEPTAR = "btAceptar";

	public static boolean mostrarDialogoConfirmacion(String titulo, String contenido, Stage propietario) {
		Alert dialogo = new Alert(AlertType.INFORMATION);
		dialogo.getDialogPane().getStylesheets().add(LocalizadorRecursos.class.getResource(CSS).toExternalForm());
		((Button) dialogo.getDialogPane().lookupButton(ButtonType.OK)).setId(ID_BT_ACEPTAR);
		dialogo.setTitle(titulo);
		dialogo.setHeaderText(null);
		dialogo.setContentText(contenido);
		if (propietario != null) {
			dialogo.initModality(Modality.APPLICATION_MODAL);
			dialogo.initOwner(propietario);
		}
		dialogo.showAndWait();
		if (propietario != null)
			propietario.close();
		return false;
	}

}
