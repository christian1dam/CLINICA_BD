import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MAIN_INTERFACE_CLINICA extends JFrame {
    private static List<Paciente> pacientes = new ArrayList<>();
    private JPanel CLINICA_MAIN_PANEL;
    private JLabel CLINICA_LABEL;
    private JPanel HEADER_PANEL;
    private JPanel MAIN_PANEL;
    private JPanel FOOTER_PANEL;
    private JTable TablaPacientesJTABLE;
    private JButton editarMisDatosButton;
    private JButton verMisVisitasButton;
    private JButton EditarOtroPacienteButton;
    private JButton CrearPacienteButton;
    private JButton BorrarPacienteButton;
    private JButton VerOtrasVisitasButton;
    private JButton CrearVisitasButton;

    public  MAIN_INTERFACE_CLINICA(){
        cargarTabla();
        CrearPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame crearPaciente = new JFrame("Crear Paciente");
                crearPaciente.setContentPane(new CREAR_PACIENTE().getPanel());
                crearPaciente.setVisible(true);
                crearPaciente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                crearPaciente.pack();
            }
        });
    }

    private void cargarTabla(){
        String[] columnNames = {"CODIGO", "NOMBRE", "DIRECCION", "CIUDAD", "TELEFONO", "DIABETICO", "FECHA_NACIMIENTO", "TURNO"};
        pacientes = DATA_MANAGER.getPacientes();
        String[][] data = new String[pacientes.size()][columnNames.length];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = String.valueOf(pacientes.get(i).getCodigo());
            data[i][1] = String.valueOf(pacientes.get(i).getNombre());
            data[i][2] = String.valueOf(pacientes.get(i).getDireccion());
            data[i][3] = String.valueOf(pacientes.get(i).getCiudad());
            data[i][4] = String.valueOf(pacientes.get(i).getTelefono());
            data[i][5] = String.valueOf(pacientes.get(i).getDiabetico());
            data[i][6] = String.valueOf(pacientes.get(i).getFechaNacimiento());
            data[i][7] = String.valueOf(pacientes.get(i).getTurno());
        }
        TablaPacientesJTABLE.setModel(new DefaultTableModel(data, columnNames));
    }

    public static void main(String[] args) {
            JFrame pantallClinicaPrincipal = new JFrame("Clinica");
            pantallClinicaPrincipal.setContentPane(new MAIN_INTERFACE_CLINICA().CLINICA_MAIN_PANEL);
            pantallClinicaPrincipal.setVisible(true);
            pantallClinicaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pantallClinicaPrincipal.pack();
    }
}
