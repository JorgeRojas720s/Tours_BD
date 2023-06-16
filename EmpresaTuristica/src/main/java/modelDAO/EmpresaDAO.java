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
import model.Empresa;
import modelDTO.EmpresaDTO;

/**
 *
 * @author jitor
 */
public class EmpresaDAO {
    
    
    private Connection connection;

    public EmpresaDAO() {
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
    
    
    public void guardarEmpresa(EmpresaDTO empresaDTO) {
        String sql = "INSERT INTO TBL_EMPRESA (EMP_CEDJURIDICA, EMP_NOMBRE, EMP_TELEFONO, EMP_CORREO,"
                + "EMP_FECHAFUND , EMP_CALIFICACION) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
          
            statement.setInt(1, empresaDTO.getEmpCedjuridica());
            statement.setString(2, empresaDTO.getEmpNombre());
            statement.setString(3, empresaDTO.getEmpTelefono());
            statement.setString(4, empresaDTO.getEmpCorreo());
            statement.setDate(5, (Date)empresaDTO.getEmpFechafund());
            statement.setString(6, empresaDTO.getEmpCalificacion());
            
            
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
        
    
    public EmpresaDTO getEmpresaPorCedula(String cedulaJuridica) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM TBL_EMPRESA WHERE EMP_CEDJURIDICA = ?");
            statement.setString(1, cedulaJuridica);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                EmpresaDTO empresaDTO = new EmpresaDTO();
                empresaDTO.setEmpCedjuridica(Integer.valueOf(resultSet.getString("EMP_CEDJURIDICA")));
                empresaDTO.setEmpNombre(resultSet.getString("EMP_NOMBRE"));
                empresaDTO.setEmpTelefono(resultSet.getString("EMP_TELEFONO"));
                empresaDTO.setEmpCorreo(resultSet.getString("EMP_CORREO"));
                empresaDTO.setEmpFechafund(resultSet.getDate("EMP_FECHAFUND"));
                empresaDTO.setEmpCalificacion(resultSet.getString("EMP_CALIFICACION"));
              
                return empresaDTO;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    
    public void actualizarEmpresa(EmpresaDTO empresaDTO) {
    try {
        PreparedStatement statement = connection.prepareStatement("UPDATE TBL_EMPRESA SET EMP_NOMBRE = ?, "
        + "EMP_TELEFONO = ?, EMP_CORREO = ?, EMP_CALIFICACION = ? WHERE EMP_CEDJURIDICA = ?");
        statement.setString(1, empresaDTO.getEmpNombre());
        statement.setString(2, empresaDTO.getEmpTelefono());
        statement.setString(3, empresaDTO.getEmpCorreo());
        statement.setString(4, empresaDTO.getEmpCalificacion());
        statement.setInt(5, empresaDTO.getEmpCedjuridica());
        
        statement.executeUpdate();
        
        statement.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public void eliminarEmpresa(int cedJuridica) {
        try {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM TBL_EMPRESA WHERE EMP_CEDJURIDICA = ?");
        
        statement.setInt(1, cedJuridica);
        
        statement.executeUpdate();
        
        statement.close();
       
        } catch (SQLException e) {
            e.printStackTrace();
    }
}
    
    
    public List<Empresa> obtenerEmpresas() {
        List<Empresa> listaEmpresas = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM TBL_EMPRESA ORDER BY EMP_CEDJURIDICA";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Integer cedulaJuridica = resultSet.getInt("EMP_CEDJURIDICA");
                String empNombre = resultSet.getString("EMP_NOMBRE");
                String empTelefono = resultSet.getString("EMP_TELEFONO");
                String empCorreo = resultSet.getString("EMP_CORREO");
                Date empFechaFund = resultSet.getDate("EMP_FECHAFUND");
                String empCalificacion = resultSet.getString("EMP_CALIFICACION");

                Empresa empresa = new Empresa(cedulaJuridica, empNombre,empTelefono, 
                empCorreo,empFechaFund,empCalificacion);
                listaEmpresas.add(empresa);
            }

            resultSet.close(); 
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaEmpresas;
    }
    
    
public List<Integer> obtenerIdsEmpresas() {
    List<Integer> listaIdsEmpresas = new ArrayList<>();

    try {
        String query = "SELECT EMP_CEDJURIDICA FROM TBL_EMPRESA ORDER BY EMP_CEDJURIDICA";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Integer cedulaJuridica = resultSet.getInt("EMP_CEDJURIDICA");
            listaIdsEmpresas.add(cedulaJuridica);
        }

        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaIdsEmpresas;
}

public void eliminarToursDeEmpresa(int idEmpresa) {
    try {
        // Eliminar los tours asociados a la empresa
        String sql = "DELETE FROM TBL_TOURS WHERE ID_EMPRESA = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idEmpresa);
        statement.executeUpdate();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejar la excepción apropiadamente
    }
}
    
}
