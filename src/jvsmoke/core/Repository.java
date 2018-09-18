package jvsmoke.core;

import jvsmoke.conexion.Conexion;
import jvsmoke.conexion.Singleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Como su nombre indica es un repositorio para trablajar con los esquemas de la base de datos.
 * Realiza los procedimientos de Inserción, Actualización y Eliminación de datos.
 * Tiene como propiedades a MetaData y QueryBuilder que nos proporcionan las herramientas necesarias
 * para realizar los procedimientos. jvsmoke implementa los repositorios en la clase Model.
 */
public class Repository {
    private String tabla;
    private Object[] row;
    private MetaData metaData;
    private QueryBuilder queryBuilder;
    Conexion conexion; /* conexion es pakage-private para poder usarla en los test */

    /**
     * Consturctor.
     * @param tabla Nombre de la tabla de la base de datos a la que hará referencia.
     */
    Repository(String tabla) {
        this.tabla = tabla;
        conexion = Singleton.getConexion();
        metaData = new MetaData(tabla, conexion);
        queryBuilder = new QueryBuilder(tabla, metaData.getColumNames());
        row = new Object[metaData.getColumCount()];
    }

    /**
     * Asigna un valor en la posicion de Row según indica el parámetro indexColum.
     * Tiene sobrecarga pasando como paramtro el nombre (String) de la columna.
     * @param indexColum indice de la columna de la tabla.
     * @param value valor que se quiere asignar.
     */
    public void set(int indexColum, Object value) {
        row[indexColum] = value;
    }

    /**
     * Asigna un valor en la posicion de Repository según indica el parámetro nombreColum.
     * Tiene sobrecarga pasando como parametro el indice (int) de la columna.
     * @param nombreColum nombre de la columna de la tabla.
     * @param value valor que se quiere asignar.
     */
    public void set(String nombreColum, Object value) {
        int index = -1;
        for (int i = 0; i<metaData.getColumCount(); i++)
            if (metaData.getColumNames()[i].equals(nombreColum)) index = i;
        row[index] = value;
    }

    /**
     * Toma el valor de la posición en Row que indica el parámetro indexColum.
     * Tiene sobrecarga pasando como parámetro en nombre (String) de la columna.
     * @param indexColum indice de la columna de la tabla.
     * @return el valor que contiene la posición de Row según indica indexColum.
     */
    public Object get(int indexColum) {
        return row[indexColum];
    }

    /**
     * Toma el valor de la posición en Row que indica el parámetro nombreColum.
     * Tiene sobrecarga pasando como parámetro el indice (int) de la columna.
     * @param nombreColum nombre de la columna de la tabla.
     * @return el valor que contine la posición de Row según indica nombreColum.
     */
    public Object get(String nombreColum) {
        int index = -1;
        for(int i=0; i<metaData.getColumCount(); i++)
            if(metaData.getColumNames()[i].equals(nombreColum))
                index = i;
        return row[index];
    }

    /**
     * Carga Row con un registro existente de la base de datos.
     * Se requerirá su uso antes de los procedimientos de aptualización o eliminación.
     * @param id el identificador del registro a actualizar o eliminar.
     */
    public void load(int id) {
        String query = "select * from " + tabla + " where id = " + id;
        ResultSet resultset = conexion.getResultSet(query);
        try {
            for(int i=0; i<metaData.getColumCount(); i++)
                row[i] = resultset.getObject(i+1);
            resultset.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    /**
     * Procedimiento para insertar un registro en la base de datos.
     * Se necesita que Row esté previamente cargado con los datos del registro a insertar.
     */
    public void insert() {
        String query = queryBuilder.createQueryInsert();
        PreparedStatement statement = conexion.getStatement(query);
        try {
            for(int i=1; i<metaData.getColumCount(); i++) {
                if(metaData.getColumTypes()[i].equals("INTEGER"))
                    statement.setInt(i, (int)row[i]);
                if(metaData.getColumTypes()[i].equals("TEXT"))
                    statement.setString(i, (String)row[i]);
                if(metaData.getColumTypes()[i].equals("REAL"))
                    statement.setDouble(i, (double)row[i]);
            }
            statement.execute();
            statement.close();
        }catch(Exception e) { e.printStackTrace(); }
    }

    /**
     * Procedimiento para aptualizar un registro de la base de datos.
     * Se necesita que Row esté previamente cargado con los datos del registro a aptualizar.
     */
    public void update() {
        String query = queryBuilder.createQueryUpdate();
        PreparedStatement statement = conexion.getStatement(query);
        try {
            for(int i=1; i<metaData.getColumCount(); i++) {
                if(metaData.getColumTypes()[i].equals("INTEGER"))
                    statement.setInt(i, (int)row[i]);
                if(metaData.getColumTypes()[i].equals("TEXT"))
                    statement.setString(i, (String)row[i]);
                if(metaData.getColumTypes()[i].equals("REAL"))
                    statement.setDouble(i, (double)row[i]);
            }
            /* esto es el: "where id = id" :D :D es en lo único que se diferencia con insert */
            statement.setInt(metaData.getColumCount(), (int)row[0]);
            statement.execute();
            statement.close();
        }catch(Exception e) { e.printStackTrace(); }
    }

    /**
     * Procedimiento para eliminar un registro de la base de datos.
     * Se necesita que Row esté previamente cargado con el registro a eliminar.
     */
    public void delete() {
        String query = queryBuilder.createQueryDelete() + get("id");
        PreparedStatement statement = conexion.getStatement(query);
        try {
            statement.execute();
            statement.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}