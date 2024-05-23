import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CREAR_PACIENTE extends JFrame {
    private static List<Paciente> pacientes = new ArrayList<>();
    private JPanel CREAR_PACIENTE;
    private JTextField setNombre;
    private JTextField setDireccion;
    private JTextField setCiudad;
    private JTextField setTelefono;
    private JTextField setFechaNacimiento;
    private JTextField setTurno;
    private JButton CREARPACIENTE;

    public CREAR_PACIENTE(){
        CREARPACIENTE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pacientes = DATA_MANAGER.getPacientes();
                DATA_MANAGER.addPaciente(new Paciente(setNombre.getText(), setDireccion.getText(), setCiudad.getText(), setTelefono.getText(), Date.valueOf(setFechaNacimiento.getText()).toLocalDate(), Integer.valueOf(setTurno.getText())));
            }
        });
    }

    public JPanel getPanel() {
        return this.CREAR_PACIENTE;
    }
}
