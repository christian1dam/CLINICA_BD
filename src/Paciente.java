import java.time.LocalDate;

public class Paciente {
    private int codigo;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private int diabetico;
    private LocalDate fechaNacimiento;
    private int turno;

    //TODO -> IMPLEMENTAR VALIDACIONES
    public Paciente(int codigo, String nombre, String direccion, String ciudad, String telefono, int diabetico, LocalDate fechaNacimiento, int turno) {
        this.codigo = codigo;
        setNombre(nombre);
        setDireccion(direccion);
        setCiudad(ciudad);
        setTelefono(telefono);
        setDiabetico(diabetico);
        setFechaNacimiento(fechaNacimiento);
        setTurno(turno);
    }
    public Paciente(String nombre, String direccion, String ciudad, String telefono, LocalDate fechaNacimiento, int turno) {
        setNombre(nombre);
        setDireccion(direccion);
        setCiudad(ciudad);
        setTelefono(telefono);
        setDiabetico(diabetico);
        setFechaNacimiento(fechaNacimiento);
        setTurno(turno);
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getDiabetico() {
        return this.diabetico;
    }

    public void setDiabetico(int diabetico) {
        this.diabetico = diabetico;
    }

    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTurno() {
        return this.turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", telefono='" + telefono + '\'' +
                ", diabetico=" + diabetico +
                ", fechaNacimiento=" + fechaNacimiento +
                ", turno=" + turno +
                '}';
    }
}
