package jvsmoke.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * JTextField modificado. Ofrece un evento de focus que nos permite hacer que el
 * texto de TextFielCom haga de label. Además ofrece otro evento de tipo keypress
 * para hacer que TextFeilCom solo sea numérico.
 */
public class TextFieldCom extends JTextField {
    private boolean onlyNumber;
    private String titulo;
    private Font font = new Font(Font.DIALOG, Font.ITALIC, getFont().getSize());
    private Font auxFont;
    private Color background = new Color(254, 249, 231);
    private Color foreground = Color.GRAY;

    /**
     * Contructor.
     * @param titulo texto para el TextFielCom que hará de label del mismo.
     */
    public TextFieldCom(String titulo) {
        this.titulo = titulo;
        onlyNumber = false;
        auxFont = this.getFont();
        setText(titulo);
        setFont(font);
        setBackground(background);
        setForeground(foreground);
        addFocusListener(new eTitulo());
    }

    /**
     * Subclase que implementa el evento FocusListener del TextFielCom.
     */
    private class eTitulo implements FocusListener {

        /**
         * Captura del evento focusGained de TextFielCom.
         * Este evento es agreado a TextFieldCom si se ha instanciado con el parámetro
         * titulo y es el encargado de hacer que el text de TextFieldCom haga de label.
         * @param e evento de tipo focusGained a capturar.
         */
        @Override
        public void focusGained(FocusEvent e) {
            if(getText().equals(titulo)){
                setText("");
                setFont(auxFont);
                setForeground(Color.BLACK);
                setBackground(Color.WHITE);
            }
        }

        /**
         * Captura del evento focusLost de TextFielCom.
         * Este evento es agreado a TextFieldCom si se ha instanciado con el parámetro
         * titulo y es el encargado de hacer que el text de TextFieldCom haga de label.
         * @param e evento de tipo focusLost a capturar.
         */
        @Override
        public void focusLost(FocusEvent e) {
            if(getText().equals("")) {
                setText(titulo);
                setFont(font);
                setForeground(foreground);
                setBackground(background);
            }
        }
    }
}
