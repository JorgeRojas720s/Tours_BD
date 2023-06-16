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
import model.Cliente;
import modelDAO.ClienteDAO;
import modelDAO.ReservaDAO;
import modelDAO.TourDAO;
import modelDTO.ClienteDTO;
/**
 * FXML Controller class
 *
 * @author jitor
 */
public class AdministrarClienteController implements Initializable {
    
    ClienteDAO clienteDAO = new ClienteDAO();
    
    ReservaDAO reservaDAO = new ReservaDAO();

    TourDAO tourDAO = new TourDAO();

    @FXML
    private AnchorPane panelRegistrar;
    @FXML
    private Button BtnRegistrarVolver;
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
    private Button BtnBuscar;
    @FXML
    private TableView<Cliente> tbvListaDeClientes;
    @FXML
    private TableColumn<Cliente, String> columID;
    @FXML
    private TableColumn<Cliente, String> columPApellido;
    @FXML
    private TableColumn<Cliente, String> columSApellido;
    @FXML
    private Button BtnEliminar;
    @FXML
    private Button BtnModificar;
    @FXML
    private TableColumn<Cliente, String> columNombre;
    @FXML
    private TextField txtFecNacimiento;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        BtnEliminar.setDisable(true);
        BtnModificar.setDisable(true);
        
        llenarTabla();

        actualizarTablaClientes();
     
    }  
    
     private void mostrarAlerta(String message) {
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }  
    
    private void llenarTabla(){
        
        columID.setCellValueFactory(new PropertyValueFactory<>("cliIdcliente"));
        columNombre.setCellValueFactory(new PropertyValueFactory<>("cliNombre"));  
        columPApellido.setCellValueFactory(new PropertyValueFactory<>("cliPapellido"));
        columSApellido.setCellValueFactory(new PropertyValueFactory<>("cliSapellido"));
    }
    
    @FXML
    private void clickVolver(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void clickBuscar(ActionEvent event) {
        
        clienteDAO = new ClienteDAO();
        String idCliente = txtCedula.getText();
        
        if(!idCliente.isEmpty()){
            BtnEliminar.setDisable(false);
            BtnModificar.setDisable(false);
            ClienteDTO clienteDTO = clienteDAO.getClientePorCedula(idCliente);
            if(clienteDTO != null){
                
            txtNombre.setText(clienteDTO.getCliNombre());
            txtPApellido.setText(clienteDTO.getCliPapellido());
            txtSApellido.setText(clienteDTO.getCliSapellido());
            txtTelefono.setText(clienteDTO.getCliTelefono());
            txtCorreo.setText(clienteDTO.getCliCorreo());
            txtFecNacimiento.setText(clienteDTO.getCliFecnacimiento().toString());
             
         
            }
        }else{
            mostrarAlerta("No se encontró ningun Cliente"); 
        }

    }


    @FXML
    private void clickEliminar(ActionEvent event) throws IOException {
      
        String idCliente = txtCedula.getText();
        
        if(!idCliente.isEmpty()){     
   
   
        int id = Integer.parseInt(idCliente);
        
         List<Integer> listaIdsReservas = reservaDAO.obtenerReservasDeCliente(id);
   
         for (Integer idReservas : listaIdsReservas) {
  
             reservaDAO.eliminarReserva(idReservas);

         }
        
        clienteDAO.eliminarCliente(id);
        
        id = 0;
        
        mostrarAlerta("Eliminado Exitosamente");
        
        actualizarTablaClientes();
        
        App.setRoot("primary");
            
          
        }else{
             mostrarAlerta("No se a ingresado una Cedula");
        }      
    }

    @FXML
    private void clickModificar(ActionEvent event) throws IOException {
        
         String idCliente = txtCedula.getText();
            
        if(!idCliente.isEmpty()){

        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String pApellido = txtPApellido.getText();
        String sApellido = txtSApellido.getText();
        String fecNacimiento = txtFecNacimiento.getText();
 
        ClienteDTO clienteDTO = new ClienteDTO();
        
        clienteDTO.setClienteId(Integer.valueOf(idCliente)); 
        clienteDTO.setCliNombre(nombre);
        clienteDTO.setCliTelefono(telefono);
        clienteDTO.setCliCorreo(correo);
        clienteDTO.setCliPapellido(pApellido);
        clienteDTO.setCliSapellido(sApellido);
        clienteDTO.setCliFecnacimiento(Date.valueOf(fecNacimiento));
        
        clienteDAO.actualizarCliente(clienteDTO);
        
        mostrarAlerta("Modificado Exitosamente");
        
        App.setRoot("primary");

        }else{
        mostrarAlerta("No a ingresado una Cedula");   
        }
    
    }

    private void actualizarTablaClientes() {
        
        ClienteDAO clienteDAO1 = new ClienteDAO();
    
        List<Cliente> listaClientes = clienteDAO1.obtenerClientes();
        
        clienteDAO1.closeConnection();
    
        ObservableList<Cliente> clienteObservableList = FXCollections.observableArrayList(listaClientes);
        tbvListaDeClientes.setItems(clienteObservableList);
   
    } 

    @FXML
    private void obtenerIdCliente(MouseEvent event) {
        
        int index = tbvListaDeClientes.getSelectionModel().getFocusedIndex();
        Cliente temp = tbvListaDeClientes.getItems().get(index);
        int id = clienteDAO.getClientePorCedula(temp.getCliIdcliente().toString()).getClienteId();
 
        txtCedula.setText(String.valueOf(id));
    }
    
    
}
