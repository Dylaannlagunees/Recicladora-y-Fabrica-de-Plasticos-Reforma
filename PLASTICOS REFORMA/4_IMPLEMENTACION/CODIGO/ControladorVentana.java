package recicladora.reforma.view;

import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ControladorVentana extends AnchorPane {
	private HashMap<String, Node>Pantallas= new HashMap<String, Node>();
	public ControladorVentana(){
		super();
		
	}
	public void agregarPantalla(String opcion, Node pantallaFXML){
		Pantallas.put(opcion, pantallaFXML);
		
	}
	public Node obtenerPantalla(String opcion){
		return Pantallas.get(opcion);
	}
	public boolean cargarPantalla(String opcion, String ruta){
		try{
			//System.out.println(ruta);
			FXMLLoader miCargador= new FXMLLoader(getClass().getResource(ruta));
			AnchorPane ventanaCargada= (AnchorPane)miCargador.load();
			@SuppressWarnings("unused")
			IControladorVentana controladorVentana= ((IControladorVentana)miCargador.getController());
		//	controladorVentana.asignarVentanaPrincipal(this);
			agregarPantalla(opcion, ventanaCargada);
			
			return true;
		}
		catch (Exception e){
			System.out.println("opcion=>"+opcion+"<===>"+e.getMessage());
			return false;
		}
	}
	
	public boolean descargarPantalla(String opcion){
		if(Pantallas.remove(opcion)==null){
			System.out.println();
			return false;
			}
		else{
			return true;
			}
	}
	public boolean mostrarPantalla(final String opcion){
		if(Pantallas.get(opcion)!=null){
			final DoubleProperty opacidad=opacityProperty();
		    if(!getChildren().isEmpty()){
		    	Timeline tiempo= new Timeline(
		    	   new KeyFrame(Duration.ZERO, new KeyValue(opacidad, 1.0)),
		    	   new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>(){
		    		   public void handle(ActionEvent event){
		    			   getChildren().remove(0);
		    			   getChildren().add(0, Pantallas.get(opcion));
		    			   Timeline entrada=new Timeline(
		    					   new KeyFrame(Duration.ZERO, new KeyValue (opacidad, 0.0)),
		    					   new KeyFrame(new Duration(1000), new KeyValue(opacidad, 1.0)));
		    			   entrada.play();
		    		   }
		    	   }, new KeyValue(opacidad, 0.0)));
		    	tiempo.play();
		    }
		    else{
		    	setOpacity(0.0);
		    	getChildren().add(Pantallas.get(opcion));
		    	Timeline entrada=new Timeline(
		    			new KeyFrame(Duration.ZERO, new KeyValue(opacidad, 0.0)),
		    			new KeyFrame(new Duration(1000), new KeyValue(opacidad, 1.0)));
		    	entrada.play();
		    }
		    return true;
		   }
		else{
			System.out.println("No se pudo cargar el archivo");
			return false;
		}
	}
}