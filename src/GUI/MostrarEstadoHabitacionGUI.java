package GUI;

import java.awt.Color;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Controller.HabitacionController;
import modelosTabla.DateLabelFormatter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MostrarEstadoHabitacionGUI extends JFrame{
	private HabitacionController controller;
	private JDatePickerImpl datePickerDesde;
	private JDatePickerImpl datePickerHasta;

	public JDatePickerImpl getDatePickerDesde() {
		return datePickerDesde;
	}

	public void setDatePickerDesde(JDatePickerImpl datePickerDesde) {
		this.datePickerDesde = datePickerDesde;
	}

	public JDatePickerImpl getDatePickerHasta() {
		return datePickerHasta;
	}

	public void setDatePickerHasta(JDatePickerImpl datePickerHasta) {
		this.datePickerHasta = datePickerHasta;
	}

	public MostrarEstadoHabitacionGUI() {
		controller = new HabitacionController(this);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		this.setSize(600, 300);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos de Habitaciones", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(73, 36, 457, 155);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDesde = new JLabel("Desde fecha");
		lblDesde.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesde.setBounds(10, 35, 86, 13);
		panel.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta fecha");
		lblHasta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHasta.setBounds(17, 70, 79, 13);
		panel.add(lblHasta);
		
		JLabel lblAsterisco = new JLabel("(*)");
		lblAsterisco.setForeground(Color.RED);
		lblAsterisco.setBounds(106, 35, 29, 13);
		panel.add(lblAsterisco);
		
		JLabel lblAsterisco1 = new JLabel("(*)");
		lblAsterisco1.setForeground(Color.RED);
		lblAsterisco1.setBounds(106, 70, 29, 13);
		panel.add(lblAsterisco1);
		
		//Adding DatePicker DESDE
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Hoy");
		p.put("text.month", "Mes");
		p.put("text.year", "Ano");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePickerDesde = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePickerDesde.setBounds(140, 30, 110, 30);
		panel.add(datePickerDesde);
		
		//Adding DatePicker HASTA
		UtilDateModel model2 = new UtilDateModel();
		Properties p2 = new Properties();
		p2.put("text.today", "Hoy");
		p2.put("text.month", "Mes");
		p2.put("text.year", "Ano");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
		datePickerHasta = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		datePickerHasta.setBounds(140, 65, 110, 30);
		panel.add(datePickerHasta);
		
		JLabel lblCObligatorio = new JLabel("(*) Campos Obligatorios");
		lblCObligatorio.setForeground(Color.RED);
		lblCObligatorio.setBounds(35, 120, 150, 13);
		panel.add(lblCObligatorio);
		
		//Buttons
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setBackground(new Color(50, 205, 50));
		btnAceptar.setBounds(444, 210, 85, 21);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(255, 0, 0));
		btnCancelar.setBounds(353, 210, 85, 21);
		getContentPane().add(btnCancelar);
		
		btnCancelar.addActionListener(e->dispose());
		
		btnAceptar.addActionListener(e->{
			controller.verficarFechas();
		});
		
	}
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}
	
	//Getters and Setters
	public void setDesde(JDatePickerImpl datePicker) {this.datePickerDesde = datePicker;}
	public JDatePickerImpl getDesde() {return this.datePickerDesde;}
	public void setHasta(JDatePickerImpl datePicker) {this.datePickerHasta = datePicker;}
	public JDatePickerImpl getHasta() {return this.datePickerHasta;}

}
