package GUI;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Controller.DarAltaController;
import Controller.PasajeroController;
import DTO.PasajeroBusquedaDTO;
import DTO.PasajeroDTO;


@SuppressWarnings({ "serial", "unused" })
public class GestionPasajeroBusquedaGUI extends JFrame implements ActionListener {
	private ButtonGroup bg;
	private List<PasajeroBusquedaDTO> lista;
	private PasajeroController controller;

	public GestionPasajeroBusquedaGUI(List<PasajeroBusquedaDTO> lista, String t) {

		this.lista = lista;
		this.setLocationRelativeTo(null);
		this.setSize(700, 300);

		this.setLayout(new BorderLayout());

		JLabel title = new JLabel(t);

		this.add(title, BorderLayout.NORTH);

		//Filling search results
		JPanel results = new JPanel();
		results.setLayout(new GridLayout(0, 1));

		bg = new ButtonGroup();
		System.out.println("Cree bg");

		int tam = lista.size();
		String s = "";
		for (int i = 0; i < tam; i++) {
			s += lista.get(i).getApellido() + " ";
			s += lista.get(i).getNombre() + " ";
			s += lista.get(i).getTipodoc() + " ";
			s += lista.get(i).getNdoc() + " ";

			final JRadioButton rb = new JRadioButton(s);
			rb.setActionCommand(Integer.toString(i));
			bg.add(rb);
			results.add(rb);
			s = "";
		}

		JScrollPane sp = new JScrollPane(results);
		sp.setBounds(0, 0, 0, 0);
		sp.setPreferredSize(new Dimension(600, 600));
		System.out.println("Cree el JScrollPane");

		this.add(sp, BorderLayout.CENTER);
		System.out.println("Anadi JScrollPane al JFrame");

		//Buttons
		JPanel buttons = new JPanel();
		buttons.setBounds(0, 0, 0, 0);
		buttons.setLayout(new BorderLayout());
		JButton siguiente = new JButton("Siguiente");

		siguiente.addActionListener(e -> {
			actionPerformed(e);
//			try {
//				controller.DarAltaPasajero();
//			}catch (Exception e1) {
//				//System.out.println("Es en el try de gestion pasajero");
//				e1.printStackTrace();
//			}
		});

		buttons.add(siguiente, BorderLayout.LINE_END);
		this.add(buttons, BorderLayout.SOUTH);

		System.out.println("Cree y anadi botones");


		SwingUtilities.updateComponentTreeUI(this);

		System.out.println("update");

	}

	public void update() {
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void setTable(JTable tabla) {
	}

	public void setController(PasajeroController unController) {
		controller = unController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Siguiente")) {
			//			try {
//				controller.DarAltaPasajero();
//			}catch (Exception e1) {
//				//System.out.println("Es en el try de gestion pasajero");
//				e1.printStackTrace();
//			}
			if ((bg.getSelection()) == null) {
				try {
					AltaPasajeroGUI a = new AltaPasajeroGUI();
					DarAltaController controllerAlta = new DarAltaController(a);
					controllerAlta.DarAltaPasajero();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				System.out.println("Selected Radio Button: " + lista.get((Integer.parseInt(bg.getSelection().getActionCommand()))).toString());
				PasajeroBusquedaDTO unPasajeroBusquedaDTO = lista.get((Integer.parseInt(bg.getSelection().getActionCommand())));
				//todo modificarPasajeroGUI();
			}
		}
	}

}