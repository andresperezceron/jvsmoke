package jvsmoke.com;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;

/**
 * Factoria de componentes JSwing y Com (heredean de JSwing), listos para ser añandidos al JFrame.
 * Para ulilizar esta Factoria se han de quitar los layout del JFrame.
 * Todos sus metodos son estáticos.
 */
public class ComFactory {

    /**
     * Proporciona un JButton con la imagen de fondo y con Tooltip.
     * @param bounds rectángulo que forma las dimensiones del componente en el JFrame.
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

    public static JButton Button(@NotNull Rectangle bounds, String titulo, boolean enabled) {
        JButton b = new JButton(titulo);
        b.setBounds(bounds);
        b.setEnabled(enabled);
        return b;
    }

    public static ComboBoxCom Combo(@NotNull Rectangle bounds, @NotNull String titulo, boolean enabled) {
        ComboBoxCom cb = (titulo.equals("")) ? new ComboBoxCom() : new ComboBoxCom(titulo);
        cb.setFont(new Font("Dialog", Font.PLAIN, 12));
        cb.setBounds(bounds.getBounds());
        cb.setEnabled(enabled);
        return cb;
    }

    /**
     * Se ulitiza el componente JLabel para poner cualquier imagen en el JFrame.
     * @param bounds rectángulo que forma las dimensiones del componente en el JFrame.
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
     * Proporciona un JLabel.
     * @param bounds rectángulo que forma las dimensiones del componente en el JFrame.
     * @param titulo texto que aparecera en el JLabel.
     * @param font fuente del texto del JLabel.
     * @return JLabel listo para se añadido al JFrame.
     */
    public static JLabel Label(@NotNull Rectangle bounds, String titulo, Font font) {
        JLabel label = new JLabel(titulo);
        label.setBounds(bounds);
        label.setText(titulo);
        label.setFont(font);
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
        PanelCom panelCom = new PanelCom(null);
        panelCom.setBounds(bounds.getBounds());
        panelCom.setBorder(BorderFactory.createTitledBorder(titulo));
        panelCom.setEnabled(enabled);
        for(Component aCom : com) panelCom.add(aCom);
        return panelCom;
    }

    public static PanelCom Panel(@NotNull Rectangle bounds, String titulo, TableCom tabla, boolean enabled) {
        PanelCom p = new PanelCom(new BorderLayout());
        p.setLayout(new BorderLayout());
        p.setBounds(bounds.getBounds());
        p.setBorder(BorderFactory.createTitledBorder(titulo));
        p.setEnabled(enabled);
        p.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return p;
    }

    public static TableCom Table(String[] nomColum, int[] tamColum) {
        TableCom t = new TableCom(nomColum, tamColum);
        t.setRowSorter(new TableRowSorter<>(t.getModel()));
        return  t;
    }

    /**
     * Propociona un TextFielCom con las posibilidades de utilizar titulo de label y que sea solo numérico.
     * @param bounds rectangulo que forma las dimensiones del componente en el Frame.
     * @param titulo título activa la función titulo-label; para sin función título pasarle cadena en blanco.
     * @param onlynumber combierte a TextFieldCom para texto solo numérico.
     * @param enabled activar o desactivar el TextFielCom.
     * @return TextFielCom listo para ser agregado al JFrame.
     */
    public static TextFieldCom TextFieldCom(@NotNull Rectangle bounds, String titulo, boolean onlynumber, boolean enabled) {
        TextFieldCom tfc = new TextFieldCom(titulo, onlynumber);
        tfc.setBounds(bounds.getBounds());
        tfc.setEnabled(enabled);
        return tfc;
    }
}
