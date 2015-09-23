package recicladora.reforma.controller;

import java.net.URL;
import java.util.ResourceBundle;

import recicladora.reforma.model.Empleado;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PrincipalController implements Initializable{
	@FXML private Label lblUser;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblUser.setText("");
	}

	public void asignarDatos(Empleado objeto){
		lblUser.setText(objeto.toString().trim());
	}
	
	public void limpiarDatos(){
		lblUser.setText("");
	}
}
