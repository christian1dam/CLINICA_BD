import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.time.LocalDate;

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
    //CONSTANTES APRA LA TABLA USUARIOS
    private static final String DBT_USUARIOS = "usuarios";
    private static final String DBT_USUARIOS_C_NOMBRE = "NOMBRE";
    private static final String DBT_USUARIOS_C_CONTRASENYA = "CONTRASENYA";
    private static final String BDT_USUARIOS_SELECT_ALL_QUERY = "SELECT * FROM " + DBT_USUARIOS;
    //CONSTANTES PARA LA TABLA PACIENTES
    private static final String DBT_PACIENTES = "pacientes";
    private static final String DBT_PACIENTES_C_CODIGO = "CODIGO";
    private static final String DBT_PACIENTES_C_NOMBRE = "NOMBRE";
    private static final String DBT_PACIENTES_C_DIRECCION = "DIRECCION";
    private static final String DBT_PACIENTES_C_CIUDAD = "CIUDAD";
    private static final String DBT_PACIENTES_C_TELEFONO = "TELEFONO";
    private static final String DBT_PACIENTES_C_DIABETICO = "DIABETICO";
    private static final String DBT_PACIENTES_C_FECHA_NACIMIENTO = "FECHA_NACIMIENTO";
    private static final String DBT_PACIENTES_C_TURNO = "TURNO";
    private static final String DBT_PACIENTES_C_ID_USUARIO = "ID_USUARIO";
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

    //METODO PARA CARGAR EL DRIVER MYSQL-JDBC
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
    //METODO PARA CREAR LA CONEXION CON LA BASE DE DATOS
    public static boolean openConnectionToDatabase(){
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
    //METODO PARA CERRAR LA CONEXION CON LA BASE DE DATOS
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

    //METODO PARA EJECUTAR LA QUERY SELECT * FROM USUARIOS Y DEVOLVER EL RESULTADO
    public static ResultSet selectAllFromUsuarios(){
        return selectAllFromUsuarios(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public static ResultSet selectAllFromUsuarios(int resultSetType, int resultSetConcurrency){
        try{
            Statement statement = connection.createStatement(resultSetType, resultSetConcurrency);
            return statement.executeQuery(BDT_USUARIOS_SELECT_ALL_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    //METODO PARA EJECUTAR LA QUERY SELECT * FROM PACIENTES Y DEVOLVER EL RESULTADO.
    public static ResultSet selectAllFromPacientes(){
        return selectAllFromPacientes(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }
    //MISMO METODO PERO CON EL CONTENIDO
    public static ResultSet selectAllFromPacientes(int resultSetType, int resultSetConcurrency)
    {
        try{
            Statement statement = connection.createStatement(resultSetType, resultSetConcurrency);
            return statement.executeQuery(BDT_PACIENTES_SELECT_ALL_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }
    //METODO PARA EJECUTAR LA QUERY SELECT * FROM VISITAS Y DEVOLVER EL RESULTADO.
    public static ResultSet selectAllFromVisitas(){
        return selectAllFromVisitas(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }
    //MISMO METODO PERO CON EL CONTENIDO
    public static ResultSet selectAllFromVisitas(int resultSetType, int resultSetConcurrency)
    {
        try{
            Statement statement = connection.createStatement(resultSetType, resultSetConcurrency);
            return statement.executeQuery(DBT_VISITAS_SELECT_ALL_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    //METODO PARA DEVOLVER POR CONSOLA LOS DATOS DE LA BASE DE DATOS
    public static void printTablaPacientes(){
        try{
            ResultSet resultSet = selectAllFromPacientes(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            if(resultSet != null){
                while (resultSet.next()){
                    int codigoPaciente = resultSet.getInt(DBT_PACIENTES_C_CODIGO);
                    String nombrePaciente = resultSet.getString(DBT_PACIENTES_C_NOMBRE);
                    String direccionPaciente = resultSet.getString(DBT_PACIENTES_C_DIRECCION);
                    String ciudadPaciente = resultSet.getString(DBT_PACIENTES_C_CIUDAD);
                    String telefonoPaciente = resultSet.getString(DBT_PACIENTES_C_TELEFONO);
                    int esDiabeticoPaciente = resultSet.getInt(DBT_PACIENTES_C_DIABETICO); //IF PACIENTE DIABETICO -> 1 ELSE -> 0
                    LocalDate fechaNacimientoPaciente = resultSet.getDate(DBT_PACIENTES_C_FECHA_NACIMIENTO).toLocalDate();
                    int turnoPaciente = resultSet.getInt(DBT_PACIENTES_C_TURNO);
                    System.out.println(codigoPaciente + "\t" + nombrePaciente + "\t" + direccionPaciente + "\t" + ciudadPaciente + "\t" + telefonoPaciente + "\t" + esDiabeticoPaciente + "\t" + fechaNacimientoPaciente + "\t" + turnoPaciente);
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    //METODO PARA DEVOLVER POR CONSOLA LOS DATOS DE LA BASE DE DATOS
    public static void printTablaVisitas(){
        try{
            ResultSet resultSet = selectAllFromVisitas(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            if(resultSet != null){
                while (resultSet.next()){
                    int codigoVisita = resultSet.getInt(DBT_VISITAS_C_CODIGO);
                    int idPaciente = resultSet.getInt(DBT_VISITAS_C_ID_PACIENTE);
                    LocalDate fechaVisitaPaciente = resultSet.getDate(DBT_VISITAS_C_FECHA_VISITA).toLocalDate();
                    String enfermedaVisita = resultSet.getString(DBT_VISITAS_C_ENFERMEDAD);
                    Double importeVisita = resultSet.getDouble(DBT_VISITAS_C_IMPORTE);
                    Double porcentajePagoVisita = resultSet.getDouble(DBT_VISITAS_C_PORCENTAJE_PAGO);
                    LocalDate proximaVisita = resultSet.getDate(DBT_VISITAS_C_PROXIMA_VISITA).toLocalDate();
                    System.out.println(codigoVisita + "\t" + idPaciente + "\t" + fechaVisitaPaciente + "\t" + enfermedaVisita + "\t" + importeVisita + "\t" + porcentajePagoVisita + "\t" + proximaVisita);
                }
                resultSet.close();
                System.out.println("ALL APPOINTMENTS SUCCESSFULLY CHARGED!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
