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
        qb = new QueryBuilder("componente", meta.getColumNames());
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
}