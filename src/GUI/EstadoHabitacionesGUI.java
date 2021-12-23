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
	private JButton btnAceptar;

	//Getters
	public List<JTable> getLTables() {
		return LTables;
	}
	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public EstadoHabitacionesGUI(Date desde, Date hasta, List<HabitacionDTO> Lhab, List<String> LTiposSelect, HabitacionController uncontroller) {

		controller = uncontroller;

		this.setBounds(100, 100, 676, 600);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
		setContentPane(contentPane);


		JTabbedPane tabbedPane = new JTabbedPane();

		List<String> LTipos = controller.getAllTiposHabDisponibles(Lhab);
		List<String> LTiposFinal = new ArrayList<>();

		for(String tipo : LTipos){
			for(String tipoSelected : LTiposSelect){
				if(tipoSelected.equals(tipo)){
					LTiposFinal.add(tipo);
				}
			}
		}
		//......Generar Tabla
		for(String tipo : LTiposFinal) {

			ArrayList<String> DateArray = generarFechas(desde, hasta);
			Object[] DateData = DateArray.toArray();

			DefaultTableModel model = new DefaultTableModel();

			List<HabitacionDTO> SubLHab = new ArrayList<>();
			for(HabitacionDTO h: Lhab){
				if(h.getTipo().equals(tipo)) {
					SubLHab.add(h);
				}
			}


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

			}

			JTable table = new JTable(model){
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@Override
				public Class getColumnClass(int column){
					if(column < 1){
						return String.class;
					}else{
						return Boolean.class;
					}
				}
				@Override
				public boolean isCellSelected(int row, int column){return column != 0;}

				@Override
				public boolean isCellEditable (int row, int column){
					if (column > 0) {
						return !(EstadoArray.get(column-1).get(row).equals("Fuera de Servicio") || EstadoArray.get(column-1).get(row).equals("Ocupado"));
					}else{
						return false;
					}
				}
			};
			table.getTableHeader().setReorderingAllowed(false);

			model.addColumn("Numero de habitacion", DateData);


			for (int q = 0; q < size; q++){
				Object[] estadoColumna = BoolArray.get(q).toArray();
				model.addColumn(SubLHab.get(q).getNumero(), estadoColumna);
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
							c.setBackground(controller.getColor(EstadoArray.get(column-1).get(row)));
							c.setEnabled(false);
							c.setHorizontalAlignment(SwingConstants.CENTER);
							return c;
						}else {
							JCheckBox c = new JCheckBox("", value.equals(true));
							c.setBackground(controller.getColor(EstadoArray.get(column-1).get(row)));
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

		JPanel ButtonContainer = new JPanel();
		ButtonContainer.setLayout(new FlowLayout());

		JPanel Labels = new JPanel();
		Labels.setLayout(new FlowLayout());

		JPanel Button = new JPanel();
		Button.setLayout(new FlowLayout());

		JLabel lblLibre = new JLabel();
		lblLibre.setSize(20,20);
		lblLibre.setOpaque(true);
		lblLibre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblLibre.setText("Libre");
		lblLibre.setForeground(Color.BLACK);
		lblLibre.setBackground(controller.getDisponible());
		Labels.add(lblLibre);

		JLabel lblFueraDeServicio = new JLabel();
		lblFueraDeServicio.setSize(20,20);
		lblFueraDeServicio.setOpaque(true);
		lblFueraDeServicio.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblFueraDeServicio.setText("Fuera de Servicio");
		lblFueraDeServicio.setForeground(Color.BLACK);
		lblFueraDeServicio.setBackground(controller.getFueraDeServicio());
		Labels.add(lblFueraDeServicio);

		JLabel lblOcupado = new JLabel();
		lblOcupado.setSize(20,20);
		lblOcupado.setOpaque(true);
		lblOcupado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblOcupado.setText("Ocupado");
		lblOcupado.setForeground(Color.BLACK);
		lblOcupado.setBackground(controller.getOcupado());
		Labels.add(lblOcupado);

		JLabel lblReservado = new JLabel();
		lblReservado.setSize(20,20);
		lblReservado.setOpaque(true);
		lblReservado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblReservado.setText("Reservado");
		lblReservado.setForeground(Color.BLACK);
		lblReservado.setBackground(controller.getReservado());
		Labels.add(lblReservado);


		//------------------------------------------------------------

		JButton btnCancelar = new JButton();
		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(0, 0, 160, 80);
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(205, 50, 50));
		Button.add(btnCancelar);
		btnCancelar.addActionListener(e ->{
			dispose();
		});

		btnAceptar = new JButton();
		btnAceptar.setText("Aceptar");
		btnAceptar.setBounds(0, 0, 160, 80);
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setBackground(new Color(50, 205, 50));
		Button.add(btnAceptar);
		/*btnAceptar.addActionListener(e ->{
			controller.getSelections(LTables);
			controller.getSelectedHab(LTables);
		});*/

		ButtonContainer.add(Button, BorderLayout.EAST);

		contentPane.add(Labels);
		contentPane.add(ButtonContainer);

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