import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class main {
    private static List<Usuario> usuarios = new ArrayList<>();
    public static void main(String[] args) {
        DB_MANAGER.loadDriver();
        try {
            usuarios = DATA_MANAGER.getUsers();
        } catch (SQLException e) {
           e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (DatabaseConnectionException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
