/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author jitor
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_TOURS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Tour.findAll", query = "SELECT t FROM Tour t"),
    @javax.persistence.NamedQuery(name = "Tour.findByTurIdtour", query = "SELECT t FROM Tour t WHERE t.turIdtour = :turIdtour"),
    @javax.persistence.NamedQuery(name = "Tour.findByTurNombretour", query = "SELECT t FROM Tour t WHERE t.turNombretour = :turNombretour"),
    @javax.persistence.NamedQuery(name = "Tour.findByTurFechasalida", query = "SELECT t FROM Tour t WHERE t.turFechasalida = :turFechasalida"),
    @javax.persistence.NamedQuery(name = "Tour.findByTurFecharegreso", query = "SELECT t FROM Tour t WHERE t.turFecharegreso = :turFecharegreso"),
    @javax.persistence.NamedQuery(name = "Tour.findByTurCostos", query = "SELECT t FROM Tour t WHERE t.turCostos = :turCostos")})
public class Tour implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TUR_IDTOUR")
    private Integer turIdtour;
    @javax.persistence.Column(name = "TUR_NOMBRETOUR")
    private String turNombretour;
    @javax.persistence.Column(name = "TUR_FECHASALIDA")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date turFechasalida;
    @javax.persistence.Column(name = "TUR_FECHAREGRESO")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date turFecharegreso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @javax.persistence.Column(name = "TUR_COSTOS")
    private BigDecimal turCostos;
    @javax.persistence.OneToMany(mappedBy = "cliIdtours")
    private Collection<Cliente> clienteCollection;
    @javax.persistence.JoinColumn(name = "TUR_IDEMPRESA", referencedColumnName = "EMP_CEDJURIDICA")
    @javax.persistence.ManyToOne
    private Empresa turIdempresa;
    @javax.persistence.JoinColumn(name = "TUR_CODIGOTIPOTOURS", referencedColumnName = "TPT_CODIGO")
    @javax.persistence.ManyToOne(optional = false)
    private TipoTour turCodigotipotours;
    @javax.persistence.OneToMany(mappedBy = "resIdtour")
    private Collection<Reserva> reservaCollection;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "itiIdtours")
    private Collection<Itinerario> itinerarioCollection;

    public Tour() {
    }
    
    public Tour(Integer pIdTour, String pNombreTour, Date pTourFecSalida, 
        Date pTourFecRegreso, BigDecimal pCostosTour) {
        
        this.turIdtour = pIdTour;
        this.turNombretour = pNombreTour;
        this.turFechasalida = pTourFecSalida;
        this.turFecharegreso = pTourFecRegreso;
        this.turCostos = pCostosTour;
     //   this.turIdempresa.equals(pIdEmpresa);
    
    }

    public Tour(Integer turIdtour) {
        this.turIdtour = turIdtour;
    }

    public Integer getTurIdtour() {
        return turIdtour;
    }

    public void setTurIdtour(Integer turIdtour) {
        this.turIdtour = turIdtour;
    }

    public String getTurNombretour() {
        return turNombretour;
    }

    public void setTurNombretour(String turNombretour) {
        this.turNombretour = turNombretour;
    }

    public Date getTurFechasalida() {
        return turFechasalida;
    }

    public void setTurFechasalida(Date turFechasalida) {
        this.turFechasalida = turFechasalida;
    }

    public Date getTurFecharegreso() {
        return turFecharegreso;
    }

    public void setTurFecharegreso(Date turFecharegreso) {
        this.turFecharegreso = turFecharegreso;
    }

    public BigDecimal getTurCostos() {
        return turCostos;
    }

    public void setTurCostos(BigDecimal turCostos) {
        this.turCostos = turCostos;
    }

    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    public Empresa getTurIdempresa() {
        return turIdempresa;
    }

    public void setTurIdempresa(Empresa turIdempresa) {
        this.turIdempresa = turIdempresa;
    }

    public TipoTour getTurCodigotipotours() {
        return turCodigotipotours;
    }

    public void setTurCodigotipotours(TipoTour turCodigotipotours) {
        this.turCodigotipotours = turCodigotipotours;
    }

    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
    }

    public Collection<Itinerario> getItinerarioCollection() {
        return itinerarioCollection;
    }

    public void setItinerarioCollection(Collection<Itinerario> itinerarioCollection) {
        this.itinerarioCollection = itinerarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (turIdtour != null ? turIdtour.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tour)) {
            return false;
        }
        Tour other = (Tour) object;
        if ((this.turIdtour == null && other.turIdtour != null) || (this.turIdtour != null && !this.turIdtour.equals(other.turIdtour))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Tour[ turIdtour=" + turIdtour + " ]";
    }
    
}
