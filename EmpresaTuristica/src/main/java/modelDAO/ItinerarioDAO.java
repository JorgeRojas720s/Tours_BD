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
import model.Itinerario;
import modelDTO.ItinerarioDTO;

/**
 *
 * @author jitor
 */
public class ItinerarioDAO {
    
        
    private Connection connection;

    public ItinerarioDAO() {
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
    
    public void guardarItinerario(ItinerarioDTO itinerarioDTO) {
        String sql = "INSERT INTO TBL_ITINERARIO (ITI_IDTOURS, ITI_IDITINERARIO, ITI_LUGAR, ITI_FECLLEGADA, ITI_FECSALIDA,"
                + "ITI_DURACION , ITI_DESCP_ACTIVIDADES, ITI_HORALLEGADA, ITI_HORASALIDA) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, itinerarioDTO.getItiIdtours());
            statement.setString(2, itinerarioDTO.getItinerarioId().toString());
            statement.setString(3, itinerarioDTO.getItiLugar());
            statement.setDate(4,   (Date)itinerarioDTO.getItiFechorallegada());
            statement.setDate(5,   (Date)itinerarioDTO.getItiFechorasalida());
            statement.setString(6, itinerarioDTO.getItiDuracion());
            statement.setString(7, itinerarioDTO.getItiDescpActividades());
             statement.setString(8, itinerarioDTO.getItiHorallegada());
            statement.setString(9, itinerarioDTO.getItiHorasalida());
            
            
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
    
    public ItinerarioDTO getItinerarioPorId(String idItinerario) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM TBL_ITINERARIO WHERE ITI_IDITINERARIO = ?");
            statement.setString(1, idItinerario);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                ItinerarioDTO itinerarioDTO = new ItinerarioDTO();
                itinerarioDTO.setItinerarioId(Integer.valueOf(resultSet.getString("ITI_IDITINERARIO")));
                itinerarioDTO.setItiIdtours(resultSet.getString("ITI_IDTOURS"));
                itinerarioDTO.setItiLugar(resultSet.getString("ITI_LUGAR"));
                itinerarioDTO.setItiFechorallegada(resultSet.getDate("ITI_FECLLEGADA"));
                itinerarioDTO.setItiFechorasalida(resultSet.getDate("ITI_FECSALIDA"));
                itinerarioDTO.setItiDuracion(resultSet.getString("ITI_DURACION"));
                itinerarioDTO.setItiDescpActividades(resultSet.getString("ITI_DESCP_ACTIVIDADES"));
                itinerarioDTO.setItiHorallegada(resultSet.getString("ITI_HORALLEGADA"));
                itinerarioDTO.setItiHorasalida(resultSet.getString("ITI_HORASALIDA"));
                
                return itinerarioDTO;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
public List<Integer> obtenerItinerariosDeTour(int idTour) {
    List<Integer> listaIdsItinerarios = new ArrayList<>();
    System.out.println("wwwwwwwwww");
    try {
        String query = "SELECT ITI_IDITINERARIO FROM TBL_ITINERARIO WHERE ITI_IDTOURS = ? ORDER BY ITI_IDITINERARIO";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idTour);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Integer idItinerario = resultSet.getInt("ITI_IDITINERARIO");
            listaIdsItinerarios.add(idItinerario);
        }

        resultSet.close();
        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    System.out.println("kkkkkk");
    return listaIdsItinerarios;
}



    
public void actualizarItinerario(ItinerarioDTO itinerarioDTO) {
    try {
        PreparedStatement statement = connection.prepareStatement("UPDATE TBL_ITINERARIO SET ITI_LUGAR = ?, "
        + "ITI_FECLLEGADA = ?, ITI_FECSALIDA = ?, ITI_DURACION = ?, ITI_DESCP_ACTIVIDADES = ?, ITI_HORALLEGADA = ?, ITI_HORASALIDA = ? WHERE ITI_IDITINERARIO = ?");
        statement.setString(1, itinerarioDTO.getItiLugar());
        statement.setDate(2, itinerarioDTO.getItiFechorallegada());
        statement.setDate(3, itinerarioDTO.getItiFechorasalida());
        statement.setString(4, itinerarioDTO.getItiDuracion());
        statement.setString(5, itinerarioDTO.getItiDescpActividades());
        statement.setString(6, itinerarioDTO.getItiHorallegada());
        statement.setString(7, itinerarioDTO.getItiHorasalida());
        statement.setInt(8, itinerarioDTO.getItinerarioId());
        
        statement.executeUpdate();
        
        statement.close();

        } catch (SQLException e) {
        e.printStackTrace();
        }
    }


    public void eliminarItinerario(int idItinerario) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM TBL_ITINERARIO WHERE ITI_IDITINERARIO = ?");
        
            statement.setInt(1, idItinerario);
        
            statement.executeUpdate();
        
            statement.close();
       
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
    
    
    public List<Itinerario> obtenerItinerarios() {
        List<Itinerario> listaItinerario = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM TBL_ITINERARIO ORDER BY ITI_IDITINERARIO";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Integer idItinerario = resultSet.getInt("ITI_IDITINERARIO");
                String lugar = resultSet.getString("ITI_LUGAR");
                Date fecLlegada = resultSet.getDate("ITI_FECLLEGADA");
                Date fecSalid = resultSet.getDate("ITI_FECSALIDA");
                String duracion = resultSet.getString("ITI_DURACION");
                String descpActividades = resultSet.getString("ITI_DESCP_ACTIVIDADES");
                String horaLlegada = resultSet.getString("ITI_HORALLEGADA");
                String horaSalida = resultSet.getString("ITI_HORASALIDA");

                Itinerario itinerario = new Itinerario(idItinerario, lugar,fecLlegada,fecSalid,duracion, 
                descpActividades, horaLlegada, horaSalida);
                listaItinerario.add(itinerario);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaItinerario;
    }
    
    public int obtenerIDItinerarios(int idTour) {
    int itinerario = 0;
    System.out.println("popopopo"+idTour);
    try {
        System.out.println("entreeeeeeeee");
        PreparedStatement statement = connection.prepareStatement("SELECT ITI_IDITINERARIO FROM TBL_ITINERARIO WHERE ITI_IDTOURS = ?");
        statement.setInt(1, idTour);
        
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            System.out.println("ooo: "+ resultSet.getInt("ITI_IDITINERARIO"));
            itinerario = resultSet.getInt("ITI_IDITINERARIO");
            System.out.println("xxxx: " + itinerario);
        }
        
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        System.out.println("que peo");
        e.printStackTrace();
    }
    System.out.println("titititititi: "+ itinerario);
    return itinerario;
}
    
}
