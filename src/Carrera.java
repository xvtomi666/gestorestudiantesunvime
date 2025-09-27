import java.util.List;

public class Carrera {
    private int Id;
    private String Nombre;
    private List<Estudiante> estudiantes;
    private List<Materia> materias;

    public Carrera() {
    }

    public Carrera(int id, String nombre, List<Estudiante> estudiantes, List<Materia> materias) {
        this.Id = id; 
        this.Nombre = nombre;
        this.estudiantes = estudiantes;
        this.materias = materias;
    }
    // getters y setters

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

}