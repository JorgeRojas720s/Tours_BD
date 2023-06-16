/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDTO;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import model.TipoTour;

/**
 *
 * @author jitor
 */
public class TipoTourDTO {
  
    public SimpleStringProperty tptCodigo;
    public SimpleStringProperty tptTipotour;
    public SimpleStringProperty tptNombre;
    public SimpleStringProperty tptPais;

    public TipoTourDTO(){
        
        this.tptCodigo = new SimpleStringProperty();
        this.tptTipotour = new SimpleStringProperty();
        this.tptNombre = new SimpleStringProperty();
        this.tptPais = new SimpleStringProperty();   
    }

    public TipoTourDTO(TipoTour tipoTours){
        
        this();
        this.tptCodigo.set(tipoTours.getTptCodigo().toString());
        this.tptTipotour.set(tipoTours.getTptTipotour());
        this.tptNombre.set(tipoTours.getTptNombre());
        this.tptPais.set(tipoTours.getTptPais());
    }
    
    //Getters
    
    public Integer getTipoToursId() {
        if(tptCodigo.get()!=null && !tptCodigo.get().isEmpty())
            return Integer.valueOf(tptCodigo.get());
        else
            return null;
    }

    public String getTptTipotour() {
        return tptTipotour.get();
    }

    public String getTptNombre() {
        return tptNombre.get();
    }

    public String getTptPais() {
        return tptPais.get();
    }

    //Setters
    
    public void setTipoToursId(Integer pTptCodigo) {
        this.tptCodigo.set(pTptCodigo.toString());
    }

    public void setTptTipotour(String tptTipotour) {
        this.tptTipotour.set(tptTipotour);
    }

    public void setTptNombre(String tptNombre) {
        this.tptNombre.set(tptNombre);
    }

    public void setTptPais(String tptPais) {
        this.tptPais.set(tptPais);
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tptCodigo != null ? tptCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoTourDTO)) {
            return false;
        }
        TipoTourDTO other = (TipoTourDTO) object;
        return Objects.equals(this.tptCodigo.get(), other.tptCodigo.get());
    }

    @Override
    public String toString() {
        return "TipoToursDTO{" + "tptCodigo=" + tptCodigo + ", tptTipotour=" + tptTipotour + '}';
    }
 
}
