package jvsmoke.com;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TableCom es un JTable pero con el método getModel() sobrecargado para poder usar nuestro TableModelCom.
 * Además proporciona el método getSelectedValueAt(int colum).
 */
public class TableCom extends JTable {
    private TableModelCom tm;

    /**
     * Constructor.
     * @param nom_col nombres de la columnas que harán de cabeceras en el TableCom.
     * @param tam_col el tamaño de las columnas.
     */
    public TableCom(@NotNull String[] nom_col, int[] tam_col) {
        tm = new TableModelCom();
        for(String s : nom_col) tm.addColumn(s);
        this.setModel(tm);
        for(int i=0; i<tam_col.length; i++)
            this.getColumnModel().getColumn(i).setMaxWidth(tam_col[i]);
    }

    public TableModelCom getModel() {
        return tm;
    }

    public void load(ResultSet resultset) {
        tm.load(resultset);
    }

    public void load(Object[][] data) {
        tm.load(data);
    }

    public Object getSelectdValueAt(int colum) {
        return this.getValueAt(this.getSelectedRow(), colum);
    }
}
