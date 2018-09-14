package jvsmoke.conexion;

import jvsmoke.JvsConfig;

/**
 * Patrón Singleton para manejar la Conexión.
 * Hay que cargar previamente la propiedad de configuracion jvsmoke.JvsConfig.SQLITE3_STR_CONEXION
 */
public class Singleton {
    private static Conexion conexion;
    private Singleton() {}

    /**
     * Metodo estático para obtener la conexión con la db.
     * @return conexion abierta con la db.
     */
    public static Conexion getConexion() {
        if(conexion == null)
            conexion = new Conexion(JvsConfig.SQLITE3_STR_CONEXION);
        return conexion;
    }
}