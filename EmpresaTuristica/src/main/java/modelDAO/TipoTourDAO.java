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
import model.TipoTour;
import modelDTO.TipoTourDTO;

/**
 *
 * @author jitor
 */
public class TipoTourDAO {
    
    private Connection connection;

    public TipoTourDAO() {
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
    
 
    public void guardarTipoTour(TipoTourDTO tipoTourDTO) {
        String sql = "INSERT INTO TBL_TIPOTOURS (TPT_CODIGO, TPT_TIPOTOUR, TPT_NOMBRE, TPT_PAIS) " +
                "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
           
            statement.setString(1, tipoTourDTO.getTipoToursId().toString());
            statement.setString(2, tipoTourDTO.getTptTipotour());
            statement.setString(3, tipoTourDTO.getTptNombre());
            statement.setString(4, tipoTourDTO.getTptPais());


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
    
    
        public TipoTourDTO  getTipoTourPorCodigo(String tptCodigo) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM TBL_TIPOTOURS WHERE TPT_CODIGO = ?");
            statement.setString(1, tptCodigo);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                TipoTourDTO tipoToursDTO = new TipoTourDTO();
                tipoToursDTO.setTipoToursId(Integer.valueOf(resultSet.getString("TPT_CODIGO")));
                tipoToursDTO.setTptTipotour(resultSet.getString("TPT_TIPOTOUR"));
                tipoToursDTO.setTptNombre(resultSet.getString("TPT_NOMBRE"));
                tipoToursDTO.setTptPais(resultSet.getString("TPT_PAIS"));
               
                return tipoToursDTO;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
        
    public void actualizarTipoTour(TipoTourDTO tipoTourDTO) {
        try {
        PreparedStatement statement = connection.prepareStatement("UPDATE TBL_TIPOTOURS SET TPT_TIPOTOUR = ?, "
        + "TPT_NOMBRE = ?, TPT_PAIS = ?");
        
        statement.setString(1, tipoTourDTO.getTptTipotour());
        statement.setString(2, tipoTourDTO.getTptNombre());
        statement.setString(3, tipoTourDTO.getTptPais());
        //statement.setString(4, tipoTourDTO.getTipoToursId().toString());
       
        
        statement.executeUpdate();
        
        statement.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public void eliminarTipoTour(int idTipoTour) {
        try {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM TBL_TIPOTOURS WHERE TPT_CODIGO = ?");
        
        statement.setInt(1, idTipoTour);
        
        statement.executeUpdate();
        
        statement.close();
       
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public List<TipoTour> obtenerTipoTours() {
       List<TipoTour> listaTipoTours = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM TBL_TIPOTOUR ORDER BY TPT_CODIGO";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Integer tptCodigo = resultSet.getInt("TPT_CODIGO");
                String tptTipoTour = resultSet.getString("TPT_TIPOTOUR");
                String tptNombre = resultSet.getString("TPT_NOMBRE");
                String tptPais = resultSet.getString("TPT_PAIS");

                TipoTour tipoTours = new TipoTour(tptCodigo, tptTipoTour,tptNombre,tptPais);
                listaTipoTours.add(tipoTours);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaTipoTours;
    }
    
}
