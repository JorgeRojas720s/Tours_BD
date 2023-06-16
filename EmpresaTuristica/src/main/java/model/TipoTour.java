/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author jitor
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_TIPOTOURS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Tipotour.findAll", query = "SELECT t FROM Tipotour t"),
    @javax.persistence.NamedQuery(name = "Tipotour.findByTptCodigo", query = "SELECT t FROM Tipotour t WHERE t.tptCodigo = :tptCodigo"),
    @javax.persistence.NamedQuery(name = "Tipotour.findByTptTipotour", query = "SELECT t FROM Tipotour t WHERE t.tptTipotour = :tptTipotour"),
    @javax.persistence.NamedQuery(name = "Tipotour.findByTptNombre", query = "SELECT t FROM Tipotour t WHERE t.tptNombre = :tptNombre"),
    @javax.persistence.NamedQuery(name = "Tipotour.findByTptPais", query = "SELECT t FROM Tipotour t WHERE t.tptPais = :tptPais")})
public class TipoTour implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TPT_CODIGO")
    private Integer tptCodigo;
    @javax.persistence.Column(name = "TPT_TIPOTOUR")
    private String tptTipotour;
    @javax.persistence.Column(name = "TPT_NOMBRE")
    private String tptNombre;
    @javax.persistence.Column(name = "TPT_PAIS")
    private String tptPais;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "turCodigotipotours")
    private Collection<Tour> tourCollection;

    public TipoTour() {
    }

    
    public TipoTour(Integer tptCodigo, String tptTipoTour, String tptNombre, 
        String tptPais) {
        
        this.tptCodigo = tptCodigo;
        this.tptTipotour = tptTipoTour;
        this.tptNombre = tptNombre;
        this.tptPais = tptPais;
    }
    
    public TipoTour(Integer tptCodigo) {
        this.tptCodigo = tptCodigo;
    }

    public Integer getTptCodigo() {
        return tptCodigo;
    }

    public void setTptCodigo(Integer tptCodigo) {
        this.tptCodigo = tptCodigo;
    }

    public String getTptTipotour() {
        return tptTipotour;
    }

    public void setTptTipotour(String tptTipotour) {
        this.tptTipotour = tptTipotour;
    }

    public String getTptNombre() {
        return tptNombre;
    }

    public void setTptNombre(String tptNombre) {
        this.tptNombre = tptNombre;
    }

    public String getTptPais() {
        return tptPais;
    }

    public void setTptPais(String tptPais) {
        this.tptPais = tptPais;
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
        hash += (tptCodigo != null ? tptCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTour)) {
            return false;
        }
        TipoTour other = (TipoTour) object;
        if ((this.tptCodigo == null && other.tptCodigo != null) || (this.tptCodigo != null && !this.tptCodigo.equals(other.tptCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Tipotour[ tptCodigo=" + tptCodigo + " ]";
    }
    
}
