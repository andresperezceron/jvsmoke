package jvsmoke.core;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

/**
 * Interfaz destinada a agrupar los listener de tipo Action, Focus y Mouse de la View en uno solo.
 * Los eventos que solo influyen a la view suelen ser simples, por ello "Alle" los agrupa para una
 * mejor organización desde los controladores y para crear el tipo de datos que implementa el método
 * de la interfaz EventViewManager, la cual está totalmente ligada a "Alle".
 */
public interface Alle extends ActionListener, FocusListener, MouseListener {}
