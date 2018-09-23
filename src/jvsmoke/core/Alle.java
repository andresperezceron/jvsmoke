package jvsmoke.core;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

/**
 *Interfaz destinada a agrupar los listener de tipo Action, Focus y Mouse de la View en un solo evento.
 * Ya que los eventos que solo influyen a la view suelen ser simples, se agrupan en uno solo "Alle" para
 * mejor organización desde los controladores y para crear el tipo de datos que implementa el método de
 * la interfaz EventViewManager a la cual está totalmente ligada.
 */
public interface Alle extends ActionListener, FocusListener, MouseListener {}
