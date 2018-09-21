package jvsmoke.core;

import jvsmoke.JvsConfig;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    private ModelForTest modelForTest;

    private ModelTest() {
        JvsConfig.SQLITE3_STR_CONEXION  = "jdbc:sqlite:db/jvsmoketest.db";
        modelForTest = new ModelForTest(); /* Instanciado con el esquema "proveedor" */
    }

    @Test
    void querySimple() {
        String expect = "Proveedor 3";
        String query = "select nombre from proveedor where id = 8";
        String actual = modelForTest.querySimple(query).toString();
        assertEquals(expect, actual);
    }

    @Test
    void query() throws SQLException {
        String[] expect = {"PRO001", "PRO002", "PRO003", "PRO004", "PRO005", "PRO006"};
        String[] actual = new String[expect.length];
        ResultSet resultSet = modelForTest.query("select referencia from proveedor;");
        for(int i=0; resultSet.next(); i++)
            actual[i] = resultSet.getString("referencia");
        assertArrayEquals(expect, actual);
    }

    @Test
    void getRepository() {
        assertNotNull(modelForTest.getRepository());
    }

    @Test
    void getBy() {
        Object[] expect = {7, "PRO002", "Proveedor 2"};
        Object[] actual = modelForTest.getBy("referencia", "PRO002");
        assertArrayEquals(expect, actual);
    }

    @Test
    void getIdBy() {
        String strUniqueKey = "referencia";
        String referencia = "PRO001";
        int expect = 6;
        int actual = modelForTest.getIdBy(strUniqueKey, referencia);
        assertEquals(expect, actual);
    }

    @Test
    void finalCheckToInsert() {
        String referencia = "PRO007";
        String nombrePro = "Proveedor 7";
        modelForTest.getRepository().set("referencia", referencia);
        modelForTest.getRepository().set("nombre", nombrePro);
        assertTrue(modelForTest.finalCheckToInsert());
    }

    @Test
    void finalCheckToUpdate() {
        modelForTest.getRepository().load(22); /* Proveedor 6 */
        modelForTest.getRepository().set("nombre", "el nuevo nombre del proveedor 6");
        modelForTest.getRepository().set("referencia", "PRO0022");
        assertTrue(modelForTest.finalCheckToUpdate());
    }
}