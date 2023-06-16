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

import model.Tour;
import modelDAO.ItinerarioDAO;
import modelDAO.ReservaDAO;
import modelDAO.TipoTourDAO;

import modelDAO.TourDAO;
import modelDTO.EmpresaDTO;

import modelDTO.TipoTourDTO;
import modelDTO.TourDTO;
/**
 * FXML Controller class
 *
 * @author jitor
 */
public class AdministrarTourController implements Initializable {
    
    TourDAO tourDAO = new TourDAO();

    TipoTourDAO tipoTourDAO = new TipoTourDAO();
    
    ItinerarioDAO itinerarioDAO = new ItinerarioDAO();
    
    ReservaDAO reservaDAO = new ReservaDAO();
    
    String tptCodigo;
    
    @FXML
    private AnchorPane panelAdministrar;
    @FXML
    private Button BtnAdministrarVolver;
    @FXML
    private TextField txtIdTour;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCostos;
    @FXML
    private TextField txtCodigoTPT;
    @FXML
    private TextField txtNombreTPT;
    @FXML
    private TableView<Tour> tbvListaDeTours;
    @FXML
    private TableColumn<Tour, String> columID;
    @FXML
    private TableColumn<Tour, String> columNombre;
    @FXML
    private TableColumn<Tour, String> columPrecio;
    @FXML
    private Button buscarTour;
    @FXML
    private Button BtnModificar;
    @FXML
    private Button BtnEliminar;
    @FXML
    private TextField txtFecRegreso;
    @FXML
    private TextField txtFecSalida;
    @FXML
    private TextField txtPais;
    @FXML
    private TextField txtTipoTour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        BtnModificar.setDisable(true);
        BtnEliminar.setDisable(true);
        
        llenarTabla();
        
        actualizarTablaTours();
        
    }    
    
    private void llenarTabla(){
        
        columID.setCellValueFactory(new PropertyValueFactory<>("turIdtour"));
        columNombre.setCellValueFactory(new PropertyValueFactory<>("turNombretour"));  
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("turCostos"));
    }
    
    private void mostrarAlerta(String message) {
     
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
    
    private void modificarTipoTour(){
        
        String tipoTour = txtTipoTour.getText();
        String tptNombre = txtNombreTPT.getText();
        String pais = txtPais.getText();
    
        TipoTourDTO tipoTourDTO = new TipoTourDTO();
        
        tipoTourDTO.setTptTipotour(tipoTour);
        tipoTourDTO.setTptNombre(tptNombre);
        tipoTourDTO.setTptPais(pais);
        
        tipoTourDAO.actualizarTipoTour(tipoTourDTO);
    }

    @FXML
    private void clickModificar(ActionEvent event) throws IOException {
        
        String idTour = txtIdTour.getText();
            
        if(!idTour.isEmpty()){
            
        modificarTipoTour();

        String nombre = txtNombre.getText();
        String costos = txtCostos.getText();
        String fecRegreso = txtFecRegreso.getText();
        String fecSalida = txtFecSalida.getText();
    
        TourDTO touorDTO = new TourDTO();

        touorDTO.setTourId(Integer.valueOf(idTour)); 
        touorDTO.setNombreTour(nombre);
        touorDTO.setTurCostos(costos);
        touorDTO.setFechaRegreso(Date.valueOf(fecRegreso));
        touorDTO.setFechaSalida(Date.valueOf(fecSalida));

        tourDAO.actualizarTour(touorDTO);
 
        
        mostrarAlerta("Modificado Exitosamente");
        
        App.setRoot("primary");

        }else{
        mostrarAlerta("No a ingresado una Cedula Juridica");   
        } 
    }


    @FXML
    private void clickEliminar(ActionEvent event) throws IOException {
        
         String idTour = txtIdTour.getText();
 
         if(!idTour.isEmpty()){
             
        int id = Integer.parseInt(idTour);
        
        int tptCodigo1 = Integer.parseInt(tptCodigo);
        
        int res = tourDAO.obtenerReservas(id);
             
        int iti = itinerarioDAO.obtenerIDItinerarios(id);
        
        reservaDAO.eliminarReserva(res);
             
        itinerarioDAO.eliminarItinerario(iti);
       
        tourDAO.eliminarTour(id);
        
        tipoTourDAO.eliminarTipoTour(tptCodigo1);
        
        id = 0;
        tptCodigo1 = 0;
        
        mostrarAlerta("Eliminado Exitosamente");
        
        actualizarTablaTours();
        
        App.setRoot("primary");
        }else{
              mostrarAlerta("No se a ingresado un Tour");
        }  
    }
    
    private void getTipoTour(String tptCodigo){
        
        tipoTourDAO = new TipoTourDAO(); 

        TipoTourDTO tipoTourDTO = tipoTourDAO.getTipoTourPorCodigo(tptCodigo);
        
        txtTipoTour.setText(tipoTourDTO.getTptTipotour());
        txtNombreTPT.setText(tipoTourDTO.getTptNombre());
        txtPais.setText(tipoTourDTO.getTptPais());
    }

    @FXML
    private void clickBuscar(ActionEvent event) {
            tourDAO = new TourDAO();   
            
            String idTour = txtIdTour.getText();
            if (!idTour.isEmpty()) {
                BtnEliminar.setDisable(false);
                BtnModificar.setDisable(false);
                TourDTO tourDTO = tourDAO.getTourPorID(idTour);

                if (tourDTO != null) {
//                  id = pacienteDto.getPacDtoId();
                    txtNombre.setText(tourDTO.getNombreTour());
                    txtCostos.setText(tourDTO.getTurCostos());
                    txtCodigoTPT.setText(tourDTO.getTurCodigotipotours());
                    txtFecSalida.setText(tourDTO.getFechaSalida().toString());
                    txtFecRegreso.setText(tourDTO.getFechaRegreso().toString());
                    
                    tptCodigo = txtCodigoTPT.getText();
                    
                    System.out.println("tttttt: " + tptCodigo);
                   
                    getTipoTour(tptCodigo);
                   
                }
            } else {
                    mostrarAlerta("No se encontró ningun Tour");
            }  
    }
        
    private void actualizarTablaTours() {
        
        TourDAO tourDAO1 = new TourDAO();
    
        List<Tour> listaTours = tourDAO1.obtenerTour();
        
        tourDAO1.closeConnection();
    
        ObservableList<Tour> toursObservableList = FXCollections.observableArrayList(listaTours);
        tbvListaDeTours.setItems(toursObservableList);
   
    } 

    @FXML
    private void obtenerIdTours(MouseEvent event) {
        
        int index = tbvListaDeTours.getSelectionModel().getFocusedIndex();
        Tour temp = tbvListaDeTours.getItems().get(index);
        int id = tourDAO.getTourPorID(temp.getTurIdtour().toString()).getTourId();
 
        txtIdTour.setText(String.valueOf(id));
    }

}
