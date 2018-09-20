package jvsmoke.core;

import jvsmoke.JvsConfig;
import jvsmoke.conexion.Conexion;
import jvsmoke.conexion.Singleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetaDataTest {
    private MetaData meta;

    private MetaDataTest() {
        JvsConfig.SQLITE3_STR_CONEXION  = "jdbc:sqlite:db/jvsmoketest.db";
        Conexion conexion = Singleton.getConexion();
        meta = new MetaData("tipo", conexion);
    }

    @Test
    void getColumNames() {
        String[] expect = {"id", "id_tipo", "nombre", "esamarre"};
        assertArrayEquals(expect, meta.getColumNames());
    }

    @Test
    void getColumTypes() {
        String[] expect = {"INTEGER", "INTEGER", "TEXT", "TEXT"};
        assertArrayEquals(expect, meta.getColumTypes());
    }

    @Test
    void getColumCont() {
        int expect = 4;
        assertEquals(expect, meta.getColumCount());
    }
}