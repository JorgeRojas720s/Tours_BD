/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDTO;

import java.sql.Date;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Empresa;
import model.Reserva;

/**
 *
 * @author jitor
 */
public class ReservaDTO {
    
    public SimpleStringProperty resIdreserva;
    public SimpleObjectProperty resFecreserva;
    public SimpleStringProperty resCantpersonas;
    public SimpleStringProperty resCosto;
    public SimpleStringProperty resMontosabonados;
    
    public SimpleStringProperty resIdcliente;
    public SimpleStringProperty resIdtour;


     public ReservaDTO(){
      
        this.resIdreserva = new SimpleStringProperty();
        this.resFecreserva = new SimpleObjectProperty();
        this.resCantpersonas = new SimpleStringProperty();
        this.resCosto = new SimpleStringProperty();
        this.resMontosabonados = new SimpleStringProperty();
        
        this.resIdcliente = new SimpleStringProperty();
        this.resIdtour = new SimpleStringProperty();

    }
     
    public ReservaDTO(Reserva reserva) {
        this();
      
        this.resIdreserva.set(reserva.getResIdreserva().toString());
        this.resFecreserva.set(reserva.getResFecreserva());
        this.resCantpersonas.set(reserva.getResCantpersonas().toString());
        this.resCosto.set(reserva.getResCosto().toString());
        this.resMontosabonados.set(reserva.getResMontosabonados().toString());
        
        this.resIdcliente.set(reserva.getResIdcliente().getCliIdcliente().toString());
        this.resIdtour.set(reserva.getResIdtour().getTurIdtour().toString());     
    }

    public Integer getReservaId() {
        if(resIdreserva.get()!=null && !resIdreserva.get().isEmpty())
            return Integer.valueOf(resIdreserva.get());
        else
            return null;
    }

    public Date getResFecreserva() {
        return (Date) resFecreserva.get();
    }

    public String getResCantpersonas() {
        return resCantpersonas.get();
    }

    public String getResCosto() {
        return resCosto.get();
    }

    public String getResMontosabonados() {
        return resMontosabonados.get();
    }

    public String getResIdCliente() {
        return resIdcliente.get();
    }

    public String getResIdtour() {
        return resIdtour.get();
    }
    


    public void setReservaId(Integer pResIdreserva) {
        this.resIdreserva.set(pResIdreserva.toString());
    }

    public void setResFecreserva(Date resFecreserva) {
        this.resFecreserva.set(resFecreserva);
    }

    public void setResCantpersonas(String resCantpersonas) {
        this.resCantpersonas.set(resCantpersonas);
    }

    public void setResCosto(String resCosto) {
        this.resCosto.set(resCosto);
    }

    public void setResMontosabonados(String resMontosabonados) {
        this.resMontosabonados.set(resMontosabonados);
    }

    public void setResIdCliente(String resIdcliente) {
        this.resIdcliente.set(resIdcliente);
    }

    public void setResIdtour(String resIdtour) {
        this.resIdtour.set(resIdtour);
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (resIdreserva != null ? resIdreserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ReservaDTO other = (ReservaDTO) obj;
        return resIdreserva != null ? resIdreserva.equals(other.resIdreserva) : other.resIdreserva == null;
    }

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "resIdreserva=" + resIdreserva +
                ", resFecreserva=" + resFecreserva +
                ", resCantpersonas=" + resCantpersonas +
                ", resCosto=" + resCosto +
                ", resMontosabonados=" + resMontosabonados +
                '}';
    }
    
    
}
