package jvsmoke.core;

/**
 * Interfaz implementada por Model.
 * Al ser Model Abstracta obliga a implementar el método finalCheck a todos los modelos
 * que hereden de el. Se utiliza para chequear los datos previos a los procedimientos de
 * inserción o actualización.
 */
public interface Check {

    /**
     * Verifica los datos antes de su inserción.
     * @return nos indica si los datos son correctos antes de ser insertados.
     */
    boolean finalCheckToInsert();

    /**
     * Verifica los datos antes de su actualización.
     * @return nos indica si los datos son correctos antes de ser actualizados.
     */
    boolean finalCheckToUpdate();
}
