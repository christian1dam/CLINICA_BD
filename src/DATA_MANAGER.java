import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DATA_MANAGER {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Visita> visitas = new ArrayList<>();
    private static List<Paciente> pacientes = new ArrayList<>();

    public static List<Usuario> getUsers() throws SQLException, DatabaseConnectionException {
        if(DB_MANAGER.openConnectionToDatabase()){
            ResultSet resultSet = DB_MANAGER.selectAllFromUsuarios(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            if(resultSet != null){
                while (resultSet.next()){
                    int codigo = resultSet.getInt("codigo");
                    String nombre = resultSet.getString("nombre");
                    String password = resultSet.getString("contrasenya");
                    usuarios.add(new Usuario(codigo, nombre, password));
                }
                return usuarios;
            }
        } else{
            throw new DatabaseConnectionException("No se ha podido crear la conexión con la base de datos!");
        }
        return null;
    }


}

