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


    public static boolean existsUser(String nombre, String password) {
        if(DB_MANAGER.openConnectionToDatabase()){
            ResultSet rs = DB_MANAGER.exisitUser(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, nombre, password);
            try {
                if(rs != null && rs.next() && rs.getString("nombre").equals(nombre) && rs.getString("contrasenya").equals(password)){
                    return true;
                } else return false;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public static Paciente getPatientByID(int idUsuario) {
        if(DB_MANAGER.openConnectionToDatabase()){
            ResultSet rs = DB_MANAGER.getPatienByID(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, idUsuario);
            try {
                if(rs != null && rs.next()){
                    return new Paciente(rs.getInt("CODIGO"), rs.getString("NOMBRE"), rs.getString("DIRECCION"), rs.getString("CIUDAD"), rs.getString("TELEFONO"), rs.getInt("DIABETICO"), rs.getDate("FECHA_NACIMIENTO").toLocalDate(), rs.getInt("TURNO"), rs.getInt("ID_USUARIO"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static Integer getUserID(String nombre, String password) {
        if(DB_MANAGER.openConnectionToDatabase()){
            ResultSet rs = DB_MANAGER.getUserID(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, nombre, password);
            try {
                if (rs != null && rs.next()){
                    return rs.getInt("CODIGO");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                return null;
            }
        }
        //conexion no establecida
        return -1;
    }
}

