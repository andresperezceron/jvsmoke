package jvsmoke.core;

/**
 * Interfaz implementada por Model.
 * Al ser Model Abstracta obliga a implementar el método finalCheck a todos los modelos
 * que hereden de ella.
 */
public interface Check {

    /**
     * Metedo destinado a verificar los datos antes de su inserción o actualización.
     * @return nos indica si los datos son correctos antes de ser procesados.
     */
    boolean finalCheckToInsert();

    boolean finalCheckToUpdate();
}
