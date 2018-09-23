package jvsmoke.core;

/**
 * Está destinada a ser implementada en las vistas. Es opcional, y su utilización es solo
 * para tener agrupados eventos simples relacionados que solo influyen en la view.
 */
public interface EventViewManager {

    /**
     * Proporciona el evento de tipo "Alle" que se asignará en el controlador en su instancia.
     * @return evento del tipo "Alle".
     */
    Alle getAlle();
}
