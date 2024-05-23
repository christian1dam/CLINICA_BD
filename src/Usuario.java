public class Usuario {
    private int codigo;
    private String nombre;
    private String contrasenya;

    public Usuario(int codigo, String nombre, String contrasenya) {
        setCodigo(codigo);
        setNombre(nombre);
        setContrasenya(contrasenya);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                '}';
    }
}
