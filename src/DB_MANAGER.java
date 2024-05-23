import java.sql.*;
import java.time.LocalDate;

public class DB_MANAGER {
    private static Connection connection = null;
    //CONSTATNTES PARA LA CONEXION
    private static final String DBHOST =  "192.168.56.1";
    private static final String PORT = "1433";
    private static final String DBNAME = "CLINICA_DB";
    //CONSTANTES PARA EL USUARIO
    private static final String DBUSER = "Alberto";
    private static final String PASSWORD = "1234";
    //CONSTANTES SEGURIDAD
    private static final String ENCRYPT = "true";
    private static final String TRUST_SERVER_CERTIFICATE = "true";
    //URL FINAL
    private static final String URL ="jdbc:sqlserver://" + DBHOST + ":" + PORT + ";databaseName=" + DBNAME + ";user=" + DBUSER + ";password=" + PASSWORD + ";encrypt=" + ENCRYPT + ";trustServerCertificate=" + TRUST_SERVER_CERTIFICATE;
    //CONSTANTES PARA CHECK SI SE AH PODIDO CONECTAR CON LA BASE DE DATOS
    private static final String DB_MYSQL_CONNECTION_OK = "SUCCESSFULLY CONNECTED TO " + DBNAME + " DATABASE";
    private static final String DB_MYSQL_CONNECTION_NOT_OK = "ERROR! COULD NOT BEEN CONNECTED TO" + DBNAME + " DATABASE";
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

    //METODO PARA CARGAR EL DRIVER SQLSEREVER
    public static boolean loadDriver(){
        try {
            System.out.println("Cargando Driver DBJC...");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
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
            connection = DriverManager.getConnection(URL);
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

    //METODO PARA EJECUTAR LA QUERY SELECT * FROM PACIENTES Y DEVOLVER EL RESULTADO.
    public static ResultSet selectAllFromPacientes(){
        return selectAllFromPacientes(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public static ResultSet selectAllFromPacientes(int resultSetType, int resultSetConcurrency){
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
    public static ResultSet selectAllFromVisitas(int resultSetType, int resultSetConcurrency){
        try{
            Statement statement = connection.createStatement(resultSetType, resultSetConcurrency);
            return statement.executeQuery(DBT_VISITAS_SELECT_ALL_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    //METODO PARA DEVOLVER POR CONSOLA LOS DATOS DE LA TABLA PACIENTES
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
                    int id_usuario = resultSet.getInt(DBT_PACIENTES_C_ID_USUARIO);
                    System.out.println("CODIGO PACIENTE: " + codigoPaciente + "\t" + nombrePaciente + "\t" + direccionPaciente + "\t" + ciudadPaciente + "\t" + telefonoPaciente + "\t" + esDiabeticoPaciente + "\t" + fechaNacimientoPaciente + "\t" + turnoPaciente + "\t" + id_usuario);
                }
                resultSet.close();
            }
            System.out.println("ALL PATIENTS SUCCESSFULLY CHARGED!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    //METODO PARA DEVOLVER POR CONSOLA LOS DATOS DE LA TABLA VISITAS
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
                    System.out.println(codigoVisita + "\t" + " ID PACIENTE: " + idPaciente + "\t" + fechaVisitaPaciente + "\t" + enfermedaVisita + "\t" + importeVisita + "\t" + porcentajePagoVisita + "\t" + proximaVisita);
                }
                resultSet.close();
            }
            System.out.println("ALL APPOINTMENTS SUCCESSFULLY CHARGED!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet getPatienByName(int resultSetType, int resultSetConcurrency, String name) {
        try {
            Statement statement = connection.createStatement(resultSetType, resultSetConcurrency);
            return statement.executeQuery("select * from " + DBT_PACIENTES + " where " + DBT_PACIENTES_C_NOMBRE + " = '" + name + "';");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void crearPaciente(Paciente paciente) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("INSERT INTO " + DBT_PACIENTES + "(NOMBRE, DIRECCION, CIUDAD, TELEFONO, DIABETICO, FECHA_NACIMIENTO, TURNO, ID_USUARIO) VALUES(" + "'" + paciente.getNombre() + "'" + "'" + paciente.getDireccion() + "'" + "'" + paciente.getCiudad() + "'" + "'" + paciente.getTelefono() + "'" + "null' " + "'" + paciente.getFechaNacimiento() + "'" + "'" + paciente.getTurno() + "'" + "'" + "null" + "'" + ");");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
