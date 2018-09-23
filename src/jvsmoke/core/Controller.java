package jvsmoke.core;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador base.
 * Proporciona dos eventos de tipo acctionListener, uno para ejecutar las acciones: create, update y delete,
 * y el segundo para redireccionar a otra view y publica los metodos executeAction y redirect.
 * Se pueden instanciar controladores pasando null como modelo o lo que lo mismo se pueden instanciar controladores
 * sin modelos, a igual que se pueden pasar vistas sin nececidad de que implementen la interfaz EventViewManager.
 */
public abstract class Controller {
    public static final int ACTION_INDEX  = 1;
    protected final int ACTION_CREATE = 2;
    protected final int ACTION_UPDATE = 3;
    protected final int ACTION_DELETE = 4;
    protected final int ACTION_RENDER = 5;

    private JFrame view;
    private Model model;

    protected Alle e;
    protected Repository repository;

    /**
     * Constructor.
     * Si hay modelo creamos el repository, y si la vista implementa EventViewManager creamos Alle.
     * @param view vista a controlar.
     * @param model modelo a controlar.
     */
    public Controller(JFrame view, Model model) {
        this.view = view;
        this.model = model;
        if(model != null) repository = model.getRepository();
        if(view instanceof EventViewManager) e = ((EventViewManager) view).getAlle();
    }

    /**
     * Estos son los metodos que poseen los controladores.
     * idex: para la inicializacion de la vista.
     * create: agrupa todas las operaciones de inserción de datos.
     * upcate: agrupa todas las operaciones de actualización de datos.
     * delete: agrupa todas las operaciones de eliminación de datos.
     * render: renderizar las views.
     */
    protected abstract void  index();
    protected abstract void create();
    protected abstract void update();
    protected abstract void delete();
    protected abstract void render();

    /**
     * @param ACTION_CONTROLLER
     * @return
     */
    protected ActionListener eActionContoller(int ACTION_CONTROLLER){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeAction(ACTION_CONTROLLER);
            }
        };
    }

    /**
     * @param ctrl
     * @return
     */
    protected ActionListener eRedirect(Controller ctrl){
        return e -> redirect(ctrl);
    }

    /**
     * @param ACTION_CONTROLLER
     */
    public void executeAction(int ACTION_CONTROLLER) {
        switch(ACTION_CONTROLLER) {
            case ACTION_INDEX  :  index(); break;
            case ACTION_CREATE : create(); break;
            case ACTION_UPDATE : update(); break;
            case ACTION_DELETE : delete(); break;
            case ACTION_RENDER : render(); break;
        }
    }

    /**
     * @param ctrl
     */
    public void redirect(Controller ctrl) {
        view.dispose();
        ctrl.executeAction(ACTION_INDEX);
    }
}
