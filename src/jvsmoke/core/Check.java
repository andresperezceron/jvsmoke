package jvsmoke.core;

/**
 * Interfaz implementada por Model.
 * Al ser Model Abstracta obliga a implementar el método finalCheck a todos los modelos
 * que hereden de ella. Siver para chequear los datos previos a los procedimientos de
 * inserción o actualización de datos.
 */
public interface Check {

    /**
     * Verificalos datos antes de su inserción.
     * @return nos indica si los datos son correctos antes de ser insertados.
     */
    boolean finalCheckToInsert();

    /**
     * Verificalos datos antes de su actualización.
     * @return nos indica si los datos son correctos antes de ser actualizados.
     */
    boolean finalCheckToUpdate();
}
