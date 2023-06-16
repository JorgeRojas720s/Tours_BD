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
import model.Empresa;
import modelDAO.EmpresaDAO;
import modelDAO.ItinerarioDAO;
import modelDAO.ReservaDAO;
import modelDAO.TipoTourDAO;
import modelDAO.TourDAO;
import modelDTO.EmpresaDTO;
/**
 * FXML AdministrarEmpresaController class
 *
 * @author jitor
 */
public class AdministrarEmpresaController implements Initializable {
    
    EmpresaDAO empresaDAO = new EmpresaDAO();
    
    TourDAO tourDAO = new TourDAO();

    TipoTourDAO tipoTourDAO = new TipoTourDAO();
    
    ItinerarioDAO itinerarioDAO = new ItinerarioDAO();
    
    ReservaDAO reservaDAO = new ReservaDAO();

    @FXML
    private TextField txtCedulaJuridica;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtCalificacion;
    @FXML
    private Button BtnEliminar;
    @FXML
    private Button BtnModificar;
    @FXML
    private Button BtnBuscar;
    @FXML
    private AnchorPane panelAdministrar;
    @FXML
    private Button BtnAdministrarVolver;
    @FXML
    private TableView<Empresa> tbvListaDeEmpresas;
    @FXML
    private TableColumn<Empresa, String> columID;
    @FXML
    private TableColumn<Empresa, String> columEmpresa;
    @FXML
    private TableColumn<Empresa, String> columCalificacion;
    
    @FXML
    private TextField txtFecFundacion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        BtnEliminar.setDisable(true);
        BtnModificar.setDisable(true);
        
        llenarTabla();
        
        actualizarTablaEmpresas();  
    } 
    
    private void mostrarAlerta(String message) {
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }  
    
    private void llenarTabla(){
        
        columID.setCellValueFactory(new PropertyValueFactory<>("empCedjuridica"));
        columEmpresa.setCellValueFactory(new PropertyValueFactory<>("empNombre"));  
        //columTelefono.setCellValueFactory(new PropertyValueFactory<>("empTelefono"));
        columCalificacion.setCellValueFactory(new PropertyValueFactory<>("empCalificacion"));
    }
    
    private void limpiar(){
        
        txtCedulaJuridica.setText(null);
        txtNombre.setText(null);
        txtTelefono.setText(null);
        txtCorreo.setText(null);
        txtCalificacion.setText(null);
        txtFecFundacion.setAccessibleText(null);
    }

    @FXML
    private void clickVolver(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    
    
    @FXML
    private void clickBuscar(ActionEvent event) {
        
            empresaDAO = new EmpresaDAO();         
            String idEmpresa = txtCedulaJuridica.getText();
            if (!idEmpresa.isEmpty()) {
                BtnEliminar.setDisable(false);
                BtnModificar.setDisable(false);
                EmpresaDTO empreesaDTO = empresaDAO.getEmpresaPorCedula(idEmpresa);
                if (empreesaDTO != null) {
//                  id = pacienteDto.getPacDtoId();
                    txtNombre.setText(empreesaDTO.getEmpNombre());
                    txtTelefono.setText(empreesaDTO.getEmpTelefono());
                    txtCorreo.setText(empreesaDTO.getEmpCorreo());
                    txtCalificacion.setText(empreesaDTO.getEmpCalificacion());
                    txtFecFundacion.setText(empreesaDTO.getEmpFechafund().toString());
                }
            
            } else {
                    mostrarAlerta("No se encontró ninguna Empresa");
            }  
    }
    
    private void borrarTours(int id){
        
        List<Integer> listaIdsTours = tourDAO.obtenerToursDeEmpresa(id);

        // Eliminar cada Tour de la lista
        for (Integer idTour : listaIdsTours) {
            tourDAO.eliminarTour(idTour);
        }
        
    }

    @FXML
    private void clickEliminar(ActionEvent event) throws IOException {
         
        String idEmpresa = txtCedulaJuridica.getText();
       
        if(!idEmpresa.isEmpty()){
            
            //eliminar reservas vinculadas
            
            //eliminar itinerarios vinculados
            
            //eliminar antes tipoTour
            
            //eliminar luego tour
            
        int id = Integer.parseInt(idEmpresa);

        List<Integer> listaIdsTours = tourDAO.obtenerToursDeEmpresa(id);
            System.out.println("peobelm");
        
        for (Integer idTour : listaIdsTours) {
            
        int res = tourDAO.obtenerReservas(idTour);
            
        int tpt = tourDAO.obtenerCodigoTipoTour(idTour);
            System.out.println("Bien");
        int iti = tourDAO.obtenerItinerarios(idTour);
        
        reservaDAO.eliminarReserva(res);
        
        itinerarioDAO.eliminarItinerario(iti);
          
            System.out.println("resrs");
            
        tourDAO.eliminarTour(idTour);
        
        tipoTourDAO.eliminarTipoTour(tpt);
        
            System.out.println("ggggg");
 
        }
 
        empresaDAO.eliminarEmpresa(id);
        
        id = 0;
        
        limpiar();
        
        mostrarAlerta("Eliminado Exitosamente");
        
        actualizarTablaEmpresas();
        
        App.setRoot("primary");
            
          
        }else{
             mostrarAlerta("No se a ingresado una Cedula Juridica");
        }      
    }

    @FXML
    private void clickModificar(ActionEvent event) throws IOException {
        
        String idEmpresa = txtCedulaJuridica.getText();
            
        if(!idEmpresa.isEmpty()){

        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String calificacion = txtCalificacion.getText();
        String fecFundacion = txtFecFundacion.getText();
 
        EmpresaDTO empresaDTO = new EmpresaDTO();
        
        empresaDTO.setEmpCedjuridica(Integer.valueOf(idEmpresa)); 
        empresaDTO.setEmpNombre(nombre);
        empresaDTO.setEmpTelefono(telefono);
        empresaDTO.setEmpCorreo(correo);
        empresaDTO.setEmpCalificacion(calificacion);
        empresaDTO.setEmpFechafund(Date.valueOf(fecFundacion));
        
        empresaDAO.actualizarEmpresa(empresaDTO);
        
        mostrarAlerta("Modificado Exitosamente");
        
        App.setRoot("primary");

        }else{
        mostrarAlerta("No a ingresado una Cedula Juridica");   
        }
        
    }

    private void actualizarTablaEmpresas() {
        
        EmpresaDAO empresaDO1 = new EmpresaDAO();
    
        List<Empresa> listaEmpresas = empresaDO1.obtenerEmpresas();
        
        empresaDO1.closeConnection();
    
        ObservableList<Empresa> empresaObservableList = FXCollections.observableArrayList(listaEmpresas);
        tbvListaDeEmpresas.setItems(empresaObservableList);
   
    } 

    @FXML
    private void obtenerIdEmpresas(MouseEvent event) {
        
        int index = tbvListaDeEmpresas.getSelectionModel().getFocusedIndex();
        Empresa temp = tbvListaDeEmpresas.getItems().get(index);
        int id = empresaDAO.getEmpresaPorCedula(temp.getEmpCedjuridica().toString()).getEmpCedjuridica();
 
        txtCedulaJuridica.setText(String.valueOf(id));
    }


}
