
public class Estudiante extends Persona {
    private String carrera;
    private Materia[] materias; // arreglo de materias (se asume que Materia puede exponer una nota por getNota())

    public Estudiante(String nombre, String apellido, Integer edad, String documento,
                      String carrera, Materia[] materias) {
        super(nombre, apellido, edad, documento);
        this.carrera = carrera;
        this.materias = materias;
    }

    public Estudiante(String nombre, String apellido, int edad, String documento, double d) {
        super(nombre, apellido, edad, documento);
        // Puedes inicializar otros campos aquí si es necesario
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Materia[] getMaterias() {
        return materias;
    }

    public void setMaterias(Materia[] materias) {
        this.materias = materias;
    }

    /**
     * Calcula el promedio recursivamente sobre el subarreglo materias[indice..end].
     * Llamar con indice = 0 para promedio total.
     * Usa reflexión para intentar obtener getNota() de cada Materia (compatible con diferentes diseños).
     */
    public static double calcularPromedioRecursivo(Materia[] materias, int indice) {
        if (materias == null || indice >= materias.length) return 0.0;
        SumCount sc = sumarYContarRecursivo(materias, indice);
        return sc.count == 0 ? 0.0 : sc.sum / sc.count;
    }

    // helper interno para sumar y contar recursivamente
    private static SumCount sumarYContarRecursivo(Materia[] materias, int indice) {
        if (materias == null || indice >= materias.length) return new SumCount(0.0, 0);
        double nota = 0.0;
        int cuenta = 0;
        Materia m = materias[indice];
        if (m != null) {
            try {
                Object val = m.getClass().getMethod("getNota").invoke(m);
                if (val instanceof Number) {
                    nota = ((Number) val).doubleValue();
                    cuenta = 1;
                }
            } catch (Exception ignored) { }
        }
        SumCount resto = sumarYContarRecursivo(materias, indice + 1);
        return new SumCount(nota + resto.sum, cuenta + resto.count);
    }

    // helper simple para devolver suma y cantidad
    private static class SumCount {
        final double sum;
        final int count;
        SumCount(double s, int c) { sum = s; count = c; }
    }

    /**
     * Versión iterativa que calcula el promedio usando el arreglo de la instancia.
     */
    public double calcularPromedioIterativo() {
        if (materias == null || materias.length == 0) return 0.0;
        double suma = 0.0;
        int cuenta = 0;
        for (Materia m : materias) {
            if (m == null) continue;
            try {
                Object val = m.getClass().getMethod("getNota").invoke(m);
                if (val instanceof Number) {
                    suma += ((Number) val).doubleValue();
                    cuenta++;
                }
            } catch (Exception ignored) { }
        }
        return cuenta == 0 ? 0.0 : suma / cuenta;
    }

    @Override
    public String toString() {
        return String.format("Estudiante[%s, carrera='%s', materias=%d]",
                super.toString(), carrera, (materias == null ? 0 : materias.length));
    }

    @Override
    public boolean equals(Object o) {
        // hereda igualdad por documento desde Persona; conservarla
        if (this == o) return true;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getPromedio() {
        throw new UnsupportedOperationException("Unimplemented method 'getPromedio'");
    }
}
