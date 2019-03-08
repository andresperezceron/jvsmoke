package jvsmoke.com;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  DefaultTableModel es la vista del componente JTable.
 *  Asignamos que las celdas de la tabla no sean editables por defecto sobreescribiento el método
 *  isCellEditable. Además le añadimos dos metodos: load(ResultSet) y empy() para realizar las
 *  operaciones de carga de datos en el JTable.
 */
public class TableModelCom extends DefaultTableModel {

    /**
     * Asigna si las celdas de la tabla del JTable son editables o no. Al retornar siempre false
     * estamos estableciendo que todas las celtas de la tabla no sean editables por defecto.
     * @param fila la fila de la tabla del JTable.
     * @param columna la columna de la tabla del JTable.
     * @return si la celda que indicada por fila y columna sea editable o no.
     */
    @Override
    public boolean isCellEditable(int fila, int columna) {
        return false;
    }

    /**
     * Es el método encargado de realizar la carga de datos en el TableModelCom.
     * Hace una llamada al metodo empy() hastes de realizar la carga de datos.
     * @param resultset ResulSet cargado con el resultado obtenido de la cosulta sql.
     */
    public void load(ResultSet resultset) {
        this.empy();
        try {
            int colum_count = resultset.getMetaData().getColumnCount();
            while(resultset.next()) {
                Object row[] = new Object[colum_count];
                for(int i = 0; i<colum_count; i++)
                    row[i] = resultset.getObject(i+1);
                this.addRow(row);
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void load(Object[][] data) {
        this.empy();
        if(data != null)
            for(Object[] datum : data) this.addRow(datum);
    }

    /**
     * Elimina todas las columnas del TableModelCom.
     */
    public void empy() {
        for(int i = this.getRowCount() - 1; i >= 0; i--)
            this.removeRow(i);
    }

}
