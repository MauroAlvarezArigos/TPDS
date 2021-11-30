package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import modelosTabla.HabitacionesDisponiblesTableModel;

import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JButton;

public class EstadoHabitacionesGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstadoHabitacionesGUI frame = new EstadoHabitacionesGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EstadoHabitacionesGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnIE = new JButton("Individual Estandar");
		menuBar.add(btnIE);
		
		JButton btnDE = new JButton("Doble Estandar");
		menuBar.add(btnDE);
		
		JButton btnDS = new JButton("Doble Superior");
		menuBar.add(btnDS);
		
		JButton btnSF = new JButton("Superior Family");
		menuBar.add(btnSF);
		
		JButton btnSD = new JButton("Suite Doble");
		menuBar.add(btnSD);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		HabitacionesDisponiblesTableModel modelo = new HabitacionesDisponiblesTableModel();
		table = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(table);
		
		contentPane.add(table, BorderLayout.CENTER);
	}

}
