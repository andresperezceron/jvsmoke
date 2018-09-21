package jvsmoke.core;


/**
 * ModelForTest es una clase para testear Model.
 * Tiene el constructor modificado para que no se pueda instanciar la clase directamente desde
 * los test para así poder testear el metodo finalCheck que hereda de Model.
 * Los test se realizarán contra el equema que le pasemos al constructor. Notese que es en esta
 * clase donde implementamos finalCheck según el esquema que le pasemos al constructor.
 */
class ModelForTest extends Model {
    ModelForTest() {
        super("proveedor");
    }

    @Override
    public boolean finalCheckToInsert() {
        boolean refIsNull = getRepository().get("referencia").toString().equals("");
        boolean nomIsNull = getRepository().get("nombre").toString().equals("");
        boolean existRef = exist("referencia", getRepository().get("referencia").toString());
        boolean existNom = exist("nombre", getRepository().get("nombre").toString());
        return !refIsNull && !nomIsNull && !existRef && !existNom;
    }

    @Override
    public boolean finalCheckToUpdate() {
        /* El update lo realizamos sobre el proveedor 6 que tiene id = 22 */
        Object[] prov6 = getBy("id", 22);
        String oldRef = prov6[1].toString();
        String oldName = prov6[2].toString();
        String newRef = getRepository().get("referencia").toString();
        String newName = getRepository().get("nombre").toString();
        boolean refNull = newRef.equals("");
        boolean refDupli = exist("referencia", newRef) && !newRef.equals(oldRef);
        boolean nameNull = newName.equals("");
        boolean nameDupli = exist("referencia", newName) && !newName.equals(newRef);
        return !refNull && !refDupli && !nameNull && !nameDupli;
    }
}
