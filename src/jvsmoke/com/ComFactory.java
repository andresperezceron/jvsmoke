package jvsmoke.com;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * Factoria de componentes JSwing y Com (heredean de JSwing), listos para ser añandidos al JFrame.
 * Para ulilizar esta Factoria se han de quitar los layout del JFrame.
 * Todos sus metodos son estaticos.
 */
public class ComFactory {

    /**
     * Proporciona un JButton con la imagen de fondo y se le agrega Tooltip.
     * @param bounds rectangulo que forma las dimensiones del componente en el Frame.
     * @param ruta_img ruta de del archivo .png
     * @param tooltip mensaje que aperecerá en el tooltip; para sin tooltip pasarle cadena vacía.
     * @param enabled activar o desactivar el JButton.
     * @return JButton con imagen de fondo y un tooltip.
     */
    public static JButton Button(@NotNull Rectangle bounds, String ruta_img, String tooltip, boolean enabled) {
        JButton jButton = new JButton();
        jButton.setBounds(bounds.getBounds());
        jButton.setIcon(new ImageIcon(ruta_img));
        jButton.setToolTipText(tooltip);
        jButton.setEnabled(enabled);
        return jButton;
    }

    /**
     * Se ulitiza el componente JLabel para poner cualquier imagen en el JFrame.
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

    /**
     * Proporciona un PanelCom.
     * @param bounds rectangulo que forma las dimensiones del componente en el Frame.
     * @param titulo título para el borde; para sin título pasarle cadena en blanco.
     * @param com componentes que se agregarán al PanelCom.
     * @param enabled activar o desactivar el Panel.
     * @return PanelCom listo para ser añadido al JFrame.
     */
    public static PanelCom Panel(@NotNull Rectangle bounds, String titulo, @NotNull Component[] com, boolean enabled) {
        PanelCom panelCom = new PanelCom();
        panelCom.setBounds(bounds.getBounds());
        panelCom.setBorder(BorderFactory.createTitledBorder(titulo));
        panelCom.setEnabled(enabled);
        for(Component aCom : com) panelCom.add(aCom);
        return panelCom;
    }
}
