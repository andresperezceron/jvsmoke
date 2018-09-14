package jvsmoke.conexion;

import jvsmoke.JvsConfig;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConexionTest {
    private Conexion conexion;

    private ConexionTest() {
        JvsConfig.SQLITE3_STR_CONEXION  = "jdbc:sqlite:D:/Programacion/Sqlite/jvsmoketest.db";
        conexion = Singleton.getConexion();
    }

    @Test
    void ConexConstructor() {
        assertNotNull(conexion);
    }

    @Test
    void exeSimpleQuery() {
        String query = "select count(*) from proveedor";
        assertEquals(5, (int) conexion.exeSimpleQuery(query));
    }

    @Test
    void getResultSet() {
        ResultSet result = conexion.getResultSet("select nombre from proveedor");
        String[] nombres = new String[5];
        String[] expec = {"Proveedor 1", "Proveedor 2", "Proveedor 3", "Proveedor 4", "Proveedor 5"};
        try {
            int j=0;
            int colum_count = result.getMetaData().getColumnCount();
            while(result.next())
                for(int i = 0; i<colum_count; i++)
                    nombres[j++] = result.getString(i+1);
        }catch(SQLException e) { e.printStackTrace(); }
        assertArrayEquals(expec, nombres);
    }

    @Test
    void getStatement() {
        String query;
        query = "INSERT INTO proveedor(referencia, nombre) VALUES('PRO006', 'Proveedor 6');";
        PreparedStatement statement = conexion.getStatement(query);
        try { statement.execute();
        }catch (SQLException e) { e.printStackTrace(); }

        query = "select count(*) from proveedor";
        int seisProveedores = (int) conexion.exeSimpleQuery(query);
        assertEquals(6, seisProveedores);

        query = "DELETE FROM proveedor WHERE referencia = 'PRO006'";
        statement = conexion.getStatement(query);
        try { statement.execute();
        }catch (SQLException e) { e.printStackTrace(); }

        query = "select count(*) from proveedor";
        assertEquals(5, (int) conexion.exeSimpleQuery(query));
    }
}