/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.empresaturistica;

import java.io.IOException;
import java.math.BigDecimal;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Cliente;
import model.Reserva;
import model.Tour;
import modelDAO.ClienteDAO;
import modelDAO.ReservaDAO;
import modelDAO.TourDAO;
import modelDTO.ReservaDTO;
/**
 * FXML Controller class
 *
 * @author jitor
 */
public class RegistrarReservaController implements Initializable {

    ReservaDAO reservaDAO;
    
    TourDAO tourDAO = new TourDAO();
    
    ClienteDAO clienteDAO = new ClienteDAO();
    
    @FXML
    private AnchorPane panelRegistrar;
    @FXML
    private Button BtnRegistrarVolver;
    @FXML
    private TextField txtIdTour;
    @FXML
    private TableColumn<Tour, String> columID;
    @FXML
    private TableView<Tour> tbvListaDeTours;
    @FXML
    private TableColumn<Tour, String> columNombre;
    @FXML
    private TableColumn<Tour, String> columPrecio;
    @FXML
    private TextField txtIdCliente;
    @FXML
    private TextField txtIdReserva;
    @FXML
    private TextField txtCantPersonas;
    @FXML
    private TextField txtCosto;
    @FXML
    private TextField txtMontAbonado;
    @FXML
    private Button btnGuardar;
    @FXML
    private DatePicker dtpFecReserva;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        llenarTabla();
        
        actualizarTablaTours();
    }    
    
    public void llenarTabla(){
        
        columID.setCellValueFactory(new PropertyValueFactory<>("turIdtour"));
        columNombre.setCellValueFactory(new PropertyValueFactory<>("turNombretour"));  
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("turCostos"));
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }  
    
    public void mostrarAlertaSiNoExisteCliente(Integer idCliente, Reserva reserva) {
        
        List<Integer> listaIdsClientes = clienteDAO.obtenerIdsClientes();

        if (listaIdsClientes.contains(idCliente)) {
        
        Cliente cliente = new Cliente();
        
        cliente.setCliIdcliente(idCliente);
       
        reserva.setResIdcliente(cliente);
        
        } else {

        showAlert("No se encontro el cliente, favor registrarlo antes :)");
        }
    }
    
    public void mostrarAlertaSiNoExisteTour(Integer idTour, Reserva reserva) {
        
        List<Integer> listaIdsTours = tourDAO.obtenerIdsTours();

        if (listaIdsTours.contains(idTour)) {
        
        Tour tour = new Tour();
        
        tour.setTurIdtour(idTour);
       
        reserva.setResIdtour(tour);
        
        } else {

        showAlert("No se encontro el Tour");
        }
    }
    
    @FXML
    private void clickVolver(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void clickGuardar(ActionEvent event) throws IOException {
        
        Reserva reserva = new Reserva();
        
        mostrarAlertaSiNoExisteCliente(Integer.valueOf(txtIdCliente.getText()), reserva);
        
        mostrarAlertaSiNoExisteTour(Integer.valueOf(txtIdTour.getText()),reserva);
  
        reserva.setResIdreserva(Integer.valueOf(txtIdReserva.getText()));
        
        reserva.setResCantpersonas(Short.valueOf(txtCantPersonas.getText()));
        
        reserva.setResCosto(BigDecimal.valueOf(Long.parseLong(txtCosto.getText())));
        
        reserva.setResMontosabonados(BigDecimal.valueOf(Long.parseLong(txtMontAbonado.getText())));
        
        reserva.setResFecreserva(Date.valueOf(dtpFecReserva.getValue().toString()));
        
        ReservaDTO reservaDTO = new ReservaDTO(reserva);
        
        reservaDAO = new ReservaDAO();
        
        reservaDAO.guardarReserva(reservaDTO);
        
        reservaDAO.closeConnection();
        
        showAlert("Se guardo la reserva con exito!");
        
        App.setRoot("primary");
        
    }
    
    private void actualizarTablaTours() {
        
        TourDAO tourDAO1 = new TourDAO();
    
        List<Tour> listaTours = tourDAO1.obtenerTour();
        
        tourDAO1.closeConnection();
    
        ObservableList<Tour> toursObservableList = FXCollections.observableArrayList(listaTours);
        tbvListaDeTours.setItems(toursObservableList);
    } 

    @FXML
    private void obtenerIdTour(MouseEvent event) {
        
        int index = tbvListaDeTours.getSelectionModel().getFocusedIndex();
        Tour temp = tbvListaDeTours.getItems().get(index);
        int id = tourDAO.getTourPorID(temp.getTurIdtour().toString()).getTourId();
 
        txtIdTour.setText(String.valueOf(id));
    }

}
