package jvsmoke.core;

/**
 *  Crea las querys de los procedimientos para las consultas a la db.
 *  Los procedimientos de inserción y actualización se realizán seteando los valores.
 */
class QueryBuilder {
    private String tabla;
    private String[] colum_names;

    /**
     * Constructor.
     * @param tabla tabla de la base de datos.
     * @param colum_names vector con el nombre de las columnas de la tabla.
     */
    QueryBuilder(String tabla, String[] colum_names) {
        this.tabla = tabla;
        this.colum_names = colum_names;
    }

    /**
     * Crea querys para procedimientos de inserción de datos.
     * @return query para la inserción del registro cargado.
     */
    String createQueryInsert() {
        StringBuilder sql1 = new StringBuilder("insert into " + tabla + "(");
        StringBuilder sql2 = new StringBuilder("values(");
        for(int i=1; i<colum_names.length; i++) {
            sql1.append((i + 1 == colum_names.length) ? colum_names[i] + ")" : colum_names[i] + ",");
            sql2.append((i + 1 == colum_names.length) ? "?);" : "?,");
        }
        return sql1 + sql2.toString();
    }

    /**
     * Crea querys para procedimientos de actualización de datos.
     * @return query para la actualización del registro cargado.
     */
    String createQueryUpdate() {
        StringBuilder sql = new StringBuilder("update " + tabla + " set ");
        for(int i=1; i<colum_names.length; i++)
            sql.append((i + 1 == colum_names.length) ? colum_names[i] + "=? where id=?;" : colum_names[i] + "=?,");
        return sql.toString();
    }

    /**
     * Crea querys para procedimientos de eliminacion de datos. Solo podemos
     * aliminar un registro por su PRIMARY KEY.
     * @return query para procedimiento de eliminación a falta de añadirle la Primary Key.
     */
    String createQueryDelete() {
        return "delete from " + tabla + " where id=";
    }
}