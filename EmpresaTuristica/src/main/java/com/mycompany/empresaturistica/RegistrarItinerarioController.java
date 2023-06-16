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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Itinerario;
import model.Tour;
import modelDAO.ItinerarioDAO;
import modelDAO.TourDAO;
import modelDTO.ItinerarioDTO;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class RegistrarItinerarioController implements Initializable {
    
    TourDAO tourDAO = new TourDAO();
    
    ItinerarioDAO itinerarioDAO;

    @FXML
    private TextField txtIdTour;
    @FXML
    private TextField txtIdItinerario;
    @FXML
    private TextField txtLugar;
    @FXML
    private DatePicker dtpFechaSalida;
    @FXML
    private DatePicker dtpFecRegreso;
    @FXML
    private TextField txtDuracion;
    @FXML
    private TableView<Tour> tbvListaDeTours;
    @FXML
    private TextArea txaDescpActividades;
    @FXML
    private TableColumn<Tour, String> columID;
    @FXML
    private TableColumn<Tour, String> columNombre;
    @FXML
    private TableColumn<Tour, String> columCosto;
    @FXML
    private AnchorPane panelRegistrar;
    @FXML
    private Button BtnRegistrarVolver;
    @FXML
    private Button guardarItinerario;
    @FXML
    private ComboBox combHoraSalida;
    @FXML
    private ComboBox combHoraLLegada;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> listaDeHoras = FXCollections.observableArrayList(
        "7 a.m","8 a.m","9 a.m","10 a.m","11 a.m","1 p.m","2 p.m","3 p.m","4 p.m","5 p.m");
        
        combHoraSalida.setItems(listaDeHoras);
        
        combHoraLLegada.setItems(listaDeHoras);

        llenarTabla();
        
        actualizarTablaTours();
    }    
    
    public void llenarTabla(){
        
        columID.setCellValueFactory(new PropertyValueFactory<>("turIdtour"));
        columNombre.setCellValueFactory(new PropertyValueFactory<>("turNombretour"));  
        columCosto.setCellValueFactory(new PropertyValueFactory<>("turCostos"));
    }
    
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }  
    
    public void mostrarAlertaSiNoExisteTour(Integer idTour, Itinerario itinerario) {
        
        List<Integer> listaIdsTours = tourDAO.obtenerIdsTours();

        if (listaIdsTours.contains(idTour)) {
        
        System.out.println("El ID del Tour existe en la base de datos.");
        
        Tour tour = new Tour();
        
        tour.setTurIdtour(idTour);
       
        itinerario.setItiIdtours(tour);
        
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
        
       Itinerario itinerario = new Itinerario(); 
       
       mostrarAlertaSiNoExisteTour(Integer.valueOf(txtIdTour.getText()),itinerario);
        
        itinerario.setItiIditinerario(Integer.valueOf(txtIdItinerario.getText()));
        
        itinerario.setItiLugar(txtLugar.getText());
        
        itinerario.setItiFecsalida(Date.valueOf(dtpFechaSalida.getValue().toString()));
        
        itinerario.setItiFecllegada(Date.valueOf(dtpFecRegreso.getValue().toString()));
        
        itinerario.setItiDuracion(txtDuracion.getText());
        
        String aux1 = String.valueOf(combHoraSalida.getSelectionModel().getSelectedItem());
         
        int n1 = aux1.indexOf(" ");

        aux1 =  aux1.substring(0, n1);

        itinerario.setItiHorasalida(aux1);
        
        String aux2 = String.valueOf(combHoraSalida.getSelectionModel().getSelectedItem());
         
        int n2 = aux2.indexOf(" ");

        aux2 =  aux2.substring(0, n2);
        
        itinerario.setItiHorallegada(aux2);
        
        itinerario.setItiDescpActividades(txaDescpActividades.getText());
        
        ItinerarioDTO itinerarioDTO = new ItinerarioDTO(itinerario);
        
        itinerarioDAO = new ItinerarioDAO();
        
        itinerarioDAO.guardarItinerario(itinerarioDTO);
       
        itinerarioDAO.closeConnection();
       
       showAlert("Se guardo el itinerario con exito!");
       
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
    private void obtenerID(MouseEvent event) {

            int index = tbvListaDeTours.getSelectionModel().getFocusedIndex();
            Tour temp = tbvListaDeTours.getItems().get(index);
            int id = tourDAO.getTourPorID(temp.getTurIdtour().toString()).getTourId();
 
            txtIdTour.setText(String.valueOf(id));
    }


}
