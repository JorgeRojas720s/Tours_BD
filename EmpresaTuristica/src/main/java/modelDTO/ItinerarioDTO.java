/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDTO;

import java.sql.Date;
import java.util.Objects;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Itinerario;

/**
 *
 * @author jitor
 */
public class ItinerarioDTO {
    
    
    public SimpleStringProperty itiIditinerario;
    public SimpleStringProperty itiLugar;
    public SimpleObjectProperty itiFecllegada;
    public SimpleObjectProperty itiFecsalida;
    public SimpleStringProperty itiDuracion;
    public SimpleStringProperty itiDescpActividades;
    public SimpleStringProperty itiHorallegada;
    public SimpleStringProperty itiHorasalida;
    public SimpleStringProperty itiIdtours;
    
    public ItinerarioDTO(){
  
        this.itiIditinerario = new SimpleStringProperty();
        this.itiLugar = new SimpleStringProperty();
        this.itiFecllegada = new SimpleObjectProperty();
        this.itiFecsalida = new SimpleObjectProperty();
        this.itiDuracion = new SimpleStringProperty();
        this.itiDescpActividades = new SimpleStringProperty();
        this.itiHorallegada = new SimpleStringProperty();
        this.itiHorasalida = new SimpleStringProperty();
        this.itiIdtours = new SimpleStringProperty();
  
    }
    
    public ItinerarioDTO(Itinerario itinerario){
        
        this();
        this.itiIditinerario.set(itinerario.getItiIditinerario().toString());
        this.itiLugar.set(itinerario.getItiLugar());
        this.itiFecllegada.set(itinerario.getItiFecllegada());
        this.itiFecsalida.set(itinerario.getItiFecsalida());
        this.itiDuracion.set(itinerario.getItiDuracion());
        this.itiDescpActividades.set(itinerario.getItiDescpActividades());
        this.itiHorallegada.set(itinerario.getItiHorallegada());
        this.itiHorasalida.set(itinerario.getItiHorasalida());
        this.itiIdtours.set(itinerario.getItiIdtours().getTurIdtour().toString());
    }
    
    public Integer getItinerarioId() {
        if(itiIditinerario.get()!=null && !itiIditinerario.get().isEmpty())
            return Integer.valueOf(itiIditinerario.get());
        else
            return null;
    }

    public String getItiLugar() {
        return itiLugar.get();
    }

    public Date getItiFechorallegada() {
        return (Date) itiFecllegada.get();
    }

    public Date getItiFechorasalida() {
        return (Date) itiFecsalida.get();
    }

    public String getItiDuracion() {
        return itiDuracion.get();
    }

    public String getItiDescpActividades() {
        return itiDescpActividades.get();
    }

    public String getItiHorallegada() {
        return itiHorallegada.get();
    }

    public String getItiHorasalida() {
        return itiHorasalida.get();
    }

    public String getItiIdtours() {
        return itiIdtours.get();
    }
    
    
 

    public void setItinerarioId(Integer pItiIditinerario) {
        this.itiIditinerario.set(pItiIditinerario.toString());
    }

    public void setItiLugar(String itiLugar) {
        this.itiLugar.set(itiLugar);
    }

    public void setItiFechorallegada(Date itiFechorallegada) {
        this.itiFecllegada.set(itiFechorallegada);
    }

    public void setItiFechorasalida(Date itiFechorasalida) {
        this.itiFecsalida.set(itiFechorasalida);
    }

    public void setItiDuracion(String itiDuracion) {
        this.itiDuracion.set(itiDuracion);
    }

    public void setItiDescpActividades(String itiDescpActividades) {
        this.itiDescpActividades.set(itiDescpActividades);
    }

    public void setItiHorallegada(String itiHorallegada) {
        this.itiHorallegada.set(itiHorallegada);
    }

    public void setItiHorasalida(String itiHorasalida) {
        this.itiHorasalida.set(itiHorasalida);
    }

    public void setItiIdtours(String itiIdtours) {
        this.itiIdtours.set(itiIdtours);
    }
    
    

    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (itiIditinerario != null ? itiIditinerario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ItinerarioDTO)) {
            return false;
        }
        ItinerarioDTO other = (ItinerarioDTO) object;
        return Objects.equals(this.itiIditinerario.get(), other.itiIditinerario.get());
    }

    @Override
    public String toString() {
        return "ItinerarioDTO{" + "itiIditinerario=" + itiIditinerario + ", itiLugar=" + itiLugar + '}';
    }

    
}
