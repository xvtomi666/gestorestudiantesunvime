import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- Crear materias ---
        Materia diseñoWeb = new Materia(1, "Diseño Web", 1, 3, 0f);
        Materia progUno = new Materia(2, "Programacion I", 2, 3, 0f);
        Materia paradigmas = new Materia(3, "Paradigmas de Programacion", 3, 2, 0f);

        Materia matematica = new Materia(4, "Matematica", 5, 4, 0f);
        Materia algebra = new Materia(5, "Algebra", 6, 4, 0f);
        Materia fisica = new Materia(6, "Fisica", 7, 4, 0f);

        Materia derechoCivil = new Materia(7, "Derecho Civil", 11, 3, 0f);
        Materia derechoPenal = new Materia(8, "Derecho Penal", 12, 2, 0f);
        Materia teoriaEstado = new Materia(9, "Teoria General Del Estado", 13, 3, 0f);

        List<Materia> materiasProg = Arrays.asList(diseñoWeb, progUno, paradigmas);
        List<Materia> materiasMath = Arrays.asList(matematica, algebra, fisica);
        List<Materia> materiasDer = Arrays.asList(derechoCivil, derechoPenal, teoriaEstado);

        // --- Crear carreras ---
        Carrera carreraProg = new Carrera(1, "Programador Universitario en Sistemas", new ArrayList<>(), materiasProg);
        Carrera carreraMath = new Carrera(2, "Matematicas", new ArrayList<>(), materiasMath);
        Carrera carreraDer = new Carrera(3, "Abogacia", new ArrayList<>(), materiasDer);

        List<Carrera> carreras = new ArrayList<>();
        carreras.add(carreraProg);
        carreras.add(carreraMath);
        carreras.add(carreraDer);

        // --- Profesores y Personal ---
        List<Profesor> profesores = new ArrayList<>();
        // El constructor disponible de Profesor no acepta la edad, solo nombre, apellido y documento.
        Profesor profAna = new Profesor("Ana", "Gomez", null, "PROF-001", null, 0);
        Profesor profLuis = new Profesor("Luis", "Martinez", null, "PROF-002", null, 0);
        profesores.add(profAna);
        profesores.add(profLuis);

        List<Personal> personal = new ArrayList<>();
        Personal p1 = new Personal("María", "Fernandez", 38, "PERS-001", "Administración", "Jefa de Mesa", LocalDate.of(2018, 3, 1));
        Personal p2 = new Personal("Carlos", "Dominguez", 45, "PERS-002", "Secretaría", "Secretario", LocalDate.of(2020, 6, 15));
        personal.add(p1);
        personal.add(p2);

        // Asociar materias a profesores (bidireccional si Materia.setProfesor mantiene relación)
        diseñoWeb.setProfesor(profAna);
        progUno.setProfesor(profAna);
        paradigmas.setProfesor(profLuis);
        matematica.setProfesor(profLuis);

        // --- Estudiantes (vacío inicialmente) ---
        List<Estudiante> estudiantes = new ArrayList<>();

        // --- Menú ---
        int opcion;
        do {
            System.out.println("\n===== UNVIME 2025 - GESTOR =====");
            System.out.println("1. Crear estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Listar carreras");
            System.out.println("4. Listar profesores");
            System.out.println("5. Listar personal");
            System.out.println("6. Asignar materia a profesor");
            System.out.println("7. Resumen universidad");
            System.out.println("8. Salir");
            System.out.print("Opción: ");
            opcion = readInt(sc);

            switch (opcion) {
                case 1:
                    crearEstudiante(sc, carreras, estudiantes);
                    break;
                case 2:
                    listarEstudiantes(estudiantes);
                    break;
                case 3:
                    listarCarreras(carreras);
                    break;
                case 4:
                    listarProfesores(profesores);
                    break;
                case 5:
                    listarPersonal(personal);
                    break;
                case 6:
                    asignarMateriaAProfesor(sc, profesores, Arrays.asList(diseñoWeb, progUno, paradigmas, matematica, algebra, fisica, derechoCivil, derechoPenal, teoriaEstado));
                    break;
                case 7:
                    resumenUniversidad(profesores, personal, estudiantes, carreras);
                    break;
                case 8:
                    System.out.println("Saliendo.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 8);

        sc.close();
    }

    private static int readInt(Scanner sc) {
        try { return Integer.parseInt(sc.nextLine().trim()); }
        catch (Exception e) { return -1; }
    }

    private static void crearEstudiante(Scanner sc, List<Carrera> carreras, List<Estudiante> estudiantes) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine().trim();
        System.out.print("Edad: ");
        int edad = 0;
        try { edad = Integer.parseInt(sc.nextLine().trim()); } catch (Exception ignored) {}
        System.out.print("Documento: ");
        String documento = sc.nextLine().trim();

        System.out.println("Seleccione carrera por ID:");
        for (Carrera c : carreras) System.out.println(c.getId() + " - " + c.getNombre());
        int id = readInt(sc);
        Carrera sel = null;
        for (Carrera c : carreras) if (c.getId() == id) { sel = c; break; }
        if (sel == null) { System.out.println("Carrera no encontrada."); return; }

        System.out.println("Materias disponibles:");
        for (Materia m : sel.getMaterias()) System.out.println(m.getId() + " - " + m.getNombre());
        System.out.println("IDs de materias (coma separados) o Enter para omitir:");
        String line = sc.nextLine().trim();
        List<Materia> elegido = new ArrayList<>();
        if (!line.isEmpty()) {
            String[] parts = line.split(",");
            for (String p : parts) {
                try {
                    int mid = Integer.parseInt(p.trim());
                    for (Materia m : sel.getMaterias()) if (m.getId() == mid) { elegido.add(m); break; }
                } catch (Exception ignored) {}
            }
        }
        Materia[] materiasArr = elegido.toArray(new Materia[0]);
        Estudiante e = new Estudiante(nombre, apellido, edad, documento, sel.getNombre(), materiasArr);
        estudiantes.add(e);
        try { sel.getEstudiantes().add(e); } catch (Exception ignored) {}
        System.out.println("Estudiante creado: " + nombre + " " + apellido);
    }

    private static void listarEstudiantes(List<Estudiante> estudiantes) {
        if (estudiantes.isEmpty()) { System.out.println("No hay estudiantes."); return; }
        System.out.println("\nEstudiantes:");
        for (Estudiante e : estudiantes) {
            double prom = 0.0;
            try { prom = e.calcularPromedioIterativo(); }
            catch (Exception ex) {
                try { prom = Estudiante.calcularPromedioRecursivo(e.getMaterias(), 0); } catch (Exception ignored) {}
            }
            System.out.println(e.getNombre() + " " + e.getApellido() + " | Doc: " + e.getDocumento()
                    + " | Carrera: " + safe(e.getCarrera()) + " | Prom: " + String.format("%.2f", prom));
        }
    }

    private static void listarCarreras(List<Carrera> carreras) {
        System.out.println("\nCarreras:");
        for (Carrera c : carreras) {
            int cant = 0;
            try { cant = c.getEstudiantes().size(); } catch (Exception ignored) {}
            System.out.println(c.getId() + " - " + c.getNombre() + " | Estudiantes: " + cant);
        }
    }

    private static void listarProfesores(List<Profesor> profesores) {
        System.out.println("\nProfesores:");
        for (Profesor p : profesores) {
            System.out.println(p.getNombre() + " " + p.getApellido() + " | Doc: " + p.getDocumento()
                    + " | Materias: " + (p.getMaterias() == null ? 0 : p.getMaterias().size()));
        }
    }

    private static void listarPersonal(List<Personal> personal) {
        System.out.println("\nPersonal:");
        for (Personal p : personal) {
            System.out.println(p.getNombre() + " " + p.getApellido() + " | " + p.getDepartamento() + " - " + p.getPuesto()
                    + " | Ingreso: " + p.getFechaIngreso());
        }
    }

    private static void asignarMateriaAProfesor(Scanner sc, List<Profesor> profes, List<Materia> todasMaterias) {
        System.out.println("Seleccione profesor por índice:");
        for (int i = 0; i < profes.size(); i++) System.out.println(i + " - " + profes.get(i).getNombre() + " " + profes.get(i).getApellido());
        int pi = readInt(sc);
        if (pi < 0 || pi >= profes.size()) { System.out.println("Profesor inválido."); return; }
        Profesor prof = profes.get(pi);

        System.out.println("Materias disponibles:");
        for (int i = 0; i < todasMaterias.size(); i++) System.out.println(todasMaterias.get(i).getId() + " - " + todasMaterias.get(i).getNombre());
        System.out.print("Ingrese ID de materia a asignar: ");
        int mid = readInt(sc);
        Materia sel = null;
        for (Materia m : todasMaterias) if (m.getId() == mid) { sel = m; break; }
        if (sel == null) { System.out.println("Materia no encontrada."); return; }

        // asignar bidireccionalmente
        try { sel.setProfesor(prof); } catch (Exception ex) {
            try { prof.asignarMateria(sel); } catch (Exception ignored) {}
        }
        System.out.println("Materia asignada a " + prof.getNombre() + " " + prof.getApellido());
    }

    private static void resumenUniversidad(List<Profesor> profes, List<Personal> personal, List<Estudiante> estudiantes, List<Carrera> carreras) {
        System.out.println("\n--- Resumen Universidad ---");
        System.out.println("Profesores: " + profes.size());
        System.out.println("Personal administrativo: " + personal.size());
        System.out.println("Carreras: " + carreras.size());
        System.out.println("Estudiantes totales: " + estudiantes.size());
        // ejemplo uso de métodos estáticos de Universidad
        try {
            Estudiante[] arr = estudiantes.toArray(new Estudiante[0]);
            int totalProg = Universidad.contarEstudiantesIterativo(arr, "Programador Universitario en Sistemas");
            System.out.println("Estudiantes en Programador Universitario: " + totalProg);
        } catch (Exception ignored) {}
    }

    private static String safe(String s) { return s == null ? "(sin info)" : s; }
}