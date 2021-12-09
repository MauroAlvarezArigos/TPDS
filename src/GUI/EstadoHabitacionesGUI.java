package GUI;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import Controller.HabitacionController;
import DTO.HabitacionDTO;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

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

		JTabbedPane tabbedPane = new JTabbedPane();
		
		///Generar Tabla
		List<String> LTipos = controller.getAllTiposHabDisponibles(Lhab);

		for(String tipo : LTipos) {

			ArrayList<String> dataArray1 = generarFechas(desde, hasta);
			Object[] data1 = dataArray1.toArray();
			DefaultTableModel model1 = new DefaultTableModel();

			List<HabitacionDTO> SubLHab = new ArrayList<>();
			for(HabitacionDTO h: Lhab){
				if(h.getTipo().equals(tipo)) {
					SubLHab.add(h);
				}
			}

			JTable table1 = new JTable(model1){
				@Override
				public Class getColumnClass(int column){
					if(column < 1){
						return String.class;
					}else{
						return Boolean.class;
					}
				}
			};

			model1.addColumn("Numero de habitacion", data1);

			ArrayList<ArrayList<String>> EstadoArray = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<Boolean>> BoolArray = new ArrayList<ArrayList<Boolean>>();

			int size = SubLHab.size();
			for (int c = 0; c < size; c++) {
					EstadoArray.add(new ArrayList<>());
					BoolArray.add(new ArrayList<>());
					for (int x = 0; x < dataArray1.size(); x++) {
						EstadoArray.get(c).add(controller.getEstadoHabitacionFecha(controller.convertStringtosqlDate(dataArray1.get(x)), SubLHab.get(c)));
						BoolArray.get(c).add(false);
					}
				/*if(Lhab.get(c).getTipo().equals(tipo)) {
					//Object[] estadoColumna = EstadoArray.get(c).toArray();*/
					Object[] estadoColumna = BoolArray.get(c).toArray();
					model1.addColumn(Lhab.get(c).getNumero(), estadoColumna);
					//}
			}

			table1.setDefaultRenderer(String.class, new DefaultTableCellRenderer());
			table1.setDefaultRenderer(Boolean.class, new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
					if(value instanceof Boolean){
						JCheckBox c = new JCheckBox("", value.equals(true));
						c.setBackground(controller.GetColor(EstadoArray.get(column-1).get(row)));
						c.setHorizontalAlignment(SwingConstants.CENTER);
						return c;
					}
					else return getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				}
			});

			table1.getColumnModel().getColumn(0).setPreferredWidth(150);
			table1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			table1.setColumnSelectionAllowed(false);
    		table1.setRowSelectionAllowed(false);
			table1.setCellSelectionEnabled(true);
			for (int c = 1; c < table1.getColumnCount(); c++) {
				table1.getColumnModel().getColumn(c).setPreferredWidth(50);
			}



			JScrollPane scrollPane1 = new JScrollPane(table1);

			JPanel indEstandar = new JPanel();
			indEstandar.add(scrollPane1);
			tabbedPane.addTab(tipo, indEstandar);
		}

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