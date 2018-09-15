package jvsmoke.core;

import jvsmoke.conexion.Conexion;

import java.sql.ResultSet;

/**
 *  Crea las querys de los procedimiento; además propociona "atajos" a la hora de crear
 *  querys para consultas a la db; muy útiles cuando trabajamos desde los modelos.
 *  Tener precaución a la hora de ulizar los "atajos", ya que deben referenciar a la tabla
 *  que le pasamos en el constructor.
 *  Los procedimientos de inserción y actualización se realizán seteando los valores.
 */
public class QueryBuilder {
    private Conexion db;
    private String tabla;
    private String[] colum_names;

    /**
     * Constructor.
     * @param tabla tabla de la base de datos.
     * @param colum_names vector con el nombre de las columnas de la tabla.
     * @param db conexión con la base de datos.
     */
    public QueryBuilder(String tabla, String[] colum_names, Conexion db) {
        this.db = db;
        this.tabla = tabla;
        this.colum_names = colum_names;
    }

    /**
     * Crea querys para procedimientos de inserción de datos en la db.
     * @return query para procedimiento de inserción en modo "seteando" los datos.
     */
    public String createQueryInsert() {
        StringBuilder sql1 = new StringBuilder("insert into " + tabla + "(");
        StringBuilder sql2 = new StringBuilder("values(");
        for(int i=1; i<colum_names.length; i++) {
            sql1.append((i + 1 == colum_names.length) ? colum_names[i] + ")" : colum_names[i] + ",");
            sql2.append((i + 1 == colum_names.length) ? "?);" : "?,");
        }
        return sql1 + sql2.toString();
    }

    /**
     * Crea querys para procedimientos de actualización de datos en la db.
     * @return query para procedimiento de actualización en modo "seteando" los datos.
     */
    public String createQueryUpdate() {
        StringBuilder sql = new StringBuilder("update " + tabla + " set ");
        for(int i=1; i<colum_names.length; i++)
            sql.append((i + 1 == colum_names.length) ? colum_names[i] + "=? where id=?;" : colum_names[i] + "=?,");
        return sql.toString();
    }

    /**
     * Crea querys para procedimientos de eliminacion de datos en la db.
     * Solo podemos aliminar un registro por su PRIMARY KEY.
     * @return query para procedimiento de eliminación a falta de añadirle la Primary Key.
     */
    public String createQueryDelete() {
        return "delete from " + tabla + " where id=";
    }

    /**
     * @param strPriOrUniKey nombre de la Primary key o Unique key.
     * @param value el valor de strPriOrUniKey; es tipo Object para poder utilizar varios tipos de datos primitivos.
     * @return ResultSet cargado del registro indicado.
     */
    public ResultSet getBy(String strPriOrUniKey, Object value) {
        String query = "select * from " + tabla + " where " + strPriOrUniKey + "='" + value + "'";
        return db.getResultSet(query);
    }

    /**
     * Obtiene el id de un registro de db.
     * Ejemplo: obtener el id (PrimaryKey) desde la referencia (UniqueKey).
     * @param strPriOrUniKey clave Unique del registro.
     * @param value el propio valor de la clave Unique.
     * @return el identificador del registro.
     */
    public int getIdBy(String strPriOrUniKey, Object value) {
        String query = "select id from " + tabla + " where " + strPriOrUniKey + "='" + value + "'";
        return (int) db.exeSimpleQuery(query);
    }

    /**
     * Obtiene todos los datos de una columna de una tabla de la db.
     * Ejemplo: Obtener todas las referencias de todos los articulos.
     * @param strColum nombre de la columna.
     * @return ResulSet cargado con los datos de la columna.
     */
    public ResultSet getColum(String strColum) {
        String query = "select " + strColum + " from " + tabla;
        return db.getResultSet(query);
    }
}