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
import model.Empresa;
import model.Tour;
import modelDTO.TourDTO;

/**
 *
 * @author jitor
 */
public class TourDAO {
    
    private Connection connection;

    public TourDAO() {
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
    
    public void guardarTour(TourDTO toursDTO) {
        String sql = "INSERT INTO TBL_TOURS (TUR_IDEMPRESA,TUR_IDTOUR, TUR_NOMBRETOUR, TUR_FECHASALIDA, TUR_FECHAREGRESO,"
                + "TUR_COSTOS, TUR_CODIGOTIPOTOURS) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, toursDTO.getTurIdempresa());
            statement.setString(2, toursDTO.getTourId().toString());
            statement.setString(3, toursDTO.getNombreTour());
            statement.setDate(4, (Date)toursDTO.getFechaSalida());
            statement.setDate(5, (Date)toursDTO.getFechaRegreso());
            statement.setString(6,   toursDTO.getTurCostos());
             statement.setString(7,   toursDTO.getTurCodigotipotours());
            
            
            
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
        
    
    public TourDTO getTourPorID(String idTour) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM TBL_TOURS WHERE TUR_IDTOUR = ?");
            statement.setString(1, idTour);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                TourDTO toursDTO = new TourDTO();
                toursDTO.setTurIdempresa(resultSet.getNString("TUR_IDEMPRESA"));
                toursDTO.setTourId(Integer.valueOf(resultSet.getString("TUR_IDTOUR")));
                toursDTO.setNombreTour(resultSet.getString("TUR_NOMBRETOUR"));
                toursDTO.setFechaSalida(resultSet.getDate("TUR_FECHASALIDA"));
                toursDTO.setFechaRegreso(resultSet.getDate("TUR_FECHAREGRESO"));
                toursDTO.setTurCostos(resultSet.getString("TUR_COSTOS"));
                toursDTO.setTurCodigotipotours(resultSet.getNString("TUR_CODIGOTIPOTOURS"));

                return toursDTO;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    
    public void actualizarTour(TourDTO toursDTO) {
        //ver si es necesario modificar el codigo del tipo de Tour
        try {
        PreparedStatement statement = connection.prepareStatement("UPDATE TBL_TOURS SET TUR_NOMBRETOUR = ?, "
        + "TUR_FECHASALIDA = ?, TUR_FECHAREGRESO = ?, TUR_COSTOS = ? WHERE TUR_IDTOUR = ?");
        
        statement.setString(1, toursDTO.getNombreTour());
        statement.setDate(2, toursDTO.getFechaSalida());
        statement.setDate(3, toursDTO.getFechaRegreso());
        statement.setString(4, toursDTO.getTurCostos());
        statement.setInt(5, toursDTO.getTourId());
        
        statement.executeUpdate();
        
        statement.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    
    public void eliminarTour(int idTour) {
        try {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM TBL_TOURS WHERE TUR_IDTOUR = ?");
        
        statement.setInt(1, idTour);
        
        statement.executeUpdate();
        
        statement.close();
       
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
    
    public List<Tour> obtenerTour() {
        List<Tour> listaTour = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM TBL_TOURS ORDER BY TUR_IDTOUR";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                //String idEmpresa = resultSet.getString("TUR_IDEMPRESA");
                Integer idTour = resultSet.getInt("TUR_IDTOUR");
                String nombreTour = resultSet.getString("TUR_NOMBRETOUR");
                Date tourFecSalida = resultSet.getDate("TUR_FECHASALIDA");
                Date tourFecRegreso = resultSet.getDate("TUR_FECHAREGRESO");
                BigDecimal costosTour = resultSet.getBigDecimal("TUR_COSTOS");
                
                Tour tours = new Tour(idTour, nombreTour,tourFecSalida,tourFecRegreso,costosTour);
                listaTour.add(tours);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaTour;
    }
    
    
public List<Integer> obtenerIdsTours() {
    List<Integer> listaIdsTours = new ArrayList<>();

    try {
        String query = "SELECT TUR_IDTOUR FROM TBL_TOURS ORDER BY TUR_IDTOUR";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Integer cedulaJuridica = resultSet.getInt("TUR_IDTOUR");
            listaIdsTours.add(cedulaJuridica);
        }

        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaIdsTours;
}
    
public List<Integer> obtenerToursDeEmpresa(int idEmpresa) {
    List<Integer> listaIdsTours = new ArrayList<>();

    try {
        String query = "SELECT TUR_IDTOUR FROM TBL_TOURS WHERE TUR_IDEMPRESA IN (SELECT EMP_CEDJURIDICA FROM TBL_EMPRESA WHERE EMP_CEDJURIDICA = ?) ORDER BY TUR_IDTOUR";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idEmpresa);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Integer idTour = resultSet.getInt("TUR_IDTOUR");
            listaIdsTours.add(idTour);
        }

        resultSet.close();
        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaIdsTours;
}

public List<Integer> obtenerToursDeCliente(int idCliente) {
    List<Integer> listaIdsTours = new ArrayList<>();

    try {
        String query = "SELECT TUR_IDTOUR FROM TBL_TOURS WHERE TUR_IDEMPRESA IN (SELECT CLI_IDCLIENTE FROM TBL_CLIENTES WHERE CLI_IDCLIENTE = ?) ORDER BY TUR_IDTOUR";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCliente);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Integer idTour = resultSet.getInt("TUR_IDTOUR");
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


public int obtenerCodigoTipoTour(int idTour) {
    int codigoTipoTour = 0;
    System.out.println("Holaa");
    try {
        PreparedStatement statement = connection.prepareStatement("SELECT TUR_CODIGOTIPOTOURS FROM TBL_TOURS WHERE TUR_IDTOUR = ?");
        statement.setInt(1, idTour);
        
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            codigoTipoTour = resultSet.getInt("TUR_CODIGOTIPOTOURS");
        }
        
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    System.out.println("llllllllllll: "+ codigoTipoTour);
    return codigoTipoTour;
}

public int obtenerItinerarios(int idTour) {
    int itinerario = 0;
    System.out.println("popopopo"+idTour);
    try {
        PreparedStatement statement = connection.prepareStatement("SELECT ITI_IDITINERARIO FROM TBL_ITINERARIO WHERE ITI_IDTOURS = ?");
        statement.setInt(1, idTour);
        
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            itinerario = resultSet.getInt("ITI_IDITINERARIO");
        }
        
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    System.out.println("titititititi: "+ itinerario);
    return itinerario;
}


public int obtenerReservas(int idTour) {
    int itinerario = 0;
    System.out.println("uiuiiu");
    try {
        PreparedStatement statement = connection.prepareStatement("SELECT RES_IDRESERVA FROM TBL_RESERVA WHERE RES_IDTOUR = ?");
        statement.setInt(1, idTour);
        
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            itinerario = resultSet.getInt("RES_IDRESERVA");
        }
        
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    System.out.println("titititititi: "+ itinerario);
    return itinerario;
}

    
}
