package jvsmoke.core;

import jvsmoke.JvsConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoreSQLITE3Test {
    @Test
    void ConstructorCoreSqlite3() {
        JvsConfig.SQLITE3_STR_CONEXION  = "jdbc:sqlite:D:/Programacion/Sqlite/jvsmoketest.db";
        CoreSQLITE3 core = new CoreSQLITE3("proveedor");
        assertNotNull(core);
    }
}