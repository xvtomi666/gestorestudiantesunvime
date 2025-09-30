import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Materias de Programador Universitario
        Materia diseñoWeb = new Materia (1 ,"Diseño Web", 1, 3, 0f);
        Materia progUno = new Materia(2,"Programacion I", 2, 3, 0f);
        Materia paradigmas = new Materia(3,"Paradigmas de Programacion", 3, 2, 0f);
        // Materias de Matemáticas
        Materia matematica = new Materia(4,"Matematica", 5, 4, 0f);
        Materia algebra = new Materia(5,"Algebra", 6, 4, 0f);
        Materia fisica = new Materia(6,"Fisica", 7, 4, 0f);
        // Materias Abogacía
        Materia derechoCivil = new Materia(7,"Derecho Civil", 11, 3, 0f);
        Materia derechoPenal = new Materia(7,"Derecho Penal", 12, 2, 0f);
        Materia teoriaEstado = new Materia(8,"Teoria General Del Estado", 13, 3, 0f);

        List<Materia> materiasProg = new ArrayList<>();
        materiasProg.add(diseñoWeb);
        materiasProg.add(progUno);
        materiasProg.add(paradigmas);

        List<Materia> materiasMath = new ArrayList<>();
        materiasMath.add(matematica);
        materiasMath.add(algebra);
        materiasMath.add(fisica);

        List<Materia> materiasDer = new ArrayList<>();
        materiasDer.add(derechoCivil);
        materiasDer.add(derechoPenal);
        materiasDer.add(teoriaEstado);

        // carreras
        // Programacion
        Carrera carreraProg = new Carrera(1, "Programador Universitario en Sistemas", new ArrayList<>(), materiasProg);
        // Matematicas
        Carrera carreraMath = new Carrera(2, "Matematicas", new ArrayList<>(), materiasMath);
        // Abogacia
        Carrera carreraDer = new Carrera(3, "Abogacia", new ArrayList<>(), materiasMath);

        List<Carrera> carreras = new ArrayList<>();
        carreras.add(carreraProg);
        carreras.add(carreraMath);
        carreras.add(carreraDer);

        // menu
        List<Estudiante> estudiantes = new ArrayList<>();
        int opcion;
        do {

            System.out.println("\n===== GESTOR DE ESTUDIANTES UNVIME 2025 =====");
            System.out.println("1. Crear estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Listar carreras");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del estudiante");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese el apellido del estudiante");
                    String apellido = sc.nextLine();
                    System.out.println("Ingrese la edad del estudiante");
                    String edad = sc.nextLine();

                    System.out
                            .println("Seleccione una carrera por ID:");
                    for (Carrera c : carreras) {
                        System.out.println(c.getId() + " - " + c.getNombre());
                    }
                    int idCarrera = sc.nextInt();
                    sc.nextLine();
                    Carrera carreraSeleccionada = null;
                    for (Carrera c : carreras) {
                        if (c.getId() == idCarrera) {
                            carreraSeleccionada = c;
                            break;
                        }
                    }
                    if (carreraSeleccionada != null) {
                        Estudiante e = new Estudiante(nombre, apellido, Integer.parseInt(edad),
                                carreraSeleccionada.getNombre(), 0.0);
                        estudiantes.add(e);
                        carreraSeleccionada.getEstudiantes().add(e);
                        System.out.println(
                                "Estudiante creado y asignado a la carrera " + carreraSeleccionada.getNombre());
                    } else {
                        System.out.println("Carrera no encontrada. Estudiante no creado.");
                    }

                    System.out.println("Elija las materias del estudiante");
                    for (Materia m : carreraSeleccionada.getMaterias()) {
                        System.out.println(m.getId() + " - " + m.getNombre());
                    }
                    System.out.println("Ingrese los IDs de las materias separadas por comas (ej: 1,2):");
                    String[] idsMaterias = sc.nextLine().split(",");
                    List<Materia> materiasEstudiante = new ArrayList<>();
                    for (String idStr : idsMaterias) {
                        int id = Integer.parseInt(idStr.trim());
                        for (Materia m : carreraSeleccionada.getMaterias()) {
                            if (m.getId() == id) {
                                materiasEstudiante.add(m);
                                break;
                            }
                        }
                    }

                    System.out.println("Asigne las calificaciones de las materias:");
                    for (Materia m : materiasEstudiante) {
                        System.out.println("Ingrese la calificación para " + m.getNombre() + ":");
                        float calif = sc.nextFloat();
                        m.setCalif(calif);
                    }
                    sc.nextLine(); // limpiar buffer
                    

                    
                    System.out.println("Calificaciones asignadas correctamente.");
                    break;
                case 2:
                    if (estudiantes.isEmpty()) {
                        System.out.println("No hay estudiantes registrados.");
                    } else {
                        System.out.println("\nLista de Estudiantes:");
                        for (Estudiante est : estudiantes) {
                            System.out.println("Nombre: " + est.getNombre() + " " + est.getApellido()
                                    + ", Edad: " + est.getEdad() + ", Carrera: " + est.getCarrera() + ", Promedio: " + est.getPromedio());
                        }
                            
                    }
                    break;
                case 3:
                    System.out.println("\nLista de Carreras:");
                    for (Carrera c : carreras) {
                        System.out.println("ID: " + c.getId() + ", Nombre: " + c.getNombre()
                                + ", Cantidad de Estudiantes: " + c.getEstudiantes().size());
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;

            }

        } while (opcion != 4);
        sc.close();
    }
}