package GUI;

import Controller.OcuparController;
import DTO.PasajeroBusquedaDTO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

@SuppressWarnings("serial")
public class TablaPasajerosGUI extends JFrame {

    @SuppressWarnings("unused")
	private OcuparController controller;
    private JTable tabla;
    private JButton btnAceptar;
    private ActionListener AceptarAction;

    public JButton getBtnAceptar() {
        return btnAceptar;
    }
    public JTable getTabla() {
        return tabla;
    }
    public ActionListener getAceptarAction() {
        return AceptarAction;
    }
    public void setAceptarAction(ActionListener aceptarAction) {
        AceptarAction = aceptarAction;
    }

    public TablaPasajerosGUI(OcuparController unController, List<PasajeroBusquedaDTO> lista, int flag) {


        this.controller = unController;

        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);
        this.setSize(550,300);

        armarTabla(lista, flag);
    }

    public void armarTabla(List<PasajeroBusquedaDTO> lista, int flag) {

        JPanel PanelTablaGUI = new JPanel();
        PanelTablaGUI.setBorder(new TitledBorder(null, "Datos del Pasajero", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        setContentPane(PanelTablaGUI);
        PanelTablaGUI.setLayout(new BorderLayout());
        int tam = lista.size();

        Object[][] data = new Object[tam][5];
        Object[] columnNames = {" ", "Apellido", "Nombre", "Tipo Documento", "Numero Documento"};

        System.out.println("Pre for");
        for(int i=0; i<lista.size(); ++i) {
            data[i][0] = false;
            data[i][1] = lista.get(i).getApellido();
            data[i][2] = lista.get(i).getNombre();
            data[i][3] = lista.get(i).getTipodoc();
            data[i][4] = lista.get(i).getNdoc();
        }

        tabla = new JTable(data, columnNames) {
            @SuppressWarnings( { "rawtypes", "unchecked" } )
			@Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }


        };
        if(flag == 0) {
            tabla.setDefaultRenderer(Boolean.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    int rowCount = tabla.getRowCount();
                    int cont = 0;
                    for (int c = 0; c < rowCount; c++) {
                        if ((Boolean) table.getModel().getValueAt(c, column))
                            cont++;
                    }
                    if (cont > 1) {
                        for(int x = 0; x < rowCount; x++){
                            if ((Boolean) table.getModel().getValueAt(x, column) && !(x == row)){
                                table.getModel().setValueAt(false, x, column);
                            }
                        }
                    }
                    JCheckBox checkBox = new JCheckBox("", value.equals(true));
                    checkBox.setHorizontalAlignment(SwingConstants.CENTER);
                    return checkBox;
                }
            });
        }

        tabla.getTableHeader().setReorderingAllowed(false);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(5);
        tabla.setPreferredScrollableViewportSize(new Dimension(400, 200));
        JScrollPane sp = new JScrollPane(tabla);
        PanelTablaGUI.add(sp, BorderLayout.CENTER);

        JPanel boton = new JPanel();
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(306, 323, 156, 21);
        btnAceptar.setForeground(new Color(255, 255, 255));
        btnAceptar.setBackground(new Color(50, 205, 50));
        boton.add(btnAceptar, BorderLayout.SOUTH);
        PanelTablaGUI.add(boton, BorderLayout.SOUTH);

    }
}
