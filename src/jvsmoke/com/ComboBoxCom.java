package jvsmoke.com;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ComboBoxCom es un JComboBox pero que se le añaden los metodos load(Resultset) y setSelectedIndexByItem(Obj item).
 * load() nos propociona una forma muy fácil de cargar los datos en el ComboBoxCom y setSelectedIndexByItem
 * nos proporciona la selección de un elemento de la lista pasando el propio valor de la lista como parámetro.
 * También proporciona dos constructores, para si queremos hacer que el primer elemento de la lista haga de label.
 */
public class ComboBoxCom extends JComboBox<Object> {

    /**
     * Constructor.
     */
    public ComboBoxCom() {}

    /**
     * Constructor
     * @param titulo el primer elemento que aparecerá en la lista del ComboBoxCom para hacer de label.
     */
    public ComboBoxCom(String titulo) {
        this.addItem(titulo);
    }

    /**
     * Carga ComboBoxCom con los datos que contiene el ResultSet.
     * ComboBoxCom es capaz de cargar ResultSet que contengan más de una fila.
     * @param resultset ResultSet obtenido tras la consulta sql.
     */
    public void load(ResultSet resultset) {
        try{
            int colum_count = resultset.getMetaData().getColumnCount();
            while(resultset.next()) {
                StringBuilder item = new StringBuilder();
                for(int i=0; i<colum_count; i++)
                    item.append((i + 1 == colum_count) ?
                    resultset.getObject(i + 1) :
                    resultset.getObject(i + 1) + "   -   ");
                this.addItem(item.toString());
            }
            resultset.close();
        } catch (SQLException e) { e.printStackTrace(); }

    }

    /**
     * Método que nos proporciona la posibilidad de hacer setSelectedIndex(index) sobre un elemento que
     * contenga la lista de ComboBoxCom pasando como parámetro el propio valor del item.
     * @param item valor del item de la lista del ComboBoxCom a seleccionar.
     */
    public void setSelectedIndexByItem(Object item) {
        for(int i = 0; i < this.getItemCount(); i++)
            if(this.getItemAt(i).equals(item))
                this.setSelectedIndex(i);
    }
}
