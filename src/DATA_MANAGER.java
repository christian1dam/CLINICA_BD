import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DATA_MANAGER { ;
    private static List<Visita> visitas = new ArrayList<>();
    private static List<Paciente> pacientes = new ArrayList<>();


    public static List<Paciente> getPacientes() {
        if(DB_MANAGER.openConnectionToDatabase()){
            ResultSet rs = DB_MANAGER.selectAllFromPacientes();
            if(rs!=null){
                try {
                    while (rs.next()){
                        pacientes.add(new Paciente(rs.getInt("CODIGO"), rs.getString("NOMBRE"), rs.getString("DIRECCION"), rs.getString("CIUDAD"), rs.getString("TELEFONO"), rs.getInt("DIABETICO"), rs.getDate("FECHA_NACIMIENTO").toLocalDate(), rs.getInt("TURNO")));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            return pacientes;
        }
        return null;
    }


    public static void addPaciente(Paciente paciente) {
        if(DB_MANAGER.openConnectionToDatabase()){
            DB_MANAGER.crearPaciente(paciente);
        }
    }
}

