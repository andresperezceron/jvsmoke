package jvsmoke.core;

import jvsmoke.JvsConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    private RepositoryTest() {
        JvsConfig.SQLITE3_STR_CONEXION  = "jdbc:sqlite:D:/Programacion/Sqlite/jvsmoketest.db";
    }

    @Test
    void setByColumIndex() {
        Repository repository = new Repository(("proveedor"));
        int indexColum = 1;
        Object value = "referenciaProveedor";
        repository.set(indexColum, value);
        assertEquals(repository.get(indexColum), value);
    }

    @Test
    void setByColumName() {
        Repository repository = new Repository(("proveedor"));
        String columName = "nombre";
        Object value = "nombreDelProveedor";
        repository.set(columName, value);
        assertEquals(repository.get(columName), value);
    }

    @Test
    void getByColumIndex() {
        int columIndex = 2;
        int id = 8;
        Repository repository = new Repository(("tipo"));
        repository.load(id);
        Object value = "plantilla";
        assertEquals(repository.get(columIndex), value);
    }

    @Test
    void getByColumName() {
        String columName = "esamarre";
        int id = 10;
        Repository repository = new Repository(("tipo"));
        repository.load(id);
        Object value = "NO";
        assertEquals(repository.get(columName), value);
    }

    @Test
    void load() {
        int id = 7;
        Object[] expec = {7, 7, "ojete", "NO"};
        Repository repository = new Repository("tipo");
        repository.load(id);
        Object[] actual = new Object[expec.length];
        actual[0] = repository.get("id");
        actual[1] = repository.get("id_tipo");
        actual[2] = repository.get("nombre");
        actual[3] = repository.get("esamarre");
        assertArrayEquals(expec, actual);
    }

    @Test
    void insert() {
        String refPro = "PRO007";
        String nomPro = "Proveedor 7";
        Repository repository = new Repository("proveedor");
        repository.set("referencia", refPro);
        repository.set("nombre", nomPro);
        String query = "select count(*) from proveedor where referencia = '" + refPro + "';";
        int expectBeforeInsert = 0;
        int actualBeforeInsert = (int) repository.conexion.exeSimpleQuery(query);
        assertEquals(expectBeforeInsert, actualBeforeInsert);
        repository.insert();
        int expectAfterInsert = 1;
        int actualAfterInsert = (int) repository.conexion.exeSimpleQuery(query);
        assertEquals(expectAfterInsert, actualAfterInsert);
        query = "select id from proveedor where referencia = '" + refPro + "';";
        repository.load((int) repository.conexion.exeSimpleQuery(query));
        repository.delete();
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}