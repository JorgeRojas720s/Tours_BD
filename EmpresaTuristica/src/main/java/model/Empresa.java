/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author jitor
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_EMPRESA")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @javax.persistence.NamedQuery(name = "Empresa.findByEmpCedjuridica", query = "SELECT e FROM Empresa e WHERE e.empCedjuridica = :empCedjuridica"),
    @javax.persistence.NamedQuery(name = "Empresa.findByEmpNombre", query = "SELECT e FROM Empresa e WHERE e.empNombre = :empNombre"),
    @javax.persistence.NamedQuery(name = "Empresa.findByEmpTelefono", query = "SELECT e FROM Empresa e WHERE e.empTelefono = :empTelefono"),
    @javax.persistence.NamedQuery(name = "Empresa.findByEmpCorreo", query = "SELECT e FROM Empresa e WHERE e.empCorreo = :empCorreo"),
    @javax.persistence.NamedQuery(name = "Empresa.findByEmpFechafund", query = "SELECT e FROM Empresa e WHERE e.empFechafund = :empFechafund"),
    @javax.persistence.NamedQuery(name = "Empresa.findByEmpCalificacion", query = "SELECT e FROM Empresa e WHERE e.empCalificacion = :empCalificacion")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "EMP_CEDJURIDICA")
    private Integer empCedjuridica;
    @javax.persistence.Column(name = "EMP_NOMBRE")
    private String empNombre;
    @javax.persistence.Column(name = "EMP_TELEFONO")
    private String empTelefono;
    @javax.persistence.Column(name = "EMP_CORREO")
    private String empCorreo;
    @javax.persistence.Column(name = "EMP_FECHAFUND")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date empFechafund;
    @javax.persistence.Column(name = "EMP_CALIFICACION")
    private String empCalificacion;
    @javax.persistence.OneToMany(mappedBy = "turIdempresa")
    private Collection<Tour> tourCollection;

    public Empresa() {
    }
    
    public Empresa(Integer pCedulaJuridica, String pEmpNombre, String pEmpTelefono, 
        String pEmpCorreo, Date pEmpFechaFund,String pEmpCalificacion) {
        
        this.empCedjuridica = pCedulaJuridica;
        this.empNombre = pEmpNombre;
        this.empTelefono = pEmpTelefono;
        this.empCorreo = pEmpCorreo;
        this.empFechafund = pEmpFechaFund;
        this.empCalificacion = pEmpCalificacion;
        
    }


    public Empresa(Integer empCedjuridica) {
        this.empCedjuridica = empCedjuridica;
    }

    public Integer getEmpCedjuridica() {
        return empCedjuridica;
    }

    public void setEmpCedjuridica(Integer empCedjuridica) {
        this.empCedjuridica = empCedjuridica;
    }

    public String getEmpNombre() {
        return empNombre;
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    public String getEmpTelefono() {
        return empTelefono;
    }

    public void setEmpTelefono(String empTelefono) {
        this.empTelefono = empTelefono;
    }

    public String getEmpCorreo() {
        return empCorreo;
    }

    public void setEmpCorreo(String empCorreo) {
        this.empCorreo = empCorreo;
    }

    public Date getEmpFechafund() {
        return empFechafund;
    }

    public void setEmpFechafund(Date empFechafund) {
        this.empFechafund = empFechafund;
    }

    public String getEmpCalificacion() {
        return empCalificacion;
    }

    public void setEmpCalificacion(String empCalificacion) {
        this.empCalificacion = empCalificacion;
    }

    public Collection<Tour> getTourCollection() {
        return tourCollection;
    }

    public void setTourCollection(Collection<Tour> tourCollection) {
        this.tourCollection = tourCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empCedjuridica != null ? empCedjuridica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.empCedjuridica == null && other.empCedjuridica != null) || (this.empCedjuridica != null && !this.empCedjuridica.equals(other.empCedjuridica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Empresa[ empCedjuridica=" + empCedjuridica + " ]";
    }
    
}
