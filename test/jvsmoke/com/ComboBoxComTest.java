package jvsmoke.com;

import jvsmoke.JvsConfig;
import jvsmoke.conexion.Conexion;
import jvsmoke.conexion.Singleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComboBoxComTest {
    private Conexion conexion;

    ComboBoxComTest() {
        JvsConfig.SQLITE3_STR_CONEXION = "jdbc:sqlite:db/jvsmoketest.db";
        conexion = Singleton.getConexion();
    }

    @Test
    void load() {
        ComboBoxCom combo = new ComboBoxCom();
        combo.load(conexion.getResultSet("select nombre from proveedor;"));
        String[] expect = {"Proveedor 1","Proveedor 2","Proveedor 3","Proveedor 4","Proveedor 5","Proveedor 6"};
        String[] actual = new String[expect.length];
        for(int i = 0; i<expect.length; i++)
            actual[i] = combo.getItemAt(i).toString();
        assertArrayEquals(expect, actual);
    }

    @Test
    void setSelectedIndexByItem() {
        ComboBoxCom combo = new ComboBoxCom();
        combo.load(conexion.getResultSet("select nombre from proveedor;"));
        String expect = "Proveedor 2";
        combo.setSelectedIndexByItem(expect);
        String actual = String.valueOf(combo.getSelectedItem());
        assertEquals(expect, actual);
    }
}