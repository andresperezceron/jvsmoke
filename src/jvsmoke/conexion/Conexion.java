package jvsmoke.conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conexión db SQLITE3.
 * Se puede utilizar tal cual, pero se recomienda hacer uso de la clase Singleton
 * que proporciona jvsmoke para la gestión de la conexión a la db.
 */
public class Conexion {
    private Connection connection;
    private ResultSet resultset;
    private PreparedStatement statement;

    /**
     * Constructor de Conexion.
     * @param Url cadena para la conexion con la db.
     */
    Conexion(String Url) {
        try {
            connection = DriverManager.getConnection(Url);
            System.out.println("Conectado");
        }catch(SQLException e) { System.err.println("No se ha podido conectar."); }
    }

    /**
     * Cierra la conexion.
     */
    public void close() {
        try{
            connection.close();
            System.out.println("Desconectado");}
        catch(SQLException e) { System.err.println("No se ha podido desconectar."); }
    }

    /**
     * Metodo para realizar consultas monoregistro.
     * @param query monoregistro.
     * @return resultado de query.
     */
    public Object exeSimpleQuery(String query) {
        Object value;
        try { value = connection.prepareStatement(query).executeQuery().getObject(1); }
        catch(SQLException e) { return null; }
        return value;
    }

    /**
     * Metodo utilizado para consultas multiregistro.
     * @param query multiregistro.
     * @return Resultset cargado con el resultado de query.
     */
    public ResultSet getResultSet(String query) {
        try { resultset = connection.prepareStatement(query).executeQuery();
        }catch(SQLException e) { e.printStackTrace(); }
        return resultset;
    }

    /**
     * Metodo utilizado para los procedimientos: update, datete, insert ...
     * @param query para procedimientos.
     * @return PreparedStatement preparado para realizar el procedimiento.
     */
    public PreparedStatement getStatement(String query) {
        try { statement = connection.prepareStatement(query);
        }catch(SQLException e) { e.printStackTrace(); }
        return statement;
    }
}