 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.empresaturistica;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Tour;
import modelDAO.ClienteDAO;
import modelDAO.ReservaDAO;
import modelDAO.TourDAO;
import modelDTO.ClienteDTO;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class PrimaryController implements Initializable {
    
    ClienteDAO clienteDAO = new ClienteDAO();
    
    TourDAO tourDAO = new TourDAO();
    
    ReservaDAO reservaDAO = new ReservaDAO();

    @FXML
    private Button BtnRegistrar;
    @FXML
    private Button BtnAdministrar;
    @FXML
    private AnchorPane panelRegistrar;
    @FXML
    private Button BtnRegistrarEmpresa;
    @FXML
    private Button BtnRegistrarTour;
    @FXML
    private Button BtnRegistrarCliente;
    @FXML
    private Button BtnRegistrarVolver;
    @FXML
    private AnchorPane panelAdministrar;
    @FXML
    private Button BtnAdministrarEmpresa;
    @FXML
    private Button BtnAdministrarTour;
    @FXML
    private Button BtnAdministrarCliente;
    @FXML
    private Button BtnAdministrarVolver;
    @FXML
    private Button BtnRegistrarReserv;
    @FXML
    private Button BtnAdministrarReserva;
    @FXML
    private Button BtnAdministrarItinerario;
    @FXML
    private TextField txtBuscador;
    @FXML
    private Button BtnBuscador;
    @FXML
    private Label labNombre;
    @FXML
    private Label labSApellido;
    @FXML
    private Label labPApellido;
    @FXML
    private TableView<Tour> tbvListaDeTours;
    @FXML
    private TableColumn<Tour, String> columIdTour;
    @FXML
    private TableColumn<Tour, String> columNombreTour;
    @FXML
    private TableColumn<Tour, String> columFecSalida;
    @FXML
    private TableColumn<Tour, String> columFecRegreso;
    @FXML
    private TableColumn<Tour, String> columCosto;
    @FXML
    private Label labTelefono;
    @FXML
    private Label labCliTelefono;
    @FXML
    private Label labCorreo;
    @FXML
    private Label labCliCorreo;
    @FXML
    private Label labToursDeCliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        tbvListaDeTours.setVisible(false);
        
        labToursDeCliente.setVisible(false);
        labTelefono.setVisible(false);
        labCorreo.setVisible(false);
        
        mostrarPanelRegistrar(false);
        mostrarPanelAdministrar(false);
    }   
    
    private void llenarTabla(){
        
        columIdTour.setCellValueFactory(new PropertyValueFactory<>("turIdtour"));
        columNombreTour.setCellValueFactory(new PropertyValueFactory<>("turNombretour"));  
        columFecSalida.setCellValueFactory(new PropertyValueFactory<>("turFechasalida"));
        columFecRegreso.setCellValueFactory(new PropertyValueFactory<>("turFecharegreso"));
        columCosto.setCellValueFactory(new PropertyValueFactory<>("turCostos"));  
       
    }
    
    
    private void mostrarAlerta(String message) {
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }  
    
     public void mostrarPanelRegistrar(boolean visible){
        
        panelRegistrar.setVisible(visible);
        BtnRegistrarEmpresa.setVisible(visible);
        BtnRegistrarTour.setVisible(visible);
        BtnRegistrarCliente.setVisible(visible);
        BtnRegistrarVolver.setVisible(visible);
    }
    
    public void mostrarPanelAdministrar(boolean visible){
        
        panelAdministrar.setVisible(visible);
        BtnAdministrarEmpresa.setVisible(visible);
        BtnAdministrarTour.setVisible(visible);
        BtnAdministrarCliente.setVisible(visible);
        BtnAdministrarVolver.setVisible(visible);
    }
  

    @FXML
    private void clickRegistrar(ActionEvent event) {
        BtnRegistrar.setVisible(false);
        mostrarPanelRegistrar(true);
    }

    @FXML
    private void clickAdministrar(ActionEvent event) {
        BtnAdministrar.setVisible(false);
        mostrarPanelAdministrar(true);
    }

    @FXML
    private void clickRegistrarEmpresa(ActionEvent event) throws IOException {
        App.setRoot("registrarEmpresa");
    }

    @FXML
    private void clickRegistrarTour(ActionEvent event) throws IOException {
        App.setRoot("registrarTour");
    }

    @FXML
    private void clickRegistrarCliente(ActionEvent event) throws IOException {
        App.setRoot("registrarCliente");
    }
    
    @FXML
    private void clickRegistrarItinerario(ActionEvent event) throws IOException {
        App.setRoot("registrarItinerario");
    }
    
    @FXML
    private void clickRegistrarReserva(ActionEvent event) throws IOException {
           App.setRoot("registrarReserva");    
    }


    @FXML
    private void clickPanelRegistrarVolver(ActionEvent event) {
        BtnRegistrar.setVisible(true);
        mostrarPanelRegistrar(false);
    }

    @FXML
    private void clickAdministrarEmpresa(ActionEvent event) throws IOException {
         App.setRoot("administrarEmpresa");   
    }

    @FXML
    private void clickAdministrarTour(ActionEvent event) throws IOException {
         App.setRoot("administrarTour");   
    }

    @FXML
    private void clickAdministrarItinerario(ActionEvent event) throws IOException {
        App.setRoot("administrarItinerario"); 
    }

    @FXML
    private void clickAdministrarCliente(ActionEvent event) throws IOException {
          App.setRoot("administrarCliente"); 
    }

 
    @FXML
    private void clcikAdministrarReserva(ActionEvent event) throws IOException {
        App.setRoot("administrarReserva");
    }
    
    
    @FXML
    private void clickPanelAdministrarVolver(ActionEvent event) {
        BtnAdministrar.setVisible(true);
        mostrarPanelAdministrar(false);
    }

    @FXML
    private void clickBuscador(ActionEvent event) {
        
        String idCliente = txtBuscador.getText();
        
        if(!idCliente.isEmpty()){
            
        ClienteDTO clienteDTO = clienteDAO.getClientePorCedula(idCliente); 

        if(clienteDTO != null){

            llenarTabla();
            actualizarTablaTours(idCliente);

            tbvListaDeTours.setVisible(true);
            labTelefono.setVisible(true);
            labCorreo.setVisible(true);
            labToursDeCliente.setVisible(true);
            
            labNombre.setText(clienteDTO.getCliNombre());
            labPApellido.setText(clienteDTO.getCliPapellido());
            labSApellido.setText(clienteDTO.getCliSapellido());
            labCliTelefono.setText(clienteDTO.getCliTelefono());
            labCliCorreo.setText(clienteDTO.getCliCorreo());
  
        }else{
            mostrarAlerta("No se encontro el Cliente :(");
        }
    }else{
            mostrarAlerta("Ingrese un usuario a buscar :)"); 
        }
        
    }
    
private void actualizarTablaTours(String idCliente) {
    
    List<Integer> lista1 = reservaDAO.getReservaIdTour(idCliente);

    TourDAO tourDAO1 = new TourDAO();
    List<Tour> listaTours = tourDAO1.obtenerTour();

    List<Tour> toursCliente = listaTours.stream()
            .filter(tour -> lista1.contains(tour.getTurIdtour()))
            .collect(Collectors.toList());

    tourDAO1.closeConnection();

    ObservableList<Tour> toursObservableList = FXCollections.observableArrayList(toursCliente);
    tbvListaDeTours.setItems(toursObservableList);
}

}
