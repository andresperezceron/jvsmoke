package jvsmoke.core;

import jvsmoke.conexion.Conexion;
import jvsmoke.conexion.Singleton;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase Base para todos los Modelos.
 * Implementa la interfaz Check, de hay que sea abstracta para obligar a que todas las
 * clases hijas la interpreten. Además posee un Repository, que es el que tomarán los
 * controladores al instanciarlos. Tiene conexión con la base de datos para poder ralizar
 * todos los calculos de negocio. Para ello ofrece los metodos de querySimple para las
 * consultas monoregistro y query para las consultas multiregistro. Por último decir que
 * posee varios metodos que harán de atajos a la hora de crear consultas en los modelos.
 */
public abstract class Model implements Check {
    private Conexion conexion;
    private Repository repository;
    private String tabla;

    /**
     * Constructor.
     * @param tabla nombre de la tabla a la que hará referencia.
     */
    public Model(String tabla){
        this.tabla = tabla;
        conexion = Singleton.getConexion();
        repository = new Repository(tabla);
    }

    /**
     * Publica el metodo exeSimpleQuery de conexion para que sea visible en los modelos.
     * @param query consulta monoregistro.
     * @return valor obtenido de la consulta.
     */
    protected Object querySimple(String query) {
        return conexion.exeSimpleQuery(query);
    }

    /**
     * Publica el metodo getResultSet de conexion para que sea visible en los modelos.
     * @param query consulta multiregistro.
     * @return ResultSet obtenido de la consulta.
     */
    protected ResultSet query(String query) {
        return conexion.getResultSet(query);
    }

    /**
     * Ofrece el repository ya instanciado.
     * @return Repository
     */
    protected Repository getRepository() {
        return repository;
    }

    /**
     * Proporciona un "atajo" para obtener el id de un registro de la base de datos utilizando una clave Unique.
     * Ejemplo: obtener el id (PrimaryKey) desde la referencia (UniqueKey).
     * @param strUniqueKey clave Unique del registro.
     * @param value el valor de la clave Unique.
     * @return el identificador del registro.
     */
    protected int getIdBy(String strUniqueKey, Object value) {
        String query = "select id from " + tabla + " where " + strUniqueKey + "='" + value + "'";
        return (int) conexion.exeSimpleQuery(query);
    }

    /**
     * Indica si existe o no el registro indicado el parámetro strPriOrUniKey.
     * @param strPriOrUniKey nombre de la Primary key o Unique key.
     * @param value value el valor de strPriOrUniKey.
     * @return true si existe el registro.
     */
    protected boolean exist(String strPriOrUniKey, Object value) {
        String query = "Select count(*) from " + tabla + " where " + strPriOrUniKey + "='" + value + "';";
        return (int) conexion.exeSimpleQuery(query) > 0;
    }

    /**
     * Proporcion un "atajo" para obtener registros de la base de datos utilizando tanto claves uniques como primary.
     * @param strPriOrUniKey nombre de la Primary key o Unique key.
     * @param value el valor de strPriOrUniKey.
     * @return registro existente en la base de datos. (Object[]).
     */
    protected Object[] getBy(String strPriOrUniKey, Object value) {
        String query = "select * from " + tabla + " where " + strPriOrUniKey + "='" + value + "'";
        ResultSet resultSet = query(query);
        Object[] reg = null;
        try {
            resultSet.next();
            reg = new Object[resultSet.getMetaData().getColumnCount()];
            for(int i=0; i<reg.length; i++)
                reg[i] = resultSet.getObject(i+1);
            resultSet.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return reg;
    }
}