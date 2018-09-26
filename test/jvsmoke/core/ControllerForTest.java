package jvsmoke.core;

import javax.swing.*;

public class ControllerForTest extends Controller {
    boolean executeIndex = false;
    boolean executeCreate = false;
    boolean executeUpdate = false;
    boolean executeDelete = false;
    boolean executeRender = false;

    /**
     * Constructor.
     * Clase para testear Controller.
     * Posee propiedades de tipo booleano inicializados a false para cada metodo del CRUD
     * con el fin de simplemente verificar que hacemos referencia al método correspondiente
     * desde los controladores. Ambos parámetros view y model solo necesarios para instanciar.
     * @param view  vista a controlar.
     * @param model modelo a controlar.
     */
    ControllerForTest(JFrame view, Model model) {
        super(view, model);
    }

    @Override
    protected void index() {
        executeIndex = true;
    }

    @Override
    protected void create() {
        executeCreate = true;
    }

    @Override
    protected void update() {
        executeUpdate = true;
    }

    @Override
    protected void delete() {
        executeDelete = true;
    }

    @Override
    protected void render() {
        executeRender = true;
    }
}
