package GUI;

import Controller.OcuparController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class OcupacionSolicitadaGUI extends JFrame {

    private OcuparController controller;
    private JPanel panelOcupacionSolicitada;
    private JPanel contentPane;

    public OcupacionSolicitadaGUI(OcuparController controller){
        this.controller = controller;
        this.mostrarLista();
    }

    private void mostrarLista(){
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        contentPane.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.setBounds(100, 100,676, 600);


        panelOcupacionSolicitada = new JPanel();
        panelOcupacionSolicitada.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelOcupacionSolicitada.setBounds(100,100,650,500);
        panelOcupacionSolicitada.setAlignmentY(Component.CENTER_ALIGNMENT);
        panelOcupacionSolicitada.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(100,100,100),2 ),
                "Ocupacion Solicitada"));
        panelOcupacionSolicitada.setLayout(new BoxLayout(panelOcupacionSolicitada,BoxLayout.Y_AXIS));
        setContentPane(contentPane);


        contentPane.add(panelOcupacionSolicitada);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setBackground(new Color(0,200,0));
        getContentPane().add(btnAceptar);

        btnAceptar.addActionListener(e -> {
            controller.opcionFinal();
        });
    }

    public void addPanelOcupacion(String nHab, String tipoHab, String ingreso, String egreso, String responsable, List<String> acompanantes){
        JPanel panelOcupacion = new JPanel();
        panelOcupacion.setAlignmentY(Component.CENTER_ALIGNMENT);
        panelOcupacion.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelOcupacion.setLayout(new BoxLayout(panelOcupacion,BoxLayout.Y_AXIS));

        JLabel lblNHabitacion = new JLabel("Habitacion: "+nHab, SwingConstants.CENTER);
        lblNHabitacion.setVisible(true);
        lblNHabitacion.setBounds(0,0,300,50);
        lblNHabitacion.setFont(new Font("Serif", Font.BOLD, 20));
        lblNHabitacion.setBackground(new Color(100,100,100));
        lblNHabitacion.setOpaque(true);
        lblNHabitacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelOcupacion.add(lblNHabitacion);

        JLabel lblTipoHab = new JLabel("Tipo de Habitacion: "+tipoHab);
        lblTipoHab.setVisible(true);
        lblTipoHab.setBounds(0,0,100,25);
        panelOcupacion.add(lblTipoHab);

        JLabel lblIngreso = new JLabel("Ingreso: "+ingreso);
        lblIngreso.setVisible(true);
        lblIngreso.setBounds(0,0,100,25);
        panelOcupacion.add(lblIngreso);

        JLabel lblEgreso = new JLabel("Egreso: "+egreso);
        lblEgreso.setVisible(true);
        lblEgreso.setBounds(0,0,100,25);
        panelOcupacion.add(lblEgreso);

        JLabel lblResponsable = new JLabel("Resonsable de la Habitacion: "+responsable);
        lblResponsable.setVisible(true);
        lblResponsable.setBounds(0,0,100,25);
        panelOcupacion.add(lblResponsable);

        JLabel lblAcompanante = new JLabel("Acompanante/s: ");
        lblAcompanante.setVisible(true);
        lblAcompanante.setBounds(0,0,100,25);
        panelOcupacion.add(lblAcompanante);

        for(String s : acompanantes){
            JLabel lblNAcompanante = new JLabel(s);
            lblNAcompanante.setVisible(true);
            lblNAcompanante.setBounds(0,0,100,25);
            panelOcupacion.add(lblNAcompanante);
        }

        panelOcupacionSolicitada.add(panelOcupacion);

    }

}
