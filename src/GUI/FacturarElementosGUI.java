package GUI;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Controller.FacturarController;

@SuppressWarnings("serial")
public class FacturarElementosGUI extends JFrame{
	private JTable table;
	private DefaultTableModel model;
	private FacturarController controller;
	private JCheckBox cbxEstadia;
	private JLabel lblValorSubtotal;
	private JLabel lblValorIVA;
	private JLabel lblValorTotal;




	public JTable getTable() {
		return table;
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public JCheckBox getCbxEstadia() {
		return cbxEstadia;
	}
	public JLabel getLblValorSubtotal() {
		return lblValorSubtotal;
	}
	public JLabel getLblValorIVA() {
		return lblValorIVA;
	}
	public JLabel getLblValorTotal() {
		return lblValorTotal;
	}

	public FacturarElementosGUI(FacturarController controller, String facturarA, String tipoFactura) {
		this.controller = controller;
		this.setSize(700, 375);
		this.setTitle("Facturar");
		getContentPane().setLayout(null);


		model = new DefaultTableModel();
		table = new JTable(model){
			@Override
			public Class getColumnClass(int column){
				if(column == 2){
					return Boolean.class;
				}else{
					return String.class;
				}
			}
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 2;
			}
		};

		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				controller.updateTotalValue();
			}
		});

		JPanel panelTable = new JPanel();
		panelTable.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ocupantes de la Habitacion", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		panelTable.setBounds(37, 132, 435, 151);
		getContentPane().add(panelTable);
		panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.Y_AXIS));

		table.getTableHeader().setReorderingAllowed(false);
		table.setBounds(37, 132, 356, 151);
		getContentPane().add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		scrollPane.setVisible(true);
		panelTable.add(scrollPane);

		cbxEstadia = new JCheckBox("Valor de la estadia: $");
		cbxEstadia.setBounds(37, 102, 218, 23);
		getContentPane().add(cbxEstadia);
		cbxEstadia.addActionListener(e -> {
				controller.updateTotalValue();
		});
		
		JLabel lblGuia = new JLabel("Seleccione los elementos que seran incluidos dentro de la factura");
		lblGuia.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuia.setBounds(37, 75, 586, 23);
		getContentPane().add(lblGuia);
		
		JLabel lblTipoFactura = new JLabel("Tipo de Factura: "+tipoFactura);
		lblTipoFactura.setBounds(47, 11, 152, 14);
		getContentPane().add(lblTipoFactura);
		
		JLabel lblFacturarA = new JLabel("Facturar a: "+facturarA);
		lblFacturarA.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblFacturarA.setBounds(47, 36, 576, 40);
		getContentPane().add(lblFacturarA);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(480, 198, 143, 85);
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblSubTotal = new JLabel("SubTotal");
		GridBagConstraints gbc_lblSubTotal = new GridBagConstraints();
		gbc_lblSubTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubTotal.anchor = GridBagConstraints.EAST;
		gbc_lblSubTotal.gridx = 0;
		gbc_lblSubTotal.gridy = 0;
		panel.add(lblSubTotal, gbc_lblSubTotal);
		
		lblValorSubtotal = new JLabel("$ 0.00");
		GridBagConstraints gbc_lblValorSubtotal = new GridBagConstraints();
		gbc_lblValorSubtotal.anchor = GridBagConstraints.WEST;
		gbc_lblValorSubtotal.insets = new Insets(0, 0, 5, 0);
		gbc_lblValorSubtotal.gridx = 1;
		gbc_lblValorSubtotal.gridy = 0;
		panel.add(lblValorSubtotal, gbc_lblValorSubtotal);
		
		JLabel lblIVA = new JLabel("IVA");
		GridBagConstraints gbc_lblIVA = new GridBagConstraints();
		gbc_lblIVA.anchor = GridBagConstraints.EAST;
		gbc_lblIVA.insets = new Insets(0, 0, 5, 5);
		gbc_lblIVA.gridx = 0;
		gbc_lblIVA.gridy = 1;
		panel.add(lblIVA, gbc_lblIVA);
		
		lblValorIVA = new JLabel("$ 0.00");
		GridBagConstraints gbc_lblValorIVA = new GridBagConstraints();
		gbc_lblValorIVA.anchor = GridBagConstraints.WEST;
		gbc_lblValorIVA.insets = new Insets(0, 0, 5, 0);
		gbc_lblValorIVA.gridx = 1;
		gbc_lblValorIVA.gridy = 1;
		panel.add(lblValorIVA, gbc_lblValorIVA);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.EAST;
		gbc_lblTotal.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotal.gridx = 0;
		gbc_lblTotal.gridy = 3;
		panel.add(lblTotal, gbc_lblTotal);
		
		lblValorTotal = new JLabel("$ 0.00");
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblValorTotal = new GridBagConstraints();
		gbc_lblValorTotal.anchor = GridBagConstraints.WEST;
		gbc_lblValorTotal.gridx = 1;
		gbc_lblValorTotal.gridy = 3;
		panel.add(lblValorTotal, gbc_lblValorTotal);

		JButton btnGuardarFactura = new JButton("Guardar");
		btnGuardarFactura.setBackground(new Color(0,128,0));
		btnGuardarFactura.setForeground(Color.WHITE);
		btnGuardarFactura.setBounds(575,300,100,25);
		getContentPane().add(btnGuardarFactura);
		btnGuardarFactura.addActionListener(e -> {
			controller.guardarFactura();


		});

	}
}
