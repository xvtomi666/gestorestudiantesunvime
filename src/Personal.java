import java.time.LocalDate;

public class Personal extends Persona {
    private String departamento;
    private String puesto;
    private LocalDate fechaIngreso;

    public Personal(String nombre, String apellido, Integer edad, String documento,
                    String departamento, String puesto, LocalDate fechaIngreso) {
        super(nombre, apellido, edad, documento);
        this.departamento = departamento;
        this.puesto = puesto;
        this.fechaIngreso = fechaIngreso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return String.format("Personal[%s, departamento='%s', puesto='%s', fechaIngreso=%s]",
                super.toString(), departamento, puesto, fechaIngreso);
    }
}