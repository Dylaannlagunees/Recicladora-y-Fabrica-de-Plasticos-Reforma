package recicladora.reforma.controller;

import java.net.URL;
import java.util.ResourceBundle;

import recicladora.reforma.model.Empleado;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {

	@FXML private TextField txtUser;
	@FXML private TextField txtCode;
	@FXML private Button btnAccess;
	@FXML private Button btnClose;
	@FXML private Label lblMessage;
	public Empleado ActualEmployee=null;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblMessage.setText("");
	}
	
	public void Validar() throws Exception{
		try{
			if(this.txtUser.getText().trim().length()>0){
				if(this.txtCode.getText().trim().length()>0){					
						lblMessage.setText("");
						Empleado oEmpleado = new Empleado();
						oEmpleado.setUsuario(this.txtUser.getText());
						oEmpleado.setClave(this.txtCode.getText());
						ActualEmployee=oEmpleado.Validar();
						if(ActualEmployee==null){
							lblMessage.setText("Credenciales no validas.");
						}else{
							Stage stage = (Stage) btnClose.getScene().getWindow();
						    stage.hide();
						}
						oEmpleado=null;
				}else{
					throw new Exception("Favor de ingresar la clave.");
				}
			}else{
				throw new Exception("Favor de ingresar el usuario.");
			}		
		}catch(Exception e){
			lblMessage.setText(e.getMessage());
		}
	}
	
	public void Cerrar() throws Throwable{
		Stage stage = (Stage) btnClose.getScene().getWindow();
	    stage.close();
	}
}