package jvsmoke.core;

import jvsmoke.JvsConfig;
import jvsmoke.conexion.Conexion;
import jvsmoke.conexion.Singleton;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class QueryBuilderTest {
    private QueryBuilder qb;

    private QueryBuilderTest() {
        JvsConfig.SQLITE3_STR_CONEXION  = "jdbc:sqlite:D:/Programacion/Sqlite/jvsmoketest.db";
        Conexion conexion = Singleton.getConexion();
        MetaData meta = new MetaData("componente", conexion);
        qb = new QueryBuilder("componente", meta.getColumNames(), conexion);
    }

    @Test
    void createQueryInsert() {
        String expec;
        expec = "insert into componente(id_tipo,id_proveedor,referencia,escandallo,descripcion)values(?,?,?,?,?);";
        assertEquals(expec, qb.createQueryInsert());
    }

    @Test
    void createQueryUpdate() {
        String expec;
        expec = "update componente set id_tipo=?,id_proveedor=?,referencia=?,escandallo=?,descripcion=? where id=?;";
        assertEquals(expec, qb.createQueryUpdate());
    }

    @Test
    void createQueryDelete() {
        String expec = "delete from componente where id=";
        assertEquals(expec, qb.createQueryDelete());
    }

    @Test
    void getIdBy() {
        int expect = 2;
        assertEquals(expect, qb.getIdBy("referencia", "BOR001"));
    }

    @Test
    void getBy() throws SQLException {
        Object[] expect = {1, 1, 6, "BAN001", 1.1, "Descripción de bandeleta 1"};
        Object[] actual = new Object[expect.length];
        ResultSet result = qb.getBy("id", 1);
        result.next();
        actual[0] = result.getInt("id");
        actual[1] = result.getInt("id_tipo");
        actual[2] = result.getInt("id_proveedor");
        actual[3] = result.getString("referencia");
        actual[4] = result.getDouble("escandallo");
        actual[5] = result.getString("descripcion");
        assertArrayEquals(expect, actual);
    }

    @Test
    void getColumTest() throws SQLException {
        String[] expect = {"BAN001", "BOR001"};
        String[] actual = new String[expect.length];
        ResultSet result = qb.getColum("referencia");
        for(int i=0; result.next(); i++)
            actual[i] = result.getString("referencia");
        assertArrayEquals(expect, actual);
    }
}