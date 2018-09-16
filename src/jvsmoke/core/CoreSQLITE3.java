package jvsmoke.core;

import jvsmoke.conexion.Conexion;
import jvsmoke.conexion.Singleton;

/**
 * Clase destinada a ser herededa, aunque puede ulizarse tal cual.
 * Tiene como dependencia la tabla a la que representará en la base de datos.
 * Proporciona: una conexión con la base de datos, un MetaData (miniORM) y
 * un QueryBuilder para las consultas que necesitemos de la base de datos.
 * Las clases que extienden de ella son: Controller, Model y Repository.
 */
public class CoreSQLITE3 {
    protected Conexion conexion;
    protected MetaData metaData;
    protected QueryBuilder queryBuilder;

    /**
     * Consturctor.
     * @param tabla Nombre de la tabla de la base de datos a la que hará referencia.
     */
    public CoreSQLITE3(String tabla) {
        conexion = Singleton.getConexion();
        metaData = new MetaData(tabla, conexion);
        queryBuilder = new QueryBuilder(tabla, metaData.getColumNames(), conexion);
    }
}
