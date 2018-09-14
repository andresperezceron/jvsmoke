package jvsmoke.core;

import jvsmoke.conexion.Conexion;
import org.jetbrains.annotations.NotNull;

/**
 * Mini ORM que nos proporciona información esencial para trabajar con cualquier esquema de la db.
 * Ofrece un vector con los nombres del esquema representado en el constructor "tabla", y otro con
 * el tipo primitivo de datos. Estos tipos pueden ser 3: INTEGER, REAL y TEXT.
 */
public class MetaData {
    private String[] colum_names;
    private String[] colum_types;

    /**
     * Contructor.
     * @param tabla nombre de la tabla en la db.
     * @param db conexión abierta con la db.
     */
    public MetaData(String tabla, @NotNull Conexion db) {
        String query = "select sql from sqlite_master where name = '" + tabla + "';";
        String master = db.exeSimpleQuery(query).toString();
        master = master.replaceAll("\\(", " ")
                .replaceAll("\\)", " ")
                .replaceAll(",", " ")
                .replaceAll("\\n", " ");
        String[] esquema_split = master.split(" ");

        int total_campos = 0;
        for(String anEsquema_split : esquema_split)
            if (anEsquema_split.equals("INTEGER") || anEsquema_split.equals("TEXT") || anEsquema_split.equals("REAL"))
                total_campos++;

        colum_names = new String[total_campos];
        colum_types = new String[total_campos];

        for(int i=0, j=0, k=0; i<esquema_split.length; i++)
            if(esquema_split[i].equals("INTEGER") || esquema_split[i].equals("TEXT") || esquema_split[i].equals("REAL")) {
                colum_types[k++] = esquema_split[i];
                colum_names[j++] = esquema_split[i-1];
            }
    }

    /**
     * Metodo para obtener los nombres de las columnas de la tabla.
     * @return vector con el nombre de las columnas de la tabla.
     */
    public String[] getColumNames() {
        return colum_names;
    }

    /**
     * Metodo para obtener el tipo de datos primitivo de las columnas de la tabla.
     * @return vector con el tipo de datos de las columnas de la tabla.
     */
    public String[] getColumTypes() {
        return colum_types;
    }

    /**
     * Metodo para obtener el total de columnas que forman la tabla.
     * @return el total de columnas que forman la tabla.
     */
    public int getColumCont() {
        return colum_names.length;
    }
}
