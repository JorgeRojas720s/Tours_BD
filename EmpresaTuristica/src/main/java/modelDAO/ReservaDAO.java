/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDAO;

import java.math.BigDecimal;
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
import model.Reserva;
import modelDTO.ClienteDTO;
import modelDTO.ReservaDTO;
import modelDTO.TourDTO;

/**
 *
 * @author jitor
 */
public class ReservaDAO {
    
        private Connection connection;

    public ReservaDAO() {
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
 
    public void guardarReserva(ReservaDTO reservaDTO) {
        String sql = "INSERT INTO TBL_RESERVA (RES_IDRESERVA, RES_IDCLIENTE, RES_IDTOUR, RES_FECRESERVA, RES_CANTPERSONAS, RES_COSTO,"
                + "RES_MONTOSABONADOS) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1,reservaDTO.getReservaId().toString());
            statement.setString(2,reservaDTO.getResIdCliente());
            statement.setString(3,  reservaDTO.getResIdtour());
            statement.setDate(4,  reservaDTO.getResFecreserva());
            statement.setString(5,reservaDTO.getResCantpersonas());
            statement.setString(6,  reservaDTO.getResCosto());
            statement.setString(7,reservaDTO.getResMontosabonados());
 
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
      
    public ReservaDTO getReservaPorId(String idReserva) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM TBL_RESERVA WHERE RES_IDRESERVA = ?");
            statement.setString(1, idReserva);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                ReservaDTO reservaDTO = new ReservaDTO();
                reservaDTO.setReservaId(Integer.valueOf(resultSet.getString("RES_IDRESERVA")));
                reservaDTO.setResIdCliente(resultSet.getString("RES_IDCLIENTE"));
                reservaDTO.setResIdtour(resultSet.getString("RES_IDTOUR"));
                reservaDTO.setResFecreserva(resultSet.getDate("RES_FECRESERVA"));
                reservaDTO.setResCantpersonas(resultSet.getString("RES_CANTPERSONAS"));
                reservaDTO.setResCosto(resultSet.getString("RES_COSTO"));
                reservaDTO.setResMontosabonados(resultSet.getString("RES_MONTOSABONADOS"));
            
                return reservaDTO;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
public List<Integer> getReservaIdTour(String idCliente) {
    List<Integer> reservaIdTourList = new ArrayList<>();
    
    try {
        PreparedStatement statement = connection.prepareStatement("SELECT RES_IDTOUR FROM TBL_RESERVA WHERE RES_IDCLIENTE = ?");
        statement.setString(1, idCliente);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            int reservaIdTour = resultSet.getInt("RES_IDTOUR");
            reservaIdTourList.add(reservaIdTour);
        }
        
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return reservaIdTourList;
}


    
    
    public void actualizarReserva(ReservaDTO reservaDTO) {
        try {
        PreparedStatement statement = connection.prepareStatement("UPDATE TBL_RESERVA SET RES_IDCLIENTE = ?, "
        + "RES_IDTOUR = ?, RES_FECRESERVA = ?, RES_CANTPERSONAS = ?, RES_COSTO = ?, RES_MONTOSABONADOS = ? "
        + "WHERE RES_IDRESERVA = ?");
        statement.setString(1, reservaDTO.getResIdCliente());
        statement.setString(2, reservaDTO.getResIdtour());
        statement.setDate(3, reservaDTO.getResFecreserva());
        statement.setString(4, reservaDTO.getResCantpersonas());
        statement.setString(5, reservaDTO.getResCosto());
        statement.setString(6, reservaDTO.getResMontosabonados());
        statement.setInt(7, reservaDTO.getReservaId());
        
        statement.executeUpdate();
        
        statement.close();

        } catch (SQLException e) {
        e.printStackTrace();
        }
}
        
    public void eliminarReserva(int idReserva) {
        try {
           PreparedStatement statement = connection.prepareStatement("DELETE FROM TBL_RESERVA WHERE RES_IDRESERVA = ?");
           
            statement.setInt(1, idReserva);
        
            statement.executeUpdate();
        
            statement.close();
       
        } catch (SQLException e) {
           e.printStackTrace();
        }
}
    
   public List<Reserva> obtenerReservas() {
        List<Reserva> listaReserva = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM TBL_RESERVA ORDER BY RES_IDRESERVA";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Integer idReserva = resultSet.getInt("RES_IDRESERVA");
                Date fecReserva = resultSet.getDate("RES_FECRESERVA");
                Short cantPersonas = resultSet.getShort("RES_CANTPERSONAS");
                BigDecimal costo = resultSet.getBigDecimal("RES_COSTO");
                BigDecimal montAbonado = resultSet.getBigDecimal("RES_MONTOSABONADOS");

                Reserva reserva = new Reserva(idReserva, fecReserva,cantPersonas,costo,montAbonado);
                listaReserva.add(reserva);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaReserva;
    }
   
   
public List<Integer> obtenerReservasDeCliente(int idCliente) {
    List<Integer> listaIdsTours = new ArrayList<>();

    try {
        String query = "SELECT RES_IDRESERVA FROM TBL_RESERVA WHERE RES_IDCLIENTE IN (SELECT CLI_IDCLIENTE FROM TBL_CLIENTES WHERE CLI_IDCLIENTE = ?) ORDER BY RES_IDRESERVA";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCliente);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Integer idTour = resultSet.getInt("RES_IDRESERVA");
            System.out.println("qqqqqqqq"+idTour);
            listaIdsTours.add(idTour);
        }

        resultSet.close();
        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaIdsTours;
}
    
}
