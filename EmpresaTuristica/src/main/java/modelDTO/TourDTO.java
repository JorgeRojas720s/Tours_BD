/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDTO;

import java.sql.Date;
import java.util.Objects;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Tour;


/**
 *
 * @author jitor
 */
public class TourDTO {
    
    private SimpleStringProperty turIdtour;
    private SimpleStringProperty turNombretour;
    private SimpleObjectProperty turFechasalida;
    private SimpleObjectProperty turFecharegreso;
    private SimpleStringProperty turCostos;
    
    private SimpleStringProperty turIdempresa;
    private SimpleStringProperty turCodigotipotours;

    public TourDTO(){
        
        this.turIdtour = new SimpleStringProperty();
         this.turNombretour = new SimpleStringProperty();
        this.turFechasalida = new SimpleObjectProperty();
        this.turFecharegreso = new SimpleObjectProperty();
        this.turCostos = new SimpleStringProperty();
        
        this.turIdempresa = new SimpleStringProperty();
        this.turCodigotipotours = new SimpleStringProperty();
    }


    public TourDTO(Tour tours) {
        this();
        this.turIdtour.set(tours.getTurIdtour().toString());
        this.turNombretour.set(tours.getTurNombretour());
        this.turFechasalida.set(tours.getTurFechasalida());
        this.turFecharegreso.set(tours.getTurFecharegreso());
        this.turCostos.set(tours.getTurCostos().toString());
//      this.turIdempresa.set(new EmpresaDTO(tours.getTurIdempresa()))
        this.turIdempresa.set(tours.getTurIdempresa().getEmpCedjuridica().toString());
        this.turCodigotipotours.set(tours.getTurCodigotipotours().getTptCodigo().toString());
    }

    public Integer getTourId() {
        if(turIdtour.get()!=null && !turIdtour.get().isEmpty())
            return Integer.valueOf(turIdtour.get());
        else
            return null;
    }

    public String getTurIdempresa() {
        return turIdempresa.get();
    }

    public String getTurCodigotipotours() {
        return turCodigotipotours.get();
    }
 
    public void setTourId(Integer pTourId) {
        this.turIdtour.set(pTourId.toString());
    }

    public String getNombreTour() {
        return turNombretour.get();
    }

    public void setNombreTour(String pTurNombretour) {
        this.turNombretour.set(pTurNombretour);
    }

    public Date getFechaSalida() {
        return (Date) turFechasalida.get();
    }

    public void setFechaSalida(Date pTurFechasalida) {
        this.turFechasalida.set(pTurFechasalida);
    }

    public Date getFechaRegreso() {
        return (Date) turFecharegreso.get();
    }

    public void setFechaRegreso(Date pTurFecharegreso) {
        this.turFecharegreso.set(pTurFecharegreso);
    }

    public String getTurCostos() {
        return turCostos.get();
    }

    public void setTurCostos(String pTurCostos) {
        this.turCostos.set(pTurCostos);
    }

    public void setTurIdempresa(String turIdempresa) {
        this.turIdempresa.set(turIdempresa);
    }

    public void setTurCodigotipotours(String turCodigotipotours) {
        this.turCodigotipotours.setValue(turCodigotipotours);
    }



        @Override
    public int hashCode() {
        int hash = 0;
        hash += (turIdtour != null ? turIdtour.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TourDTO)) {
            return false;
        }
        TourDTO other = (TourDTO) object;
        return Objects.equals(this.turIdtour.get(), other.turIdtour.get());
    }

    @Override
    public String toString() {
        return "TourDTO{" + "turIdtour=" + turIdtour + ", turNombretour=" + turNombretour + '}';
    }
}
    


    
