package jvsmoke.com;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * Proporciona componentes de la libreria de JSwing ya creados.
 * Para ulilizar esta clase se han de quitar los layout del JFrame.
 * Todos los metodos sus metodos son estaticos.
 */
public class ComFactory {

    /**
     * @param bounds rectangulo que forma las dimensiones del componente en el Frame.
     * @param ruta_img ruta de del archivo .png
     * @return JLabel con la imagen de fondo y con el borde negro.
     */
    public static JLabel Label(@NotNull Rectangle bounds, String ruta_img) {
        JLabel label = new JLabel(new ImageIcon(ruta_img));
        label.setBounds(bounds.getBounds());
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }
}
