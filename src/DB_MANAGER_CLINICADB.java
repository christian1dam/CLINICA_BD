import com.mysql.cj.protocol.Resultset;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.awt.image.AbstractMultiResolutionImage;
import java.sql.*;

public class DB_MANAGER_CLINICADB {
    private static Connection connection = null;
    //CONSTATNTES PARA LA CONEXION
    private static final String DBHOST =  "localhost";
    private static final String PORT = "3306";
    private static final String DBNAME = "clinica_db";
    private static final String SERVERTIMEZONE = "serverTimezone=UTC";
    private static final String URL ="jdbc:mysql://" + DBHOST + ":" + PORT + "/" + DBNAME + "?" + SERVERTIMEZONE;
    //CONSTANTES APRA EL USUARIO
    private static final String USUARIODB = "root";
    private static final String PASSWORD = ""; //NO HAY CONTRASEÑA EN ESTE CASO
    //CONSTANTES PARA CHECK SI SE AH PODIDO CONECTAR CON LA BASE DE DATOS
    private static final String DB_MYSQL_CONNECTION_OK = "SUCCESSFULLY CONNECTED TO " + DBNAME + " DATABASE";
    private static final String DB_MYSQL_CONNECTION_NOT_OK = "ERROR! COULD NOT BEEN CONNECTED TO" + DBNAME + " DATABASE";
    //CONSTANTES PARA LA TABLA PACIENTES
    private static final String DBT_PACIENTES = "pacientes";
    private static final String DBT_PACIENTES_C_CODIGO = "CODIGO";
    private static final String DBT_PACIENTES_C_CIUDAD = "CIUDAD";
    private static final String DBT_PACIENTES_C_DIABETICO = "DIABETICO";
    private static final String DBT_PACIENTES_C_DIRECCION = "DIRECCION";
    private static final String DBT_PACIENTES_C_FECHA_NACIMIENTO = "FECHA_NACIMIENTO";
    private static final String DBT_PACIENTES_C_NOMBRE = "NOMBRE";
    private static final String DBT_PACIENTES_C_TELEFONO = "TELEFONO";
    private static final String DBT_PACIENTES_C_TURNO = "TURNO";
    private static final String BDT_PACIENTES_SELECT_ALL_QUERY = "SELECT * FROM " + DBT_PACIENTES;
    //CONSTANTES PARA LA TABLA VISITAS
    private static final String DBT_VISITAS = "visitas";
    private static final String DBT_VISITAS_C_CODIGO = "CODIGO";
    private static final String DBT_VISITAS_C_ENFERMEDAD = "ENFERMEDAD";
    private static final String DBT_VISITAS_C_FECHA_VISITA = "FECHA_VISITA";
    private static final String DBT_VISITAS_C_ID_PACIENTE = "ID_PACIENTE";
    private static final String DBT_VISITAS_C_IMPORTE = "IMPORTE";
    private static final String DBT_VISITAS_C_PORCENTAJE_PAGO = "PORCENTAJE_PAGO";
    private static final String DBT_VISITAS_C_PROXIMA_VISITA = "PROXIMA_VISITA";
    private static final String DBT_VISITAS_SELECT_ALL_QUERY = "SELECT * FROM " + DBT_VISITAS;

    public static boolean loadDriver(){
        try {
            System.out.println("Cargando Driver DBJC...");
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("DRIVER JDBC SUCCESSFULLY LOADED!");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean connect(){
        try {
            System.out.println("Connecting to the database...");
            connection = DriverManager.getConnection(URL, USUARIODB, PASSWORD);
            System.out.println("Successfully connect to the database " + DBNAME + " !");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void closeConnectioToDatabase(){
        try {
            System.out.println("Closing your connection to the database " + DBNAME);
            connection.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet selectAllFromPacientes(){
        return selectAllFromPacientes(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public static ResultSet selectAllFromPacientes(int resultSetType, int resultSetConcurrency)
    {
        try{
            Statement statement = connection.createStatement(resultSetType, resultSetConcurrency);
            ResultSet resultSet = statement.executeQuery(BDT_PACIENTES_SELECT_ALL_QUERY);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ResultSet selectAllFromVisitas(){
        return selectAllFromVisitas(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public static ResultSet selectAllFromVisitas(int resultSetType, int resultSetConcurrency)
    {
        try{
            Statement statement = connection.createStatement(resultSetType, resultSetConcurrency);
            ResultSet resultSet = statement.executeQuery(DBT_VISITAS_SELECT_ALL_QUERY);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    
}
