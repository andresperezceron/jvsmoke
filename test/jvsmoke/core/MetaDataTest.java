package jvsmoke.core;

import jvsmoke.JvsConfig;
import jvsmoke.conexion.Conexion;
import jvsmoke.conexion.Singleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetaDataTest {
    private MetaData meta;

    private MetaDataTest() {
        JvsConfig.SQLITE3_STR_CONEXION  = "jdbc:sqlite:D:/Programacion/Sqlite/jvsmoketest.db";
        Conexion conexion = Singleton.getConexion();
        meta = new MetaData("proveedor", conexion);
    }

    @Test
    void getColumNames() {
        String[] expect = {"id", "referencia", "nombre"};
        assertArrayEquals(expect, meta.getColumNames());
    }

    @Test
    void getColumTypes() {
        String[] expect = {"INTEGER", "TEXT", "TEXT"};
        assertArrayEquals(expect, meta.getColumTypes());
    }

    @Test
    void getColumCont() {
        assertEquals(3, meta.getColumCont());
    }
}