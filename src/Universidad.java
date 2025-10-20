import java.util.Arrays;
import java.util.Comparator;

public class Universidad {

    /**
     * Cuenta recursivamente cuántos estudiantes del arreglo pertenecen a la carrera dada.
     * Llamar con indice = 0 para procesar todo el arreglo.
     */
    public static int contarEstudiantesRecursivo(Estudiante[] estudiantes, String carrera, int indice) {
        if (estudiantes == null || indice >= estudiantes.length) return 0;
        Estudiante actual = estudiantes[indice];
        int sumaActual = 0;
        if (actual != null && carrera != null && carrera.equals(actual.getCarrera())) {
            sumaActual = 1;
        }
        return sumaActual + contarEstudiantesRecursivo(estudiantes, carrera, indice + 1);
    }

    /**
     * Versión iterativa equivalente.
     */
    public static int contarEstudiantesIterativo(Estudiante[] estudiantes, String carrera) {
        if (estudiantes == null) return 0;
        int cuenta = 0;
        for (Estudiante e : estudiantes) {
            if (e != null && carrera != null && carrera.equals(e.getCarrera())) {
                cuenta++;
            }
        }
        return cuenta;
    }

    /**
     * Busca recursivamente un Estudiante por documento en el arreglo a partir de 'indice'.
     * Llamar con indice = 0 para buscar en todo el arreglo.
     */
    public static Estudiante buscarEstudianteRecursivo(Estudiante[] estudiantes, String documento, int indice) {
        if (estudiantes == null || indice >= estudiantes.length || documento == null) return null;
        Estudiante actual = estudiantes[indice];
        if (actual != null && documento.equals(actual.getDocumento())) return actual;
        return buscarEstudianteRecursivo(estudiantes, documento, indice + 1);
    }

    /**
     * Versión iterativa de la búsqueda por documento.
     */
    public static Estudiante buscarEstudianteIterativo(Estudiante[] estudiantes, String documento) {
        if (estudiantes == null || documento == null) return null;
        for (Estudiante e : estudiantes) {
            if (e != null && documento.equals(e.getDocumento())) return e;
        }
        return null;
    }

    public static Estudiante[] ordenarEstudiantesPorApellido(Estudiante[] estudiantes) {
        if (estudiantes == null) return null;
        int n = estudiantes.length;

        for (int i = 0; i < n - 1; i++) {
            int indiceMinimo = i;

            for (int j = i + 1; j < n; j++) {
                Estudiante ej = estudiantes[j];
                Estudiante emin = estudiantes[indiceMinimo];

                String apj = (ej == null) ? null : ej.getApellido();
                String apmin = (emin == null) ? null : emin.getApellido();

                // Colocar apellidos null al final; comparar ignorando mayúsculas/minúsculas
                if (apmin == null && apj != null) {
                    indiceMinimo = j;
                } else if (apj != null && apmin != null) {
                    if (apj.compareToIgnoreCase(apmin) < 0) {
                        indiceMinimo = j;
                    }
                }
            }

            if (indiceMinimo != i) {
                Estudiante temp = estudiantes[i];
                estudiantes[i] = estudiantes[indiceMinimo];
                estudiantes[indiceMinimo] = temp;
            }
        }

        return estudiantes;
    }

    /**
     * Ordena (localmente en una copia) y realiza búsqueda binaria por apellido.
     * Devuelve el índice del estudiante dentro del arreglo ordenado (no del arreglo original),
     * o -1 si no se encuentra.
     */
    public static int busquedaBinariaEstudiantes(Estudiante[] estudiantes, String apellido) {
        if (estudiantes == null || apellido == null) return -1;

        // copia para no mutar el arreglo original
        Estudiante[] arr = Arrays.copyOf(estudiantes, estudiantes.length);

        // Comparator: Estudiante nulo al final; apellidos nulos al final; comparación case-insensitive
        Comparator<Estudiante> comp = Comparator.nullsLast(
                Comparator.comparing(Estudiante::getApellido, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))
        );
        Arrays.sort(arr, comp);

        // limitar búsqueda a la porción con estudiantes y apellidos no nulos
        int last = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].getApellido() != null) last = i;
        }
        if (last == -1) return -1;

        int low = 0;
        int high = last;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            Estudiante midE = arr[mid];
            String apMid = midE.getApellido(); // no es null en [0..last]
            int cmp = String.CASE_INSENSITIVE_ORDER.compare(apMid, apellido);
            if (cmp == 0) return mid;
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}