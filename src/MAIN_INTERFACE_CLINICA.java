import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MAIN_INTERFACE_CLINICA extends JFrame {
    private static final List<Usuario> usuarios = new ArrayList<>();
    private JPanel CLINICA_MAIN_PANEL;
    private JLabel CLINICA_LABEL;
    private JPanel HEADER_PANEL;
    private JPanel MAIN_PANEL;
    private JPanel FOOTER_PANEL;
    private JLabel nombrePacienteField;
    private JLabel direccionPacienteField;
    private JLabel ciudadPacietneField;
    private JLabel telefonoPacienteField;
    private JLabel diabeticoPacienteField;
    private JLabel fechaNacPacienteField;
    private JLabel turnoPacienteField;
    private JTable TablaPacientesJTABLE;
    private JButton editarMisDatosButton;
    private JButton verMisVisitasButton;
    private JButton EditarOtroPacienteButton;
    private JButton CrearPacienteButton;
    private JButton BorrarPacienteButton;
    private JButton VerOtrasVisitasButton;
    private JButton CrearVisitasButton;
    private JTextField setNombre;
    private JTextField setDireccion;
    private JTextField setCiudad;
    private JTextField setTelefono;
    private JTextField setDiabetico;
    private JTextField setFechaNac;
    private JTextField setTurno;

    public  MAIN_INTERFACE_CLINICA(){
    }

    public MAIN_INTERFACE_CLINICA(int idUsuario){
        setPatient(idUsuario);
    }



    public void setPatient(int IdUsuario){
        Paciente currentPatient = DATA_MANAGER.getPatientByID(IdUsuario);
        assert currentPatient != null;
        setNombre.setText("Nombre: " + currentPatient.getNombre());
        setDireccion.setText("Dirección: " + currentPatient.getDireccion());
        setCiudad.setText("Ciudad: " + currentPatient.getCiudad());
        setTelefono.setText("Teléfono: " + currentPatient.getTelefono());
        setFechaNac.setText("Fecha de nacimiento: " + currentPatient.getFechaNacimiento());
        setTurno.setText("Turno: " + currentPatient.getTurno());
    }

    public JPanel getMainJPanel(){
        return this.CLINICA_MAIN_PANEL;
    }
}
