package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import modelosTabla.HabitacionesDisponiblesTableModel;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class EstadoHabitacionesGUI extends JFrame {

	private JTable table;
	
	public EstadoHabitacionesGUI(Date desde, Date hasta) {
		this.setBounds(100, 100, 676, 483);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout());
		setContentPane(contentPane);
		
		///Primera Tabla
		ArrayList<String> dataArray1 = fechas(desde, hasta);
		Object[] data1 = dataArray1.toArray();
		DefaultTableModel model1 = new DefaultTableModel();
		model1.addColumn("  ", data1);
		JTable table1 = new JTable(model1);
		JScrollPane scrollPane1 = new JScrollPane(table1);
		///
		ArrayList<String> dataArray2 = fechas(desde, hasta);
		Object[] data2 = dataArray2.toArray();
		DefaultTableModel model2 = new DefaultTableModel();
		model2.addColumn("  ", data2);
		JTable table2 = new JTable(model2);
		JScrollPane scrollPane2 = new JScrollPane(table2);
		
		///TabSheets
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel indEstandar = new JPanel();
		indEstandar.add(scrollPane1);
		tabbedPane.addTab("Individual Estandar", indEstandar);
		
		JPanel dobEstandar = new JPanel();
		dobEstandar.add(scrollPane2);
		tabbedPane.addTab("Doble Estandar", dobEstandar);
		
		JPanel dobSuperior = new JPanel();
		tabbedPane.addTab("Doble Superior", dobSuperior);
		
		JPanel supFamily = new JPanel();
		tabbedPane.addTab("Superior Family", supFamily);
		
		JPanel suiDoble = new JPanel();
		tabbedPane.addTab("Suite Doble", suiDoble);		
		
		
		contentPane.add(tabbedPane);
		
		
		SwingUtilities.updateComponentTreeUI(this);

	}
	
	private ArrayList<String> fechas(Date desde, Date hasta){
		ArrayList<String> list = new ArrayList<String>();
		list.add("N° de habitacion");
		
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    	String date1 = format1.format(desde); 
    	String date2 = format1.format(hasta); 
		
    	list.add("Ingreso: "+date1);
		Date date = new Date(desde.getTime() + (1000 * 60 * 60 * 24));
		
		while(hasta.getTime() > date.getTime()) {
			String dateS = format1.format(date); 
			list.add(dateS);
			Date sig = new Date(date.getTime() + (1000 * 60 * 60 * 24));
			date = sig;
		}
		
		list.add("Egreso: "+date2);
		
		return list;
	}

}
