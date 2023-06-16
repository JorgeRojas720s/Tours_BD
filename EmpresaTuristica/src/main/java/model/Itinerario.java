/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jitor
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_ITINERARIO")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Itinerario.findAll", query = "SELECT i FROM Itinerario i"),
    @javax.persistence.NamedQuery(name = "Itinerario.findByItiIditinerario", query = "SELECT i FROM Itinerario i WHERE i.itiIditinerario = :itiIditinerario"),
    @javax.persistence.NamedQuery(name = "Itinerario.findByItiLugar", query = "SELECT i FROM Itinerario i WHERE i.itiLugar = :itiLugar"),
    @javax.persistence.NamedQuery(name = "Itinerario.findByItiFecllegada", query = "SELECT i FROM Itinerario i WHERE i.itiFecllegada = :itiFecllegada"),
    @javax.persistence.NamedQuery(name = "Itinerario.findByItiFecsalida", query = "SELECT i FROM Itinerario i WHERE i.itiFecsalida = :itiFecsalida"),
    @javax.persistence.NamedQuery(name = "Itinerario.findByItiDuracion", query = "SELECT i FROM Itinerario i WHERE i.itiDuracion = :itiDuracion"),
    @javax.persistence.NamedQuery(name = "Itinerario.findByItiDescpActividades", query = "SELECT i FROM Itinerario i WHERE i.itiDescpActividades = :itiDescpActividades"),
    @javax.persistence.NamedQuery(name = "Itinerario.findByItiHorallegada", query = "SELECT i FROM Itinerario i WHERE i.itiHorallegada = :itiHorallegada"),
    @javax.persistence.NamedQuery(name = "Itinerario.findByItiHorasalida", query = "SELECT i FROM Itinerario i WHERE i.itiHorasalida = :itiHorasalida")})
public class Itinerario implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "ITI_IDITINERARIO")
    private Integer itiIditinerario;
    @javax.persistence.Column(name = "ITI_LUGAR")
    private String itiLugar;
    @javax.persistence.Column(name = "ITI_FECLLEGADA")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date itiFecllegada;
    @javax.persistence.Column(name = "ITI_FECSALIDA")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date itiFecsalida;
    @javax.persistence.Column(name = "ITI_DURACION")
    private String itiDuracion;
    @javax.persistence.Column(name = "ITI_DESCP_ACTIVIDADES")
    private String itiDescpActividades;
    @javax.persistence.Column(name = "ITI_HORALLEGADA")
    private String itiHorallegada;
    @javax.persistence.Column(name = "ITI_HORASALIDA")
    private String itiHorasalida;
    @javax.persistence.JoinColumn(name = "ITI_IDTOURS", referencedColumnName = "TUR_IDTOUR")
    @javax.persistence.ManyToOne(optional = false)
    private Tour itiIdtours;

    public Itinerario() {
    }
    
    public Itinerario(Integer pIdItinerario, String pLugar, Date pFecLlegada, 
        Date pFecSalid, String pDuracion,String pDescpActividades, String pHoraLlegada,String pHoraSalida) {
        
        this.itiIditinerario = pIdItinerario;
        this.itiLugar = pLugar;
        this.itiFecllegada = pFecLlegada;
        this.itiFecsalida = pFecSalid;
        this.itiDuracion = pDuracion;
        this.itiDescpActividades = pDescpActividades;
        this.itiHorallegada = pHoraLlegada;
        this.itiHorasalida = pHoraSalida;
        
    }

    public Itinerario(Integer itiIditinerario) {
        this.itiIditinerario = itiIditinerario;
    }

    public Integer getItiIditinerario() {
        return itiIditinerario;
    }

    public void setItiIditinerario(Integer itiIditinerario) {
        this.itiIditinerario = itiIditinerario;
    }

    public String getItiLugar() {
        return itiLugar;
    }

    public void setItiLugar(String itiLugar) {
        this.itiLugar = itiLugar;
    }

    public Date getItiFecllegada() {
        return itiFecllegada;
    }

    public void setItiFecllegada(Date itiFecllegada) {
        this.itiFecllegada = itiFecllegada;
    }

    public Date getItiFecsalida() {
        return itiFecsalida;
    }

    public void setItiFecsalida(Date itiFecsalida) {
        this.itiFecsalida = itiFecsalida;
    }

    public String getItiDuracion() {
        return itiDuracion;
    }

    public void setItiDuracion(String itiDuracion) {
        this.itiDuracion = itiDuracion;
    }

    public String getItiDescpActividades() {
        return itiDescpActividades;
    }

    public void setItiDescpActividades(String itiDescpActividades) {
        this.itiDescpActividades = itiDescpActividades;
    }

    public String getItiHorallegada() {
        return itiHorallegada;
    }

    public void setItiHorallegada(String itiHorallegada) {
        this.itiHorallegada = itiHorallegada;
    }

    public String getItiHorasalida() {
        return itiHorasalida;
    }

    public void setItiHorasalida(String itiHorasalida) {
        this.itiHorasalida = itiHorasalida;
    }

    public Tour getItiIdtours() {
        return itiIdtours;
    }

    public void setItiIdtours(Tour itiIdtours) {
        this.itiIdtours = itiIdtours;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itiIditinerario != null ? itiIditinerario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itinerario)) {
            return false;
        }
        Itinerario other = (Itinerario) object;
        if ((this.itiIditinerario == null && other.itiIditinerario != null) || (this.itiIditinerario != null && !this.itiIditinerario.equals(other.itiIditinerario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Itinerario[ itiIditinerario=" + itiIditinerario + " ]";
    }
    
}
