package GUI;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Controller.DarAltaController;
import Controller.PasajeroController;
import DTO.PasajeroBusquedaDTO;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class GestionPasajeroBusquedaGUI extends JFrame implements ActionListener {

	private JPanel resultados;
	private ButtonGroup bg;
	private List<PasajeroBusquedaDTO> lista;
	@SuppressWarnings("unused")
	private PasajeroController controller;
	private JLabel title;
	private JScrollPane scrollPane;
	private JRadioButton rbPasajero;
	private JTextField textSearch;
	
	public GestionPasajeroBusquedaGUI(List<PasajeroBusquedaDTO> lista, String tituloActualizado) {
		
		this.lista = lista;
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		this.setSize(700, 300);
		this.setTitle("Gestionar Pasajero");

		title = new JLabel(tituloActualizado);
		title.setBounds(10, 10, 666, 13);
		getContentPane().add(title);
		
		bg = new ButtonGroup();
		
		resultados = new JPanel();
		resultados.setLayout(new GridLayout(0,1));
		
		setJPanelPasajero(lista);
		
		scrollPane = new JScrollPane(resultados);
		scrollPane.setBounds(10, 62, 666, 163);
		getContentPane().add(scrollPane);
		
		JLabel warning = new JLabel("No hay concidencias en la busqueda");
		warning.setForeground(Color.RED);
		warning.setVisible(false);
		warning.setBounds(265, 33, 218, 19);
		getContentPane().add(warning);
	
		JButton cancelar = new JButton("Cancelar");
		cancelar.setForeground(Color.WHITE);
		cancelar.setBackground(Color.RED);
		cancelar.setBounds(472, 228, 90, 25);
		getContentPane().add(cancelar);
		
		JButton next = new JButton("Siguiente");
		next.setForeground(Color.WHITE);
		next.setBackground(new Color(0, 128, 0));
		next.setBounds(576, 228, 100, 25);
		getContentPane().add(next);
		
		textSearch = new JTextField();
		textSearch.setBounds(10, 33, 150, 19);
		getContentPane().add(textSearch);
		textSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setBounds(170, 33, 85, 21);
		getContentPane().add(btnSearch);
		
		btnSearch.addActionListener(e->{
			String resultSearch = textSearch.getText();
			List<PasajeroBusquedaDTO> newLista = new ArrayList<PasajeroBusquedaDTO>();
			resultados.removeAll();
			scrollPane.removeAll();
			for(int i=0; i<lista.size(); i++) {
				if((lista.get(i).getApellido()).equals(resultSearch) || (lista.get(i).getNombre()).equals(resultSearch) || (lista.get(i).getNdoc()).equals(resultSearch)) {
					newLista.add(lista.get(i));
				}
			}
			if(newLista.size() == 0) {
				warning.setVisible(true);
			}else {
				warning.setVisible(false);
				setJPanelPasajero(newLista);
				scrollPane.add(resultados);
			}
			resultados.updateUI();
			scrollPane.updateUI();
		});
		
		cancelar.addActionListener(e->dispose());
		
		next.addActionListener(e -> actionPerformed(e));
		
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void update() {
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void setJPanelPasajero(List<PasajeroBusquedaDTO> lista) {
		String consulta = "";
		for(int i=0; i<lista.size(); i++) {
			consulta += lista.get(i).getApellido() + " ";
			consulta += lista.get(i).getNombre() + " ";
			consulta += lista.get(i).getTipodoc() + " ";
			consulta += lista.get(i).getNdoc() + " ";
			
			rbPasajero = new JRadioButton(consulta);
			rbPasajero.setActionCommand(Integer.toString(i));
			bg.add(rbPasajero);
			resultados.add(rbPasajero);
			consulta = "";
		}
	}
	
	public void setController(PasajeroController unController) {
		controller = unController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Siguiente")) {
			if ((bg.getSelection()) == null) {
				try {
					AltaPasajeroGUI altaPasajero = new AltaPasajeroGUI();
					altaPasajero.setLocationRelativeTo(null);
					DarAltaController controllerAlta = new DarAltaController(altaPasajero);
					controllerAlta.DarAltaPasajero();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				@SuppressWarnings("unused")
				PasajeroBusquedaDTO unPasajeroBusquedaDTO = lista.get((Integer.parseInt(bg.getSelection().getActionCommand())));
				//todo modificarPasajeroGUI();
			}
		}
	}
}