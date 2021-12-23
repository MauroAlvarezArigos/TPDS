package GUI;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Controller.FacturarController;
import Exceptions.CampoFaltanteException;
import Exceptions.NoConcordanciaException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class FacturarGUI extends JFrame {

	private JLabel lblNumHabitacion;
	private JLabel lblHoraDeSalida;
	private JLabel lblCuit;
	private JTextField tbxNumHabitacion;
	private JTextField tbxHoraSalida;
	private JTextField tbxCuit;
	private JTable tablePasajero;
	private JCheckBox cbxFacturaTercero;
	private DefaultTableModel model;
	private JPanel panelOcupanteHabitacion;
	private JScrollPane scrollPane;
	
	private FacturarController controller;

	
	public FacturarGUI() {
		this.controller = new FacturarController(this);
		this.setSize(700, 350);
		this.setTitle("Facturar");
		getContentPane().setLayout(null);
		
		JPanel checkOut = new JPanel();
		checkOut.setBounds(10, 20, 268, 136);
		getContentPane().add(checkOut);
		checkOut.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Check Out", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		checkOut.setLayout(null);
		
		lblNumHabitacion = new JLabel("Numero de habitacion");
		lblNumHabitacion.setBounds(10, 28, 125, 20);
		checkOut.add(lblNumHabitacion);
		
		tbxNumHabitacion = new JTextField();
		tbxNumHabitacion.setBounds(162, 29, 96, 20);
		tbxNumHabitacion.setColumns(10);
		checkOut.add(tbxNumHabitacion);
		
		lblHoraDeSalida = new JLabel("Hora de salida");
		lblHoraDeSalida.setBounds(10, 58, 125, 20);
		checkOut.add(lblHoraDeSalida);
		
		tbxHoraSalida = new JTextField();
		tbxHoraSalida.setColumns(10);
		tbxHoraSalida.setBounds(162, 59, 96, 20);
		checkOut.add(tbxHoraSalida);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setBounds(65, 106, 85, 20);
		checkOut.add(btnCancelar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(162, 106, 96, 20);
		checkOut.add(btnBuscar);
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setBackground(new Color(0, 128, 0));
		
		btnCancelar.addActionListener(e -> dispose());
		
		btnBuscar.addActionListener(e -> {
			try {
				controller.checkOut();
			} catch (CampoFaltanteException e1) {
				mostrarError(" ", "Falta rellenar un campo");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		});
		
		JPanel panelFacturaTercero = new JPanel();
		panelFacturaTercero.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Facturar a Tercero", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		panelFacturaTercero.setBounds(10, 166, 268, 80);
		getContentPane().add(panelFacturaTercero);
		panelFacturaTercero.setLayout(null);
		
		lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(10, 22, 90, 20);
		lblCuit.setEnabled(false);
		panelFacturaTercero.add(lblCuit);
		
		tbxCuit = new JTextField();
		tbxCuit.setBounds(103, 23, 145, 20);
		tbxCuit.setColumns(10);
		tbxCuit.setEnabled(false);
		panelFacturaTercero.add(tbxCuit);
		
		cbxFacturaTercero = new JCheckBox("Facturar a tercero");
		cbxFacturaTercero.setBounds(50, 48, 145, 21);
		cbxFacturaTercero.addActionListener(e->{
			if(cbxFacturaTercero.isSelected()) {
				lblCuit.setEnabled(true);
				tbxCuit.setEnabled(true);
				panelOcupanteHabitacion.setEnabled(false);
				tablePasajero.setVisible(false);
				scrollPane.setVisible(false);
			}else {
				lblCuit.setEnabled(false);
				tbxCuit.setEnabled(false);
				panelOcupanteHabitacion.setEnabled(true);
				tablePasajero.setVisible(true);
				scrollPane.setVisible(true);
			}
		});
		panelFacturaTercero.add(cbxFacturaTercero);
		
		panelOcupanteHabitacion = new JPanel();
		panelOcupanteHabitacion.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ocupantes de la Habitacion", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		panelOcupanteHabitacion.setBounds(288, 20, 388, 226);
		getContentPane().add(panelOcupanteHabitacion);
		panelOcupanteHabitacion.setLayout(null);
		panelOcupanteHabitacion.setLayout(new BoxLayout(panelOcupanteHabitacion, BoxLayout.Y_AXIS));



		model = new DefaultTableModel();

		tablePasajero = new JTable(model){
			@Override
			public Class getColumnClass(int column){
				if(column == 0){
					return Boolean.class;
				}else{
					return String.class;
				}
			}
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0;
			}
		};

		tablePasajero.setDefaultRenderer(Boolean.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				int rowCount = tablePasajero.getRowCount();
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

		tablePasajero.setBounds(20, 20, 300, 196);
		model.addColumn("Seleccionar", new Object[]{});
		model.addColumn("Nombre", new Object[]{});
		model.addColumn("Apellido", new Object[]{});
		model.addColumn("DNI", new Object[]{});
		model.addColumn("TipoDNI", new Object[]{});

		tablePasajero.getColumnModel().getColumn(0).setMaxWidth(75);
		tablePasajero.getColumnModel().getColumn(1).setMaxWidth(75);
		tablePasajero.getColumnModel().getColumn(2).setMaxWidth(75);
		tablePasajero.getColumnModel().getColumn(3).setMaxWidth(75);
		tablePasajero.getColumnModel().getColumn(4).setMaxWidth(75);

		tablePasajero.getTableHeader().setReorderingAllowed(false);

		tablePasajero.setVisible(true);
		scrollPane = new JScrollPane(tablePasajero);
		tablePasajero.setFillsViewportHeight(true);
		scrollPane.setVisible(true);
		panelOcupanteHabitacion.add(scrollPane);
		
		JButton btnFacturar = new JButton("Facturar");
		btnFacturar.setForeground(Color.WHITE);
		btnFacturar.setBackground(new Color(0, 128, 0));
		btnFacturar.setBounds(580, 256, 96, 20);
		getContentPane().add(btnFacturar);
		
		btnFacturar.addActionListener(e->{
			try {
				controller.facturar();
			} catch (NoConcordanciaException e1) {
				mostrarError("","No se encontro la Persona Juridica");
			}
		});
	}
	
	//Getters and setters
	public JLabel getLblNumHabitacion() {return lblNumHabitacion;}
	public void setLblNumHabitacion(JLabel lblNumHabitacion) {this.lblNumHabitacion = lblNumHabitacion;}
	public JLabel getLblHoraDeSalida() {return lblHoraDeSalida;}
	public void setLblHoraDeSalida(JLabel lblHoraDeSalida) {this.lblHoraDeSalida = lblHoraDeSalida;}
	public JLabel getLblCuit() {return lblCuit;}
	public void setLblCuit(JLabel lblCuit) {this.lblCuit = lblCuit;}
	public JTextField getTbxNumHabitacion() {return tbxNumHabitacion;}
	public void setTbxNumHabitacion(JTextField tbxNumHabitacion) {this.tbxNumHabitacion = tbxNumHabitacion;}
	public JTextField getTbxHoraSalida() {return tbxHoraSalida;}
	public void setTbxHoraSalida(JTextField tbxHoraSalida) {this.tbxHoraSalida = tbxHoraSalida;}
	public JTextField getTbxCuit() {return tbxCuit;}
	public void setTbxCuit(JTextField tbxCuit) {this.tbxCuit = tbxCuit;}
	public JTable getTablePasajero() {return tablePasajero;}
	public void setTablePasajero(JTable tablePasajero) {this.tablePasajero = tablePasajero;}
	public JCheckBox getCbxFacturaTercero() {return cbxFacturaTercero;}
	public void setCbxFacturaTercero(JCheckBox cbxFacturaTercero) {this.cbxFacturaTercero = cbxFacturaTercero;}
	public FacturarController getController() {return controller;}
	public void setController(FacturarController controller) {this.controller = controller;}
	public DefaultTableModel getModel() {
		return model;
	}

	public void mostrarError(String titulo, String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public int optionMessageGUI(String titulo, String detalle, Object[] options){
		JFrame padre = (JFrame) SwingUtilities.getWindowAncestor(this);
		return JOptionPane.showOptionDialog(padre,
				detalle,titulo,JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,     //do not use a custom Icon
				options,  //the titles of buttons
				options[0]);
	}
	
}
