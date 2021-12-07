package GUI;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import Controller.HabitacionController;
import DTO.HabitacionDTO;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class EstadoHabitacionesGUI extends JFrame {
	private HabitacionController controller;
	private JTable table;
	
	public EstadoHabitacionesGUI(Date desde, Date hasta, List<HabitacionDTO> Lhab, HabitacionController uncontroller) {

		controller = uncontroller;

		this.setBounds(100, 100, 676, 483);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout());
		setContentPane(contentPane);
		
		///Primera Tabla
		ArrayList<String> dataArray1 = generarFechas(desde, hasta);
		Object[] data1 = dataArray1.toArray();
		DefaultTableModel model1 = new DefaultTableModel();

		model1.addColumn("Numero de habitacion", data1);

		ArrayList<ArrayList<String>> EstadoArray = new ArrayList<ArrayList<String>>();

		int size = Lhab.size();
		for(int c = 0; c < size; c++){
			EstadoArray.add(new ArrayList<>());
			for(int x = 0; x < dataArray1.size(); x++){
				EstadoArray.get(c).add(controller.getEstadoHabitacionFecha(controller.convertStringtosqlDate(dataArray1.get(x)),Lhab.get(c)));
			}
			Object[] estadoColumna = EstadoArray.get(c).toArray();
			model1.addColumn(Lhab.get(c).getNumero(), estadoColumna);
		}

		Object[] estado = EstadoArray.toArray();
		JTable table1 = new JTable(model1);
		table1.getColumnModel().getColumn(0).setPreferredWidth(150);
		for (int c = 1; c < table1.getColumnCount(); c++){
			table1.getColumnModel().getColumn(c).setPreferredWidth(50);
		}
		JScrollPane scrollPane1 = new JScrollPane(table1);
		///
		ArrayList<String> dataArray2 = generarFechas(desde, hasta);
		Object[] data2 = dataArray2.toArray();
		DefaultTableModel model2 = new DefaultTableModel();
		model2.addColumn("  ", data2);
		JTable table2 = new JTable(model2);
		JScrollPane scrollPane2 = new JScrollPane(table2);


		//todo volver procedural en base a los datos de la DB
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
	
	private ArrayList<String> generarFechas(Date desde, Date hasta){
		ArrayList<String> list = new ArrayList<String>();

		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    	String date1 = format1.format(desde); 
    	String date2 = format1.format(hasta); 
		
    	list.add(date1);
		Date date = new Date(desde.getTime() + (1000 * 60 * 60 * 24));
		
		while(hasta.getTime() > date.getTime()) {
			String dateS = format1.format(date); 
			list.add(dateS);
			Date sig = new Date(date.getTime() + (1000 * 60 * 60 * 24));
			date = sig;
		}
		
		list.add(date2);
		
		return list;
	}

}
