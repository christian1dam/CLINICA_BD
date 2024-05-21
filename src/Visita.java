import java.time.LocalDate;

public class Visita {
    private int codigo;
    private int idPaciente;
    private LocalDate fechaVisita;
    private String enfermedad;
    private Double importe;
    private Double porcentajePago;
    private LocalDate proximaVisita;
    //TODO -> IMPLEMENTAR VALIDACIONES
    public Visita(int idPaciente, LocalDate fechaVisita, String enfermedad, Double importe, Double porcentajePago, LocalDate proximaVisita) {
        setIdPaciente(idPaciente);
        setFechaVisita(fechaVisita);
        setEnfermedad(enfermedad);
        setImporte(importe);
        setPorcentajePago(porcentajePago);
        setProximaVisita(proximaVisita);
    }

    public int getCodigo() {
        return this.codigo;
    }

    public int getIdPaciente() {
        return this.idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDate getFechaVisita() {
        return this.fechaVisita;
    }

    public void setFechaVisita(LocalDate fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public String getEnfermedad() {
        return this.enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public Double getImporte() {
        return this.importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Double getPorcentajePago() {
        return this.porcentajePago;
    }

    public void setPorcentajePago(Double porcentajePago) {
        this.porcentajePago = porcentajePago;
    }

    public LocalDate getProximaVisita() {
        return this.proximaVisita;
    }

    public void setProximaVisita(LocalDate proximaVisita) {
        this.proximaVisita = proximaVisita;
    }

    @Override
    public String toString() {
        return "Visita{" +
                "codigo=" + codigo +
                ", idPaciente=" + idPaciente +
                ", fechaVisita=" + fechaVisita +
                ", enfermedad='" + enfermedad + '\'' +
                ", importe=" + importe +
                ", porcentajePago=" + porcentajePago +
                ", proximaVisita=" + proximaVisita +
                '}';
    }
}
