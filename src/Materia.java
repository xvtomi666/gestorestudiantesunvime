import java.util.Objects;

public class Materia {
    private int Id;
    private String Nombre;
    private int Codigo;
    private int Credito;
    private float Calif;
    private Profesor profesor; // referencia al profesor que la imparte

    public Materia() {
    }

    public Materia(int id ,String nombre, int codigo, int credito, float calif) {
        this.Id = id;
        this.Nombre = nombre;
        this.Codigo = codigo;
        this.Credito = credito;
        this.Calif = calif; 
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public int getCredito() {
        return Credito;
    }

    public void setCredito(int credito) {
        Credito = credito;
    }

    public float getCalif() {
        return Calif;
    }

    public void setCalif(float calif) {
        Calif = calif;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
        if (profesor != null) {
            profesor.asignarMateria(this); // mantiene la relación bidireccional (evita duplicados)
        }
    }

    @Override
    public String toString() {
        String nombreProfesor = (profesor != null)
                ? String.format("%s %s", profesor.getNombre(), profesor.getApellido())
                : "null";
        // Si Materia tiene un campo 'nombre' úsalo; en caso contrario ajusta según tu diseño
        String nombreMateria;
        try {
            nombreMateria = (String) this.getClass().getMethod("getNombre").invoke(this);
        } catch (Exception e) {
            nombreMateria = this.getClass().getSimpleName();
        }
        return String.format("Materia[nombre='%s', profesor='%s']", nombreMateria, nombreProfesor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Materia)) return false;
        Materia materia = (Materia) o;
        // Igualdad por nombre (atributo único). Cambia a 'codigo' si tu clase tiene ese campo.
        String nombreThis = null;
        String nombreOther = null;
        try {
            nombreThis = (String) this.getClass().getMethod("getNombre").invoke(this);
            nombreOther = (String) materia.getClass().getMethod("getNombre").invoke(materia);
        } catch (Exception e) {
            // Si no existe getNombre(), compara por toString() como último recurso
            return Objects.equals(this.toString(), materia.toString());
        }
        return Objects.equals(nombreThis, nombreOther);
    }

    @Override
    public int hashCode() {
        try {
            String nombre = (String) this.getClass().getMethod("getNombre").invoke(this);
            return Objects.hash(nombre);
        } catch (Exception e) {
            return Objects.hash(this.toString());
        }
    }
}
