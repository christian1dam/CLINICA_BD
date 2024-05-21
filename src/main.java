public class main {
    public static void main(String[] args) {
        DB_MANAGER_CLINICADB.loadDriver();
        DB_MANAGER_CLINICADB.openConnectionToDatabase();
        DB_MANAGER_CLINICADB.printTablaPacientes();
        System.out.println();
        DB_MANAGER_CLINICADB.printTablaVisitas();
    }
}
