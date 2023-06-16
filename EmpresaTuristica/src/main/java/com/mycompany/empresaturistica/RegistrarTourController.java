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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Empresa;
import model.TipoTour;
import model.Tour;
import modelDAO.EmpresaDAO;
import modelDAO.TipoTourDAO;
import modelDAO.TourDAO;
import modelDTO.TipoTourDTO;
import modelDTO.TourDTO;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class RegistrarTourController implements Initializable {
    
    TourDAO tourDAO;
    
    TipoTourDAO tipoTourDAO;
    
    EmpresaDAO empresaDAO = new EmpresaDAO();
    

    @FXML
    private TextField txtIdEmpresa;
    @FXML
    private TextField txtIdTour;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCostos;
    @FXML
    private TextField txtCodigoTPT;
    @FXML
    private DatePicker dtpFecSalida;
    @FXML
    private DatePicker dtpFecRegreso;
    @FXML
    private TextField txtNombreTPT;
    @FXML
    private TableView<Empresa> tbvListaDeEmpresas;
    @FXML
    private TableColumn<Empresa, String> columID;
    @FXML
    private TableColumn<Empresa, String> columEmpresa;
    @FXML
    private TableColumn<Empresa, String> columTelefono;
    @FXML
    private TableColumn<Empresa, String> columCalificacion;
    @FXML
    private AnchorPane panelRegistrar;
    @FXML
    private Button BtnRegistrarVolver;
    @FXML
    private Button guardarTour;
    @FXML
    private TextField txtPais;
    @FXML
    private ComboBox combTipoTour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> listaTipoTour = FXCollections.observableArrayList(
        "N","I");
        
        combTipoTour.setItems(listaTipoTour);
        
        llenarTabla();
        
        actualizarTablaEmpresas();
    }    
    
    public void llenarTabla(){
        
        columID.setCellValueFactory(new PropertyValueFactory<>("empCedjuridica"));
        columEmpresa.setCellValueFactory(new PropertyValueFactory<>("empNombre"));  
        columTelefono.setCellValueFactory(new PropertyValueFactory<>("empTelefono"));
        columCalificacion.setCellValueFactory(new PropertyValueFactory<>("empCalificacion"));
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }  
    
    public void mostrarAlertaSiNoExisteEmpresa(Integer idEmpresa, Tour tour) {
        
    List<Integer> listaIdsEmpresas = empresaDAO.obtenerIdsEmpresas();

        if (listaIdsEmpresas.contains(idEmpresa)) {
        
        Empresa empresa = new Empresa();
        
        empresa.setEmpCedjuridica(idEmpresa);
       
        tour.setTurIdempresa(empresa);
        
        } else {
        
        System.out.println("++++++++++++++++++++++++El ID de la empresa no existe en la base de datos. Mostrar una alerta.+++++++++++++++++++++++++");
       showAlert("No se encontro ninguna Empresa");
        }
        
    }

    @FXML
    private void clickVolver(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void clickGuardar(ActionEvent event) throws IOException {

        
        TipoTour tipoTour = new TipoTour();
  
        tipoTour.setTptCodigo(Integer.valueOf(txtCodigoTPT.getText()));
        
        tipoTour.setTptTipotour(combTipoTour.getSelectionModel().getSelectedItem().toString());
        
        tipoTour.setTptNombre(txtNombreTPT.getText());
        
        System.out.println(txtPais.getText());
        
        tipoTour.setTptPais(txtPais.getText());
        
        TipoTourDTO tipoTourDTO = new TipoTourDTO(tipoTour); 
        
        tipoTourDAO = new TipoTourDAO();
         
        tipoTourDAO.guardarTipoTour(tipoTourDTO);
         
        tipoTourDAO.closeConnection();
        
        /////

        Tour tour = new Tour();
           
        mostrarAlertaSiNoExisteEmpresa(Integer.valueOf(txtIdEmpresa.getText()), tour);

        tour.setTurIdtour(Integer.valueOf(txtIdTour.getText()));
        
        tour.setTurNombretour(txtNombre.getText());
        
        tour.setTurFechasalida(Date.valueOf(dtpFecSalida.getValue().toString()));
        
        tour.setTurFecharegreso(Date.valueOf(dtpFecRegreso.getValue().toString()));
        
        BigDecimal numeroBigDecimal = new BigDecimal(txtCostos.getText());
        
        tour.setTurCostos(numeroBigDecimal);
        
        tour.setTurCodigotipotours(tipoTour);
        
        TourDTO tourDTO = new TourDTO(tour); 
  
        tourDAO = new TourDAO();

        tourDAO.guardarTour(tourDTO);

        tourDAO.closeConnection();
        
        showAlert("Se guardo el Tour con exito!");
 
        System.out.println("Tour y Tipo de Tour Guardados"); 
        
        App.setRoot("primary");
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
 
        txtIdEmpresa.setText(String.valueOf(id));
        
    }
}
