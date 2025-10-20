import java.util.List;
import java.util.ArrayList;

public class Profesor extends Persona {

    private String especialidad;
    private float experiencia;
    private List<Materia> materias = new ArrayList<>();

    public Profesor(String nombre, String apellido, Integer edad, String documento /*, ...otros... */, String especialidad, float experiencia) {
        super(nombre, apellido, edad, documento);
        this.especialidad = especialidad;
        this.experiencia = experiencia;
    }

    public void asignarMateria(Materia materia) {
        if (materia == null)
            return;
        if (!materias.contains(materia)) {
            materias.add(materia);
        }
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public float getExperiencia() {
        return experiencia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Profesor[nombre='%s', apellido='%s', edad=%s, documento='%s', especialidad='%s', experiencia=%s'",
                getNombre(), getApellido(), getEdad(), getDocumento(), getEspecialidad(), getExperiencia()));
        sb.append(", materias=");
        if (materias == null || materias.isEmpty()) {
            sb.append("[]");
        } else {
            sb.append("[");
            for (int i = 0; i < materias.size(); i++) {
                Materia m = materias.get(i);
                // intentar obtener un nombre legible de la materia sin depender de toString() (evita recursión)
                String nombreMateria;
                try {
                    nombreMateria = (String) m.getClass().getMethod("getNombre").invoke(m);
                } catch (Exception e) {
                    nombreMateria = m.getClass().getSimpleName();
                }
                sb.append(nombreMateria);
                if (i < materias.size() - 1)
                    sb.append(", ");
            }
            sb.append("]");
        }
        sb.append("]");
        return sb.toString();
    }
}