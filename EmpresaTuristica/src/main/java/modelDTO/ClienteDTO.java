/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDTO;

import java.sql.Date;
import java.util.Objects;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Cliente;

/**
 *
 * @author jitor
 */
public class ClienteDTO {
    
    
    public SimpleStringProperty cliIdcliente;
    public SimpleStringProperty cliNombre;
    public SimpleStringProperty cliPapellido;
    public SimpleStringProperty cliSapellido;
    public SimpleStringProperty cliTelefono;
    public SimpleStringProperty cliCorreo;
    public SimpleObjectProperty cliFecnacimiento;
    public SimpleStringProperty cliIdtours;
    
    public ClienteDTO(){
        
        this.cliIdcliente = new SimpleStringProperty();
        this.cliNombre = new SimpleStringProperty();
        this.cliPapellido = new SimpleStringProperty();
        this.cliSapellido = new SimpleStringProperty();
        this.cliTelefono = new SimpleStringProperty();
        this.cliCorreo = new SimpleStringProperty();
        this.cliFecnacimiento = new SimpleObjectProperty();
//         this.cliIdtours = new SimpleStringProperty();
    }
    
    
     public ClienteDTO(Cliente cliente){
         
        this();
        this.cliIdcliente.set(cliente.getCliIdcliente().toString());
        this.cliNombre.set(cliente.getCliNombre());
        this.cliPapellido.set(cliente.getCliPapellido());
        this.cliSapellido.set(cliente.getCliSapellido());
        this.cliTelefono.set(cliente.getCliTelefono());
        this.cliCorreo.set(cliente.getCliCorreo());
        this.cliFecnacimiento.set(cliente.getCliFecnacimiento());
//      this.cliIdtours.set(cliente.getCliIdtours().getTurIdtour().toString());
     }
     
     
    public Integer getClienteId() {
        if(cliIdcliente.get()!=null && !cliIdcliente.get().isEmpty())
            return Integer.valueOf(cliIdcliente.get());
        else
            return null;
    }

    public String getCliNombre() {
        return cliNombre.get();
    }

    public String getCliPapellido() {
        return cliPapellido.get();
    }

    public String getCliSapellido() {
        return cliSapellido.get();
    }

    public String getCliTelefono() {
        return cliTelefono.get();
    }

    public String getCliCorreo() {
        return cliCorreo.get();
    }

    public Date getCliFecnacimiento() {
        return (Date) cliFecnacimiento.get();
    }

    public String getCliIdtours() {
        return cliIdtours.get();
    }
    
    

    public void setClienteId(Integer pCliIdcliente) {
        this.cliIdcliente.set(pCliIdcliente.toString());
    }

    public void setCliNombre(String cliNombre) {
        this.cliNombre.set(cliNombre);
    }

    public void setCliPapellido(String cliPapellido) {
        this.cliPapellido.set(cliPapellido);
    }

    public void setCliSapellido(String cliSapellido) {
        this.cliSapellido.set(cliSapellido);
    }

    public void setCliTelefono(String cliTelefono) {
        this.cliTelefono.set(cliTelefono);
    }

    public void setCliCorreo(String cliCorreo) {
        this.cliCorreo.set(cliCorreo);
    }

    public void setCliFecnacimiento(Date cliFecnacimiento) {
        this.cliFecnacimiento.set(cliFecnacimiento);
    }

    public void setCliIdtours(String cliIdtours) {
        this.cliIdtours.set(cliIdtours);
    }
    
    
    
 @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliIdcliente != null ? cliIdcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ClienteDTO)) {
            return false;
        }
        ClienteDTO other = (ClienteDTO) object;
        return Objects.equals(this.cliIdcliente.get(), other.cliIdcliente.get());
    }

    @Override
    public String toString() {
        return "ClienteDTO{" + "cliIdcliente=" + cliIdcliente + ", cliNombre=" + cliNombre + '}';
    }
    
}
