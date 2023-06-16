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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Reserva;
import modelDAO.ReservaDAO;
import modelDTO.ReservaDTO;
/**
 * FXML Controller class
 *
 * @author jitor
 */
public class AdministrarReservaController implements Initializable {
    
    
    ReservaDAO reservaDAO = new ReservaDAO();


    @FXML
    private AnchorPane panelRegistrar;
    @FXML
    private Button BtnRegistrarVolver;
    @FXML
    private TextField txtIdCliente;
    @FXML
    private TextField txtIdReserva;
    @FXML
    private TextField txtIdTour;
    @FXML
    private TextField txtCantPersonas;
    @FXML
    private TextField txtCosto;
    @FXML
    private TextField txtMontAbonado;
    @FXML
    private Button BtnModificar;
    @FXML
    private TextField txtFecReserva;
    @FXML
    private Button BtnEliminar;
    @FXML
    private Button btnBuscar;
    @FXML
    private TableView<Reserva> tbvListaDeReservas;
    @FXML
    private TableColumn<Reserva, String> columIdReserva;
    @FXML
    private TableColumn<Reserva, String> columFecha;
    @FXML
    private TableColumn<Reserva, String> columPrecio;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        BtnEliminar.setDisable(true);
        BtnModificar.setDisable(true);
        
        llenarTabla();
        
        actualizarTablaReservas();
        
    }    
    
    private void mostrarAlerta(String message) {
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }  
    
    private void llenarTabla(){
        
        columIdReserva.setCellValueFactory(new PropertyValueFactory<>("resIdreserva"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("resCosto"));  
        columFecha.setCellValueFactory(new PropertyValueFactory<>("resFecreserva"));  
    }
    
    @FXML
    private void clickVolver(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    
    
    @FXML
    private void clickBuscar(ActionEvent event) {
        
        reservaDAO = new ReservaDAO();
        String idReserva = txtIdReserva.getText();
        
        if(!idReserva.isEmpty()){
            BtnEliminar.setDisable(false);
            BtnModificar.setDisable(false);
            ReservaDTO reservaDTO = reservaDAO.getReservaPorId(idReserva);
            if(reservaDTO != null){
                
            txtIdCliente.setText(reservaDTO.getResIdCliente());
            txtIdTour.setText(reservaDTO.getResIdtour());
            txtCantPersonas.setText(reservaDTO.getResCantpersonas());
            txtCosto.setText(reservaDTO.getResCosto());
            txtMontAbonado.setText(reservaDTO.getResMontosabonados());
             txtFecReserva.setText(reservaDTO.getResFecreserva().toString());
            }
        }else{
            mostrarAlerta("No se encontró ninguna Reserva"); 
        }   
    }

    @FXML
    private void clickModificar(ActionEvent event) throws IOException {
        
        String idReserva = txtIdReserva.getText();
            
        if(!idReserva.isEmpty()){

        String fecReserva = txtFecReserva.getText();
        String costo = txtCosto.getText();
        String montAbonado = txtMontAbonado.getText();
        String cantPersonas = txtCantPersonas.getText();
        String idTour = txtIdTour.getText();
        String idCliente = txtIdCliente.getText();

        ReservaDTO reservaDTO = new ReservaDTO();
        
        reservaDTO.setReservaId(Integer.valueOf(idReserva)); 
        reservaDTO.setResIdtour(idTour);
        reservaDTO.setResIdCliente(idCliente);
        reservaDTO.setResFecreserva(Date.valueOf(fecReserva));
        reservaDTO.setResCosto(costo);
        reservaDTO.setResMontosabonados(montAbonado);
        reservaDTO.setResCantpersonas(cantPersonas);
        
        
        reservaDAO.actualizarReserva(reservaDTO);
        
        mostrarAlerta("Modificado Exitosamente");
        
        App.setRoot("primary");

        }else{
        mostrarAlerta("No a ingresado una reserva");   
        }
    }

    @FXML
    private void clickEliminar(ActionEvent event) throws IOException {
        
        String idReserva = txtIdReserva.getText();
        
        if(!idReserva.isEmpty()){
            
        int id = Integer.parseInt(idReserva);

        reservaDAO.eliminarReserva(id);
        
        id = 0;
        
       // limpiar();
        
        mostrarAlerta("Eliminado Exitosamente");
        
        actualizarTablaReservas();
        
        App.setRoot("primary");
 
        }else{
             mostrarAlerta("No se a ingresado una Reserva");
        }          
    }

    private void actualizarTablaReservas() {
        
        ReservaDAO reservaDAO1 = new ReservaDAO();
    
        List<Reserva> listaReservas = reservaDAO1.obtenerReservas();
        
        reservaDAO1.closeConnection();
    
        ObservableList<Reserva> reservaeObservableList = FXCollections.observableArrayList(listaReservas);
        tbvListaDeReservas.setItems(reservaeObservableList);
    }
    
    @FXML
    private void obtenerIdCliente(MouseEvent event) {
        
        int index = tbvListaDeReservas.getSelectionModel().getFocusedIndex();
        Reserva temp = tbvListaDeReservas.getItems().get(index);
        int id = reservaDAO.getReservaPorId(temp.getResIdreserva().toString()).getReservaId();
        
        txtIdReserva.setText(String.valueOf(id));
  
    } 


}
