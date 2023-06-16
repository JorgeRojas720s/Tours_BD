/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.empresaturistica;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Itinerario;
import modelDAO.ItinerarioDAO;
import modelDTO.ItinerarioDTO;
/**
 * FXML Controller class
 *
 * @author jitor
 */
public class AdministrarItinerarioController implements Initializable {

    ItinerarioDAO itinerarioDAO = new ItinerarioDAO();

    @FXML
    private AnchorPane panelAdministrar;
    @FXML
    private Button BtnAdministrarVolver;
    @FXML
    private TextField txtIdItinerario;
    @FXML
    private TextField txtIdTour;
    @FXML
    private TextField txtLugar;
    @FXML
    private TextField txtDuracion;
    @FXML
    private Button BtnEliminar;
    @FXML
    private TableView<Itinerario> tbvListaDeItinerarios;
    @FXML
    private TableColumn<Itinerario, String> columID;
    @FXML
    private TableColumn<Itinerario, String> columLugar;
    @FXML
    private TextField txtHoraSalida;
    @FXML
    private TextField txtHoraLlegada;
    @FXML
    private TextArea txaDescpActividades;
    @FXML
    private Button BtnModificar;
    @FXML
    private Button BtnBuscar;
    @FXML
    private TextField txtFechaSalida;
    @FXML
    private TextField txtFechaRegreso;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        BtnModificar.setDisable(true);
        BtnEliminar.setDisable(true);
        
        llenarTabla();
        
        actualizarTablaItinerarios();
       
    }    
    
        
    private void mostrarAlerta(String message) {
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }  
    
    private void llenarTabla(){
        
        columID.setCellValueFactory(new PropertyValueFactory<>("itiIditinerario"));
        columLugar.setCellValueFactory(new PropertyValueFactory<>("itiLugar"));  
        
    }
    
    @FXML
    private void clickVolver(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void clickEliminar(ActionEvent event) throws IOException {
        
          String idItinerario = txtIdItinerario.getText();
          
          if(!idItinerario.isEmpty()){
              
              int id = Integer.parseInt(idItinerario);
              
              itinerarioDAO.eliminarItinerario(id);
              
              id = 0;
              
              mostrarAlerta("Eliminado Exitosamente");
              
              actualizarTablaItinerarios();
              
              App.setRoot("primary");   
          }else{
                mostrarAlerta("No se a ingresado un Itinerario");
          }
    }

    @FXML
    private void clickModificar(ActionEvent event) throws IOException {
        
        String idItinerario = txtIdItinerario.getText();
            
        if(!idItinerario.isEmpty()){

        String lugar = txtLugar.getText();
        String fecSalida = txtFechaSalida.getText();
        String fecRegreso = txtFechaRegreso.getText();
        String horaSalida = txtHoraSalida.getText();
        String horaRegreso = txtHoraLlegada.getText();
        String duracion = txtDuracion.getText();
        String descActividades = txaDescpActividades.getText();
 
        ItinerarioDTO itinerarioDTO = new ItinerarioDTO();
        //revisar sets
        
        itinerarioDTO.setItinerarioId(Integer.valueOf(idItinerario));
        itinerarioDTO.setItiLugar(lugar); 
        itinerarioDTO.setItiFechorasalida(Date.valueOf(fecSalida));
        itinerarioDTO.setItiFechorallegada(Date.valueOf(fecRegreso));
        itinerarioDTO.setItiHorasalida(horaSalida);
        itinerarioDTO.setItiHorallegada(horaRegreso);
        itinerarioDTO.setItiDuracion(duracion);
        itinerarioDTO.setItiDescpActividades(descActividades);
        
        itinerarioDAO.actualizarItinerario(itinerarioDTO);
        
        mostrarAlerta("Modificado Exitosamente");
        
        App.setRoot("primary");

        }else{
            
        mostrarAlerta("No a ingresado un Itinerario");   
        
        }
    
    }

    
    private void actualizarTablaItinerarios() {
        
        ItinerarioDAO itinerarioDAO1 = new ItinerarioDAO();
    
        List<Itinerario> listaItinerarios= itinerarioDAO1.obtenerItinerarios();
        
        itinerarioDAO1.closeConnection();
    
        ObservableList<Itinerario> itinerarioObservableList = FXCollections.observableArrayList(listaItinerarios);
        tbvListaDeItinerarios.setItems(itinerarioObservableList);
    } 
    
    @FXML
    private void obtenerIdItinerarios(MouseEvent event) {
          
        int index = tbvListaDeItinerarios.getSelectionModel().getFocusedIndex();
        Itinerario temp = tbvListaDeItinerarios.getItems().get(index);
        int id = itinerarioDAO.getItinerarioPorId(temp.getItiIditinerario().toString()).getItinerarioId();
 
        txtIdItinerario.setText(String.valueOf(id));
         
    }

    @FXML
    private void clickBuscar(ActionEvent event) {
        
        itinerarioDAO = new ItinerarioDAO();
        String idItinerario = txtIdItinerario.getText();
        System.out.println("jjjjjjjj:  "+idItinerario);
        if(!idItinerario.isEmpty()){
        BtnModificar.setDisable(false);
        BtnEliminar.setDisable(false);
        
            ItinerarioDTO itinerarioDTO = itinerarioDAO.getItinerarioPorId(idItinerario);
            
            if(itinerarioDTO != null){
                
                txtIdTour.setText(itinerarioDTO.getItiIdtours());
                txtLugar.setText(itinerarioDTO.getItiLugar());
                txtDuracion.setText(itinerarioDTO.getItiDuracion());
                txtHoraSalida.setText(itinerarioDTO.getItiHorasalida());
                txtFechaSalida.setText(itinerarioDTO.getItiFechorasalida().toString());
                txtFechaRegreso.setText(itinerarioDTO.getItiFechorallegada().toString());
                txtHoraLlegada.setText(itinerarioDTO.getItiHorallegada());
                txaDescpActividades.setText(itinerarioDTO.getItiDescpActividades());
                
            }
        }else{
            
             mostrarAlerta("No se encontró ningun Itinerario");
            
        }     
    }


}
