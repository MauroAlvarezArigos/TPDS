package GUI;

import java.awt.Color;
import java.lang.ModuleLayer.Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Controller.FacturarController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class FacturarGUI extends JFrame {

	private JTextField textNumHabitacion;
	private JTextField textHoraSalida;
	private JTextField textCuit;
	private JTable tablePasajero;
	private FacturarController controller;

	public FacturarGUI() {
		
		this.setSize(700, 350);
		this.setTitle("Facturar");
		getContentPane().setLayout(null);
		
		JPanel checkOut = new JPanel();
		checkOut.setBounds(10, 20, 268, 136);
		getContentPane().add(checkOut);
		checkOut.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Check Out", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		checkOut.setLayout(null);
		
		JLabel lblNumHabitacion = new JLabel("Numero de habitacion");
		lblNumHabitacion.setBounds(10, 28, 125, 20);
		checkOut.add(lblNumHabitacion);
		
		textNumHabitacion = new JTextField();
		textNumHabitacion.setBounds(162, 29, 96, 20);
		checkOut.add(textNumHabitacion);
		textNumHabitacion.setColumns(10);
		
		JLabel lblHoraDeSalida = new JLabel("Hora de salida");
		lblHoraDeSalida.setBounds(10, 58, 125, 20);
		checkOut.add(lblHoraDeSalida);
		
		textHoraSalida = new JTextField();
		textHoraSalida.setColumns(10);
		textHoraSalida.setBounds(162, 59, 96, 20);
		checkOut.add(textHoraSalida);
		
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
			
		});
		
		JPanel panelFacturaTercero = new JPanel();
		panelFacturaTercero.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Facturar a Tercero", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		panelFacturaTercero.setBounds(10, 166, 268, 80);
		getContentPane().add(panelFacturaTercero);
		panelFacturaTercero.setLayout(null);
		
		JLabel lblcuit = new JLabel("CUIT");
		lblcuit.setBounds(10, 22, 90, 20);
		panelFacturaTercero.add(lblcuit);
		
		textCuit = new JTextField();
		textCuit.setBounds(103, 23, 145, 20);
		panelFacturaTercero.add(textCuit);
		textCuit.setColumns(10);
		
		JCheckBox chckbxFacturaTercero = new JCheckBox("Facturar a tercero");
		chckbxFacturaTercero.setBounds(50, 48, 145, 21);
		panelFacturaTercero.add(chckbxFacturaTercero);
		
		JPanel panelOcupanteHabitacion = new JPanel();
		panelOcupanteHabitacion.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ocupantes de la Habitacion", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		panelOcupanteHabitacion.setBounds(288, 20, 388, 226);
		getContentPane().add(panelOcupanteHabitacion);
		panelOcupanteHabitacion.setLayout(null);
		
		tablePasajero = new JTable();
		tablePasajero.setBounds(10, 20, 368, 196);
		panelOcupanteHabitacion.add(tablePasajero);
		
		JButton btnFacturar = new JButton("Facturar");
		btnFacturar.setForeground(Color.WHITE);
		btnFacturar.setBackground(new Color(0, 128, 0));
		btnFacturar.setBounds(580, 256, 96, 20);
		getContentPane().add(btnFacturar);
	}
	
	//Getters and setters for labels
	public JTextField gettextNumHabitacion() {
		return textNumHabitacion;
	}
	public void setNumHabitacion(JTextField textNumHabitacion) {
		this.textNumHabitacion = textNumHabitacion;
	}
	public JTextField gettextHoraSalida() {
		return textHoraSalida;
	}
	public void settextHoraSalida(JTextField textHoraSalida) {
		this.textHoraSalida = textHoraSalida;
	}
	public JTextField getTextCuit() {
		return textCuit;
	}
	public void setTextCuit(JTextField textCuit) {
		this.textCuit = textCuit;
	}
	public JTable getTablePasajero() {
		return tablePasajero;
	}
	public void setTablePasajero(JTable tablePasajero) {
		this.tablePasajero = tablePasajero;
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
