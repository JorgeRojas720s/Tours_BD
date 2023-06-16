/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.empresaturistica;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Empresa;
import modelDAO.EmpresaDAO;
import modelDTO.EmpresaDTO;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class RegistrarEmpresaController implements Initializable {
    
  
    
    EmpresaDAO empresaDAO = new EmpresaDAO();

    @FXML
    private AnchorPane panelRegistrar;
    @FXML
    private Button BtnRegistrarVolver;
    @FXML
    private TextField txtCedulaJuridica;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private DatePicker dtpFecFundacion;
    @FXML
    private Button guardar;
    @FXML
    private ComboBox combCalificacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> listaDeCalificaciones = FXCollections.observableArrayList(
        "1 Estrellas", "2 Estrellas","3 Estrellas", "4 Estrellas","5 Estrellas");
        
         combCalificacion.setItems(listaDeCalificaciones);
        // TODO
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
        
        Empresa empresa = new Empresa();
        
        empresa.setEmpCedjuridica(Integer.valueOf(txtCedulaJuridica.getText()));
        
        empresa.setEmpNombre(txtNombre.getText());
        
        empresa.setEmpTelefono(txtTelefono.getText());
        
        empresa.setEmpCorreo(txtCorreo.getText());
        
        String aux = String.valueOf(combCalificacion.getSelectionModel().getSelectedItem());
         
        int n = aux.indexOf(" ");

        aux =  aux.substring(0, n);
        
        empresa.setEmpCalificacion(aux);
        
        empresa.setEmpFechafund(Date.valueOf(dtpFecFundacion.getValue().toString()));

        EmpresaDTO empresaDTO = new EmpresaDTO(empresa);
        
        empresaDAO = new EmpresaDAO();
        
        empresaDAO.guardarEmpresa(empresaDTO);
        
        empresaDAO.closeConnection();
        
        showAlert("Empresa guardada exitosamente!");
        
        App.setRoot("primary");
    }

    
}
