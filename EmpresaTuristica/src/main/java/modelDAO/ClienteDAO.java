/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import modelDTO.ClienteDTO;

/**
 *
 * @author jitor
 */
public class ClienteDAO {
    
        private Connection connection;

    public ClienteDAO() {
        // Initialize the database connection
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "JRojas";
            String password = "Palomo_123";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void guardarCliente(ClienteDTO clienteDTO) {
        String sql = "INSERT INTO TBL_CLIENTES (CLI_IDCLIENTE, CLI_NOMBRE, CLI_PAPELLIDO, CLI_SAPELLIDO,"
                + "CLI_TELEFONO , CLI_CORREO, CLI_FECNACIMIENTO) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, clienteDTO.getClienteId().toString());
            statement.setString(2, clienteDTO.getCliNombre());
            statement.setString(3, clienteDTO.getCliPapellido());
            statement.setString(4, clienteDTO.getCliSapellido());
            statement.setString(5, clienteDTO.getCliTelefono());
            statement.setString(6, clienteDTO.getCliCorreo());
            statement.setDate(7, (Date)clienteDTO.getCliFecnacimiento());
            
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      
    public ClienteDTO getClientePorCedula(String idCliente) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM TBL_CLIENTES WHERE CLI_IDCLIENTE = ?");
            statement.setString(1, idCliente);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                ClienteDTO clienteDTO = new ClienteDTO();
                clienteDTO.setClienteId(Integer.valueOf(resultSet.getString("CLI_IDCLIENTE")));
                clienteDTO.setCliNombre(resultSet.getString("CLI_NOMBRE"));
                clienteDTO.setCliPapellido(resultSet.getString("CLI_PAPELLIDO"));
                clienteDTO.setCliSapellido(resultSet.getString("CLI_SAPELLIDO"));
                clienteDTO.setCliTelefono(resultSet.getString("CLI_TELEFONO"));
                clienteDTO.setCliCorreo(resultSet.getString("CLI_CORREO"));
                clienteDTO.setCliFecnacimiento(resultSet.getDate("CLI_FECNACIMIENTO"));
                return clienteDTO;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
        
    
    public void actualizarCliente(ClienteDTO clienteDTO) {
        try {
        PreparedStatement statement = connection.prepareStatement("UPDATE TBL_CLIENTES SET CLI_NOMBRE = ?, CLI_PAPELLIDO = ?, CLI_SAPELLIDO = ?, CLI_TELEFONO = ?, CLI_CORREO = ? WHERE CLI_IDCLIENTE = ?");
        statement.setString(1, clienteDTO.getCliNombre());
        statement.setString(2, clienteDTO.getCliPapellido());
        statement.setString(3, clienteDTO.getCliSapellido());
        statement.setString(4, clienteDTO.getCliTelefono());
        statement.setString(5, clienteDTO.getCliCorreo());
        statement.setInt(6, clienteDTO.getClienteId());
        
        statement.executeUpdate();
        
        statement.close();

        } catch (SQLException e) {
        e.printStackTrace();
        }
}
    
        
    public void eliminarCliente(int idCliente) {
        try {
           PreparedStatement statement = connection.prepareStatement("DELETE FROM TBL_CLIENTES WHERE CLI_IDCLIENTE = ?");
           
            statement.setInt(1, idCliente);
        
            statement.executeUpdate();
        
            statement.close();
       
        } catch (SQLException e) {
           e.printStackTrace();
        }
}

        
    public List<Cliente> obtenerClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM TBL_CLIENTES ORDER BY CLI_IDCLIENTE";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Integer cedula = resultSet.getInt("CLI_IDCLIENTE");
                String nombre = resultSet.getString("CLI_NOMBRE");
                String pApellido = resultSet.getString("CLI_PAPELLIDO");
                String sApellido = resultSet.getString("CLI_SAPELLIDO");
                String telefono = resultSet.getString("CLI_TELEFONO");
                String correo = resultSet.getString("CLI_CORREO");
                Date fecNac = resultSet.getDate("CLI_FECNACIMIENTO");

                Cliente cliente = new Cliente(cedula, nombre,pApellido,sApellido,telefono,correo,fecNac);
                listaClientes.add(cliente);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaClientes;
    }
    
    
    public List<Integer> obtenerIdsClientes() {
    List<Integer> listaIdsClientes = new ArrayList<>();

    try {
        String query = "SELECT CLI_IDCLIENTE FROM TBL_CLIENTES ORDER BY CLI_IDCLIENTE";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Integer cedula = resultSet.getInt("CLI_IDCLIENTE");
            listaIdsClientes.add(cedula);
        }

        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaIdsClientes;
}
    
   
}
