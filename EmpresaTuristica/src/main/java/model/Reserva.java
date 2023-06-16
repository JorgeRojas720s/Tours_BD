/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jitor
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_RESERVA")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
    @javax.persistence.NamedQuery(name = "Reserva.findByResIdreserva", query = "SELECT r FROM Reserva r WHERE r.resIdreserva = :resIdreserva"),
    @javax.persistence.NamedQuery(name = "Reserva.findByResFecreserva", query = "SELECT r FROM Reserva r WHERE r.resFecreserva = :resFecreserva"),
    @javax.persistence.NamedQuery(name = "Reserva.findByResCantpersonas", query = "SELECT r FROM Reserva r WHERE r.resCantpersonas = :resCantpersonas"),
    @javax.persistence.NamedQuery(name = "Reserva.findByResCosto", query = "SELECT r FROM Reserva r WHERE r.resCosto = :resCosto"),
    @javax.persistence.NamedQuery(name = "Reserva.findByResMontosabonados", query = "SELECT r FROM Reserva r WHERE r.resMontosabonados = :resMontosabonados")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "RES_IDRESERVA")
    private Integer resIdreserva;
    @javax.persistence.Column(name = "RES_FECRESERVA")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date resFecreserva;
    @javax.persistence.Column(name = "RES_CANTPERSONAS")
    private Short resCantpersonas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @javax.persistence.Column(name = "RES_COSTO")
    private BigDecimal resCosto;
    @javax.persistence.Column(name = "RES_MONTOSABONADOS")
    private BigDecimal resMontosabonados;
    @javax.persistence.JoinColumn(name = "RES_IDCLIENTE", referencedColumnName = "CLI_IDCLIENTE")
    @javax.persistence.ManyToOne
    private Cliente resIdcliente;
    @javax.persistence.JoinColumn(name = "RES_IDTOUR", referencedColumnName = "TUR_IDTOUR")
    @javax.persistence.ManyToOne
    private Tour resIdtour;

    public Reserva() {
    }

    public Reserva(Integer pIdReserva, Date pFecReserva, Short pCantPersonas, 
        BigDecimal pCosto, BigDecimal pMontAbonado) {
        
        this.resIdreserva = pIdReserva;
        this.resFecreserva = pFecReserva;
        this.resCantpersonas = pCantPersonas;
        this.resCosto = pCosto;
        this.resMontosabonados = pMontAbonado;
    }
 
    public Reserva(Integer resIdreserva) {
        this.resIdreserva = resIdreserva;
    }

    public Integer getResIdreserva() {
        return resIdreserva;
    }

    public void setResIdreserva(Integer resIdreserva) {
        this.resIdreserva = resIdreserva;
    }

    public Date getResFecreserva() {
        return resFecreserva;
    }

    public void setResFecreserva(Date resFecreserva) {
        this.resFecreserva = resFecreserva;
    }

    public Short getResCantpersonas() {
        return resCantpersonas;
    }

    public void setResCantpersonas(Short resCantpersonas) {
        this.resCantpersonas = resCantpersonas;
    }

    public BigDecimal getResCosto() {
        return resCosto;
    }

    public void setResCosto(BigDecimal resCosto) {
        this.resCosto = resCosto;
    }

    public BigDecimal getResMontosabonados() {
        return resMontosabonados;
    }

    public void setResMontosabonados(BigDecimal resMontosabonados) {
        this.resMontosabonados = resMontosabonados;
    }

    public Cliente getResIdcliente() {
        return resIdcliente;
    }

    public void setResIdcliente(Cliente resIdcliente) {
        this.resIdcliente = resIdcliente;
    }

    public Tour getResIdtour() {
        return resIdtour;
    }

    public void setResIdtour(Tour resIdtour) {
        this.resIdtour = resIdtour;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resIdreserva != null ? resIdreserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.resIdreserva == null && other.resIdreserva != null) || (this.resIdreserva != null && !this.resIdreserva.equals(other.resIdreserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Reserva[ resIdreserva=" + resIdreserva + " ]";
    }
    
}
