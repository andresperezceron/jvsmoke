package jvsmoke.core;

import javax.swing.*;
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
     * Realiza la acción indicada por el parámetro ACTION_CONTROLLER.
     * @param ACTION_CONTROLLER el tipo de acción a realizar con el controlador.
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
     * Procedimiento para redireccionar a otra view. Es el procedimiento al que hace llamada eRedirect.
     * Cierra la view actual e inicializa controller (ACTION_INDEX) que levanta la nueva view.
     * @param ctrl contoller al que queremos redireccionar.
     */
    public void redirect(Controller ctrl) {
        view.dispose();
        ctrl.executeAction(ACTION_INDEX);
    }

    /**
     * Este evento es el que asignaremos normalmente a JButtons para realizar las operaciones del CRUD.
     * @param ACTION_CONTROLLER el tipo de acción a realizar con el controlador.
     * @return Listener de tipo Action.
     */
    protected ActionListener eActionContoller(int ACTION_CONTROLLER){
        return e -> executeAction(ACTION_CONTROLLER);
    }

    /**
     * Evento utilizado para redireccionar utilizando el método redirect(Controller).
     * @param ctrl controller ya instaciado a que queremos redireccionar.
     * @return Listener de tipo Action.
     */
    protected ActionListener eRedirect(Controller ctrl){
        return e -> redirect(ctrl);
    }

    /**
     * Proporciona la View que le pasamos a Controller.
     * @return JFrame View que controlamos desde Controller.
     */
    public JFrame getView() {
        return view;
    }

    /**
     * Proporciona el Model que le pasamos a Controller.
     * @return Model que controlamos desde Controller.
     */
    public Model getModel() {
        return model;
    }

}