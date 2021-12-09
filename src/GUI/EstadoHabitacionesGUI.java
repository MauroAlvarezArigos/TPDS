package GUI;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import Controller.HabitacionController;
import DTO.HabitacionDTO;
import utils.Converter;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class EstadoHabitacionesGUI extends JFrame {
	private HabitacionController controller;
	private Converter converter = new Converter();
	private List<JTable> LTables = new ArrayList<>();
	
	public EstadoHabitacionesGUI(Date desde, Date hasta, List<HabitacionDTO> Lhab, HabitacionController uncontroller) {

		controller = uncontroller;

		this.setBounds(100, 100, 676, 500);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout());
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane();

		List<String> LTipos = controller.getAllTiposHabDisponibles(Lhab);

		//......Generar Tabla
		for(String tipo : LTipos) {

			ArrayList<String> DateArray = generarFechas(desde, hasta);
			Object[] DateData = DateArray.toArray();

			DefaultTableModel model = new DefaultTableModel();

			List<HabitacionDTO> SubLHab = new ArrayList<>();
			for(HabitacionDTO h: Lhab){
				if(h.getTipo().equals(tipo)) {
					SubLHab.add(h);
				}
			}

			JTable table = new JTable(model){
				@Override
				public Class getColumnClass(int column){
					if(column < 1){
						return String.class;
					}else{
						return Boolean.class;
					}
				}
			};
			model.addColumn("Numero de habitacion", DateData);


			//Generar Lista de Bools para LosCheckboxes y Lista de estados
			//.......Start
			ArrayList<ArrayList<String>> EstadoArray = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<Boolean>> BoolArray = new ArrayList<ArrayList<Boolean>>();

			int size = SubLHab.size();
			for (int c = 0; c < size; c++) {
					EstadoArray.add(new ArrayList<>());
					BoolArray.add(new ArrayList<>());
					for (int x = 0; x < DateArray.size(); x++) {
						EstadoArray.get(c).add(controller.getEstadoHabitacionFecha(converter.convertStrtoLocalDate(DateArray.get(x)), SubLHab.get(c)));
						BoolArray.get(c).add(false);
					}
					Object[] estadoColumna = BoolArray.get(c).toArray();
					model.addColumn(SubLHab.get(c).getNumero(), estadoColumna);
			}
			//.......End

			//Setear los CellRenderers para los distintos tipos de datos
			//.......Start
			table.setDefaultRenderer(String.class, new DefaultTableCellRenderer());
			table.setDefaultRenderer(Boolean.class, new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
					if(value instanceof Boolean){
						if(EstadoArray.get(column-1).get(row).equals("Fuera de Servicio") || EstadoArray.get(column-1).get(row).equals("Ocupado")){
							JCheckBox c = new JCheckBox("", false);
							c.setBackground(controller.GetColor(EstadoArray.get(column-1).get(row)));
							c.setEnabled(false);
							c.setHorizontalAlignment(SwingConstants.CENTER);
							return c;
						}else {
							JCheckBox c = new JCheckBox("", value.equals(true));
							c.setBackground(controller.GetColor(EstadoArray.get(column-1).get(row)));
							c.setHorizontalAlignment(SwingConstants.CENTER);
							return c;
						}
					}
					else return getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				}
			});
			//.......End

			//Setear tama�os y apariencia de cada columna
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			table.setColumnSelectionAllowed(false);
    		table.setRowSelectionAllowed(false);
			table.setCellSelectionEnabled(true);
			for (int c = 1; c < table.getColumnCount(); c++) {
				table.getColumnModel().getColumn(c).setPreferredWidth(50);
			}



			JScrollPane scrollPane = new JScrollPane(table);

			JPanel indEstandar = new JPanel();
			indEstandar.add(scrollPane);
			tabbedPane.addTab(tipo, indEstandar);

			//Add generated table to external table array
			LTables.add(table);
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