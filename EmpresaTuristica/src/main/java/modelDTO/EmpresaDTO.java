/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDTO;

import java.sql.Date;
import java.util.Objects;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Empresa;

/**
 *
 * @author jitor
 */
public class EmpresaDTO {
    
    public SimpleStringProperty empCedjuridica;
    public SimpleStringProperty empNombre;
    public SimpleStringProperty empTelefono;
    public SimpleStringProperty empCorreo;
    public  SimpleObjectProperty empFechafund;
    public SimpleStringProperty empCalificacion;

    
    public EmpresaDTO(){
      
        this.empCedjuridica = new SimpleStringProperty();
        this.empNombre = new SimpleStringProperty();
        this.empTelefono = new  SimpleStringProperty();
        this.empCorreo = new SimpleStringProperty();
        this.empFechafund = new SimpleObjectProperty();
        this.empCalificacion = new SimpleStringProperty();
    }
    
    
    public EmpresaDTO(Empresa empresa) {
        this();
      
        this.empCedjuridica.set(empresa.getEmpCedjuridica().toString());
        this.empNombre.set(empresa.getEmpNombre());
        this.empTelefono.set(empresa.getEmpTelefono());
        this.empCorreo.set(empresa.getEmpCorreo());
        this.empFechafund.set(empresa.getEmpFechafund());
        this.empCalificacion.set(empresa.getEmpCalificacion());
    }

    //Getters
   
    public Integer getEmpCedjuridica() {
        if(empCedjuridica.get()!=null && !empCedjuridica.get().isEmpty())
            return Integer.valueOf(empCedjuridica.get());
        else
            return null;
    }
 
    public String getEmpNombre() {
        return empNombre.get();
    }

    public String getEmpTelefono() {
        return empTelefono.get();
    }

    public String getEmpCorreo() {
        return empCorreo.get();
    }

    public Date getEmpFechafund() {
        return (Date) empFechafund.get();
    }

    public String getEmpCalificacion() {
        return empCalificacion.get();
    }
    
 
    //Setters

    public void setEmpCedjuridica(Integer pEmpCedjuridica) {
        this.empCedjuridica.set(pEmpCedjuridica.toString());
    }

    public void setEmpNombre(String pEmpNombre) {
        this.empNombre.set(pEmpNombre);
    }

    public void setEmpTelefono(String pEmpTelefono) {
        this.empTelefono.set(pEmpTelefono);
    }

    public void setEmpCorreo(String pEmpCorreo) {
        this.empCorreo.set(pEmpCorreo);
    }

    public void setEmpFechafund(Date pEmpFechafund) {
        this.empFechafund.set(pEmpFechafund);
    }

    public void setEmpCalificacion(String pEmpCalificacion) {
        this.empCalificacion.set(pEmpCalificacion);
    }

    

        @Override
    public int hashCode() {
        int hash = 0;
        hash += (empCedjuridica != null ? empCedjuridica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EmpresaDTO)) {
            return false;
        }
        EmpresaDTO other = (EmpresaDTO) object;
        return Objects.equals(this.empCedjuridica.get(), other.empCedjuridica.get());
    }

    @Override
    public String toString() {
        return "EmpresaDTO{" + "empCedjuridica=" + empCedjuridica + ", empNombre=" + empNombre + '}';
    }

}
