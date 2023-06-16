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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Cliente;
import model.Tour;
import modelDAO.ClienteDAO;
import modelDAO.TourDAO;
import modelDTO.ClienteDTO;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class RegistrarClienteController implements Initializable {
    
    ClienteDAO clienteDAO;
    
    TourDAO tourDAO = new TourDAO();

    @FXML
    private AnchorPane panelRegistrar;
    @FXML
    private Button BtnRegistrarVolver;
    
    private TextField txtIdTour;
 
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPApellido;
    @FXML
    private TextField txtSApellido;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private DatePicker dtpFecNacimiento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }  

    @FXML
    private void clickVolver(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void clickGuardar(ActionEvent event) throws IOException {
        
        Cliente cliente = new Cliente();
   
        cliente.setCliIdcliente(Integer.valueOf(txtCedula.getText()));
        
        cliente.setCliNombre(txtNombre.getText());
        
        cliente.setCliPapellido(txtPApellido.getText());
        
        cliente.setCliSapellido(txtSApellido.getText());
        
        cliente.setCliTelefono(txtTelefono.getText());
        
        cliente.setCliCorreo(txtCorreo.getText());

        cliente.setCliFecnacimiento(Date.valueOf(dtpFecNacimiento.getValue().toString()));
        
        ClienteDTO clienteDTO = new ClienteDTO(cliente);
        
        clienteDAO = new ClienteDAO();
        
        clienteDAO.guardarCliente(clienteDTO);
        
        clienteDAO.closeConnection();
        
        showAlert("Cliente guardado exitosamente!");
        
        App.setRoot("primary");
    }
    
}
