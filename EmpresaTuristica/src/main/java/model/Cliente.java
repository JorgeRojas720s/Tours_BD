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
@javax.persistence.Table(name = "TBL_CLIENTES")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @javax.persistence.NamedQuery(name = "Cliente.findByCliIdcliente", query = "SELECT c FROM Cliente c WHERE c.cliIdcliente = :cliIdcliente"),
    @javax.persistence.NamedQuery(name = "Cliente.findByCliNombre", query = "SELECT c FROM Cliente c WHERE c.cliNombre = :cliNombre"),
    @javax.persistence.NamedQuery(name = "Cliente.findByCliPapellido", query = "SELECT c FROM Cliente c WHERE c.cliPapellido = :cliPapellido"),
    @javax.persistence.NamedQuery(name = "Cliente.findByCliSapellido", query = "SELECT c FROM Cliente c WHERE c.cliSapellido = :cliSapellido"),
    @javax.persistence.NamedQuery(name = "Cliente.findByCliTelefono", query = "SELECT c FROM Cliente c WHERE c.cliTelefono = :cliTelefono"),
    @javax.persistence.NamedQuery(name = "Cliente.findByCliCorreo", query = "SELECT c FROM Cliente c WHERE c.cliCorreo = :cliCorreo"),
    @javax.persistence.NamedQuery(name = "Cliente.findByCliFecnacimiento", query = "SELECT c FROM Cliente c WHERE c.cliFecnacimiento = :cliFecnacimiento")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CLI_IDCLIENTE")
    private Integer cliIdcliente;
    @javax.persistence.Column(name = "CLI_NOMBRE")
    private String cliNombre;
    @javax.persistence.Column(name = "CLI_PAPELLIDO")
    private String cliPapellido;
    @javax.persistence.Column(name = "CLI_SAPELLIDO")
    private String cliSapellido;
    @javax.persistence.Column(name = "CLI_TELEFONO")
    private String cliTelefono;
    @javax.persistence.Column(name = "CLI_CORREO")
    private String cliCorreo;
    @javax.persistence.Column(name = "CLI_FECNACIMIENTO")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date cliFecnacimiento;
    @javax.persistence.JoinColumn(name = "CLI_IDTOURS", referencedColumnName = "TUR_IDTOUR")
    @javax.persistence.ManyToOne
    private Tour cliIdtours;
    @javax.persistence.OneToMany(mappedBy = "resIdcliente")
    private Collection<Reserva> reservaCollection;

    public Cliente() {
    }
    
    public Cliente(Integer pCedula, String pNombre,String pPApellido,String pSApellido, 
    String pTelefono,String pCorreo, Date pFecNac) {
        
        this.cliIdcliente = pCedula;
        this.cliNombre = pNombre;
        this.cliPapellido = pPApellido;
        this.cliSapellido = pSApellido;
        this.cliTelefono = pTelefono;
        this.cliCorreo = pCorreo;
        this.cliFecnacimiento = pFecNac;
        
    }

    public Cliente(Integer cliIdcliente) {
        this.cliIdcliente = cliIdcliente;
    }

    public Integer getCliIdcliente() {
        return cliIdcliente;
    }

    public void setCliIdcliente(Integer cliIdcliente) {
        this.cliIdcliente = cliIdcliente;
    }

    public String getCliNombre() {
        return cliNombre;
    }

    public void setCliNombre(String cliNombre) {
        this.cliNombre = cliNombre;
    }

    public String getCliPapellido() {
        return cliPapellido;
    }

    public void setCliPapellido(String cliPapellido) {
        this.cliPapellido = cliPapellido;
    }

    public String getCliSapellido() {
        return cliSapellido;
    }

    public void setCliSapellido(String cliSapellido) {
        this.cliSapellido = cliSapellido;
    }

    public String getCliTelefono() {
        return cliTelefono;
    }

    public void setCliTelefono(String cliTelefono) {
        this.cliTelefono = cliTelefono;
    }

    public String getCliCorreo() {
        return cliCorreo;
    }

    public void setCliCorreo(String cliCorreo) {
        this.cliCorreo = cliCorreo;
    }

    public Date getCliFecnacimiento() {
        return cliFecnacimiento;
    }

    public void setCliFecnacimiento(Date cliFecnacimiento) {
        this.cliFecnacimiento = cliFecnacimiento;
    }

    public Tour getCliIdtours() {
        return cliIdtours;
    }

    public void setCliIdtours(Tour cliIdtours) {
        this.cliIdtours = cliIdtours;
    }

    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliIdcliente != null ? cliIdcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.cliIdcliente == null && other.cliIdcliente != null) || (this.cliIdcliente != null && !this.cliIdcliente.equals(other.cliIdcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cliente[ cliIdcliente=" + cliIdcliente + " ]";
    }
    
}
