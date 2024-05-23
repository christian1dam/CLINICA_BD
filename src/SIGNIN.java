import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SIGNIN extends JFrame{
    private JPanel SIGN_IN_PANEL;
    private JPanel HEADER_PANEL;
    private JLabel CLINICA_LABEL;
    private JPanel MAIN_PANEL;
    private JLabel NombreLabel;
    private JLabel ContrasenyaLabel;
    private JPasswordField getContrasenyaTxt;
    private JTextField getNombreTxt;
    private JButton entrarButton;


    public SIGNIN() {
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = getNombreTxt.getText();
                int idUsuario;
                String password = getContrasenyaTxt.getText();
                if(DATA_MANAGER.existsUser(nombre, password)){
                    idUsuario = DATA_MANAGER.getUserID(nombre, password);
                    MAIN_INTERFACE_CLINICA pacientesPanel = new MAIN_INTERFACE_CLINICA();
                    pacientesPanel.setContentPane(new MAIN_INTERFACE_CLINICA().getMainJPanel());
                    pacientesPanel.setVisible(true);
                    pacientesPanel.pack();
                    pacientesPanel.setPatient(idUsuario);
                    SwingUtilities.getWindowAncestor((JComponent) e.getSource()).dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame singInWindow = new JFrame("sign in window");
        singInWindow.setContentPane(new SIGNIN().SIGN_IN_PANEL);
        singInWindow.setMaximumSize(new Dimension(500, 500));
        singInWindow.setVisible(true);
        singInWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        singInWindow.pack();
    }
}
